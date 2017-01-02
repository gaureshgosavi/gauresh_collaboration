myApp.config(['$locationProvider','$routeProvider',function($locationProvider,$routeProvider){
    $routeProvider

    .when('/home',{
        templateUrl : 'app/components/page/home.html',
        controller : 'homeController'
    })

    .when('/about',{
        templateUrl : 'app/components/page/about.html',
        controller : 'aboutController'
    })

    .when('/contact',{
        templateUrl : 'app/components/page/contact.html',
        controller : 'contactController'
    })
    .otherwise({
        redirectTo: '/home'
    })

    $locationProvider.hashPrefix('!');

}]);
