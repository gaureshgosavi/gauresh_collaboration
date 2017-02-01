myApp.factory('userFactory', ['$http', '$q', function ($http, $q) {
    var url = 'http://localhost:8090/collaboration_backend/user/';

    return {
         fetchAllUsers: fetchAllUsers,
        createUser: createUser,
        updateUser: updateUser
    };

    function fetchAllUsers() {
        var deferred = $q.defer();
        $http.get(url + 'list').
            then(function (response) {
                deferred.resolve(response.data);
            }, function (errResponse) {
                deferred.reject(errResponse);
            });
        return deferred.promise;
    }

       function createUser(user) {
        var deferred = $q.defer();
        $http.post(url+'register', user)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while creating User');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
 
 
    function updateUser(user, id) {
        var deferred = $q.defer();
        $http.put(url+'user/get'+id, user)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while updating User');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
}]);
