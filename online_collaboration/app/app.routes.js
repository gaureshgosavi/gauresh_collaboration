
window.routes =
    {
        "/about": {
            templateUrl: 'app/components/page/about.html',
            controller: 'aboutController',
            controllerAs: 'aboutCtrl',
            requireLogin: false,
            roles: ['GUEST']
        },

        "/contact": {
            templateUrl: 'app/components/page/contact.html',
            controller: 'contactController',
            controllerAs: 'contactCtrl',
            requireLogin: false,
            roles: ['GUEST']
        },

        "/user/home": {
            templateUrl: 'app/components/user/home.html',
            controller: 'UserController',
            controllerAs: 'userCtrl',
            requireLogin: true,
            roles: ['USER']
        },

        "/admin/home": {
            templateUrl: 'app/components/admin/home.html',
            controller: 'AdminController',
            controllerAs: 'adminCtrl',
            requireLogin: true,
            roles: ['ADMIN']
        },

        "/admin/pendingBlogs": {
            templateUrl: 'app/components/blog/acceptBlog.html',
            controller: 'AdminController',
            controllerAs: 'adminCtrl',
            requireLogin: true,
            roles: ['ADMIN']
        },

        "/admin/pendingUsers": {
            templateUrl: 'app/components/user/acceptUser.html',
            controller: 'AdminController',
            controllerAs: 'adminCtrl',
            requireLogin: true,
            roles: ['ADMIN']
        },

        "/user/createBlog": {
            templateUrl: 'app/components/blog/createBlog.html',
            controller: 'BlogController',
            controllerAs: 'blogCtrl',
            requireLogin: true,
            roles: ['USER']
        },

        "/user/viewBlog": {
            templateUrl: 'app/components/blog/viewBlog.html',
            controller: 'BlogController',
            controllerAs: 'blogCtrl',
            requireLogin: true,
            roles: ['USER']
        },

        "/user/singleBlog": {
            templateUrl: 'app/components/blog/singleBlog.html',
            controller: 'BlogController',
            controllerAs: 'blogCtrl',
            requireLogin: true,
            roles: ['USER']
        },

        "/user/createForum": {
            templateUrl: 'app/components/forum/createForum.html',
            controller: 'ForumController',
            controllerAs: 'forumCtrl',
            requireLogin: true,
            roles: ['USER']
        },

        "/login": {
            templateUrl: 'app/components/authentication/login.html',
            controller: 'AuthenticationController',
            controllerAs: 'authCtrl',
            requireLogin: false,
            roles: ['GUEST']
        },
        "/register": {
            templateUrl: 'app/components/authentication/register.html',
            controller: 'AuthenticationController',
            controllerAs: 'authCtrl',
            requireLogin: false,
            roles: ['GUEST']
        },
        "/error": {
            templateUrl: 'app/components/authentication/error.html',
            controller: 'AuthenticationController',
            controllerAs: 'authCtrl',
            requireLogin: false,
            roles: ['GUEST']
        },
        "/blogs": {
            templateUrl: 'app/components/blog/listblog.html',
            controller: 'BlogController',
            controllerAs: 'blogCtrl',
            requireLogin: true,
            roles: ['USER', 'ADMIN']
        },

    };

myApp.config(['$locationProvider', '$routeProvider', '$httpProvider', function ($locationProvider, $routeProvider, $httpProvider) {


    // allows the cookie with session id to be send back
    //$httpProvider.defaults.withCredentials = true;

    // fill up the path in the $routeProvider the objects created before
    for (var path in window.routes) {
        $routeProvider.when(path, window.routes[path]);
    }

    $routeProvider.otherwise({
        redirectTo: '/login'
    })

    $locationProvider.hashPrefix('!');

}]);


myApp.run(function ($rootScope, $location, AuthenticationFactory) {
    $rootScope.$on('$locationChangeStart', function (event, next, current) {

        // iterate through all the routes
        for (var i in window.routes) {
            // if routes is present make sure the user is authenticated 
            // before login using the authentication service            
            if (next.indexOf(i) != -1) {
                // if trying to access page which requires login and is not logged in                                
                if (window.routes[i].requireLogin && !AuthenticationFactory.getUserIsAuthenticated()) {
                    $location.path('/login');
                }
                else if ((AuthenticationFactory.getUserIsAuthenticated())
                    &&
                    (window.routes[i].roles.indexOf(AuthenticationFactory.getRole()) == -1)) {
                    $location.path('/error');
                }
            }
        }
    });

    $rootScope.logout = function () {
        console.log($rootScope.userId);
        AuthenticationFactory.logout($rootScope.userId)
            .then(
            function (user) {
                AuthenticationFactory.setUserIsAuthenticated(false);
                AuthenticationFactory.setRole('GUEST');
                $rootScope.authenticated = false;
                $rootScope.isAdmin = false;
                $rootScope.isUser = false;
                $rootScope.islogin = false;
                $location.path('/login');
                console.log(user);
            },
            function (errorResponse) {

                console.log(errorResponse);
            }
            )
    }

});
