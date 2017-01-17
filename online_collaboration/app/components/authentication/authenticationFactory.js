var AuthenticationModule = angular.module('AuthenticationModule',[]);

AuthenticationModule.factory('AuthenticationFactory',['$http','$q', '$rootScope',function($http,$q,$rootScope){
    var url = 'http://localhost:8090/collaboration_backend/';
    var userIsAuthenticated = false;
    var role = 'GUEST';
    var user = {};

    return{
        setUserIsAuthenticated: setUserIsAuthenticated,
        getUserIsAuthenticated: getUserIsAuthenticated,
        setRole: setRole,
        getRole: getRole,
        login: login
    };

    function setUserIsAuthenticated(value) {
        userIsAuthenticated = value;
    }

    function getUserIsAuthenticated() {
        return userIsAuthenticated;
    }

    function setRole(value) {
        role = value;
    }

    function getRole() {
        return role;
    }

    function login(credentials) {
        console.log(credentials);
        
    var deferred = $q.defer();
    $http.post(url+'login', credentials)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while logging in');
                $rootScope.errorMessage = "Invalid credentials."
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;

    }
}]);