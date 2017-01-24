var ForurmModule = angular.module('ForurmModule', []);

ForurmModule.factory('ForumFactory', ['$http', '$q', function ($http, $q) {
    var url = 'http://localhost:8090/collaboration_backend//forum/';

    var forumCommentUrl = 'http://localhost:8090/collaboration_backend//forumComment/';

    var factory = {
        fetchAllForums: fetchAllForums,
        createForum: createForum,
        updateForum: updateForum,
        deleteForum: deleteForum,
        getForum: getForum,
        createForumComment: createForumComment,
        getforumComments: getforumComments
    };

    return factory;

    //getting all forums
    function fetchAllForums() {

        var deferred = $q.defer();

        $http.get(url + 'list').
            then(function (response) {
                console.log(response.data);
                deferred.resolve(response.data);
            }, function (errResponse) {
                console.error('error fetching forums');
                deferred.reject(errResponse);
            });
        return deferred.promise;
    }

    //create forum
    function createForum(forum) {
        var deferred = $q.defer();
        $http.post(url + 'create', forum).
            then(function (response) {
                console.log(response.data);
                deferred.resolve(response.data);
            }, function (errResponse) {
                console.error('error creating forums');
                deferred.reject(errResponse);
            });
        return deferred.promise;
    }

    //update forum
    function updateForum(forum) {
        var deferred = $q.defer();
        $http.put(url + 'get/' + forumId, forum).
            then(function (response) {
                console.log(response.data);
                deferred.resolve(response.data);
            }, function (errResponse) {
                console.error('error updating forums');
                deferred.reject(errResponse);
            });
        return deferred.promise;
    }

    // delete forum
    function deleteForum(forumId) {
        var deferred = $q.defer();

        $http.delete(url + 'delete/' + forumId).
            then(function (response) {
                deferred.resolve(response.data);
                console.log(response.data);
            }, function (errResponse) {
                console.error('error deleting forums');
                deferred.reject(errResponse);
            });
        return deferred.promise;
    }

    // get forum by forumId
    function getForum(forumId) {
        var deferred = $q.defer();
        $http.get(url + 'get/' + forumId).
            then(function (response) {
                console.log(response.data);
                deferred.resolve(response.data);
            }, function (errResponse) {
                console.error('error fetching forum');
                deferred.reject(errResponse);
            });
        return deferred.promise;
    }

    //create comment
    function createForumComment(fc) {
        var deferred = $q.defer();
        $http.post(forumCommentUrl + 'create', fc).
            then(function (response) {
                console.log(response.data);
                deferred.resolve(response.data);
            }, function (errResponse) {
                deferred.reject(errResponse);
                console.error("Error creating comment in forum");
            });
        return deferred.promise;
    }

    //all forum comments by forum id
	function getforumComments(forumId) {
		var deferred = $q.defer();
		$http.get(forumCommentUrl + 'get/' + forumId).
			then(function (response) {
				deferred.resolve(response.data);
				console.log('fetched forum comments');
			}, function (errResponse) {
				deferred.reject(errResponse);
				console.error('error fetching forumComments');
			});
		return deferred.promise;
	}

}]);