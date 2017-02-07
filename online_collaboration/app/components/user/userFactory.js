var UserModule = angular.module('UserModule', ['UploadModule']);

UserModule.factory('UserFactory', ['$http', '$q', function ($http, $q) {
    var url = 'http://localhost:8090/collaboration_backend/';

    return {
        getUser: getUser,
        updateUser: updateUser,
        addFriend: addFriend,
        getMyFriends: getMyFriends,
        getRequests: getRequests,
        acceptFriendRequest: acceptFriendRequest,
        rejectFriendRequest: rejectFriendRequest
    };

    function updateUser(user, userId) {
        var deferred = $q.defer();
        $http.put(url + '/user/get/' + userId, user)
            .then(
            function (response) {
                deferred.resolve(response.data);
                console.log(response.data);
            },
            function (errResponse) {
                console.error('Error while updating User');
                deferred.reject(errResponse);
            }
            );
        return deferred.promise;
    }

    function getUser(userId) {
        var deferred = $q.defer();
        $http.get(url + '/user/get/' + userId)
            .then(
            function (response) {
                deferred.resolve(response.data);
                console.log(response.data);
            },
            function (errResponse) {
                console.error('Error while updating User');
                deferred.reject(errResponse);
            }
            );
        return deferred.promise;
    }

    function addFriend(request) {
        console.log(request);

        var deferred = $q.defer();
        $http.post(url + '/addFriend', request)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function (errResponse) {
                console.error('Error while sending request');
                deferred.reject(errResponse);
            }
            );
        return deferred.promise;
    }

    function getMyFriends(userId) {
        console.log(userId);

        var deferred = $q.defer();
        $http.get(url + '/friendList/' + userId)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function (errResponse) {
                console.error('Error while fetching friends');
                deferred.reject(errResponse);
            }
            );
        return deferred.promise;

    }

    function getRequests(userId) {
        console.log(userId);

        var deferred = $q.defer();
        $http.get(url + '/friendRequests/' + userId)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function (errResponse) {
                console.error('Error while fetching friends');
                deferred.reject(errResponse);
            }
            );
        return deferred.promise;

    }

    function acceptFriendRequest(request) {
        debugger;
        console.log(request);

        var deferred = $q.defer();
        $http.put(url + '/acceptRequest', request)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function (errResponse) {
                console.error('Error while accepting request');
                deferred.reject(errResponse);
            }
            );
        return deferred.promise;

    }

    function rejectFriendRequest(request) {
        debugger;
        console.log(request);

        var deferred = $q.defer();
        $http.put(url + '/rejectRequest' , request)
            .then(
            function (response) {
                console.log(response.data);
                deferred.resolve(response.data);
            },
            function (errResponse) {
                console.error('Error while rejecting request');
                deferred.reject(errResponse);
                console.log(errResponse);
            }
            );
        return deferred.promise;

    }
}]);
