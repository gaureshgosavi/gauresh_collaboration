
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
                    event.preventDefault();
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
});