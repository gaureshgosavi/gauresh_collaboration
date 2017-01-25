var ForumCommentModule = angular.module('ForumCommentModule', []);

ForumCommentModule.factory('ForumCommentFactory', ['$http', '$q', function ($http, $q) {

    var url = "http://localhost:8090/collaboration_backend//forumComment/"

    var factory = {
        getforumComments: getforumComments,
        createforumComment: createforumComment,
        deleteforumComment: deleteforumComment
    };

    return factory;

    //all forum comments by forum id
    function getforumComments(forumId) {
        var deferred = $q.defer();
        $http.get(url + 'get/' + forumId).
            then(function (response) {
                deferred.resolve(response.data);
                console.log('fetched forums comments');
            }, function (errResponse) {
                deferred.reject(errResponse);
                console.error('error fetching forumcomments');
            });
    }

    //create forum comment
    function createforumComment(forumId) {
        var deferred = $q.defer();
        $http.post(url + 'create' + forumId).
            then(function (response) {
                deferred.resolve(response.data);
                console.log('created forum comment');
            }, function (errResponse) {
                deferred.reject(errResponse);
                console.error('error creating forumcomments');
            });
    }

}]);