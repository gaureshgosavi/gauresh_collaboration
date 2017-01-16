app.controller('forumCommentController', ['forumCommentFactory', '$http', '$scope', function (forumCommentFactory, $http, $scope) {

    var self = this;
    self.forumComments = [];
    self.forumComment = {id: null, userId: '', forumComment: ''};

    self.submit = submit;
    //self.edit = edit;
    //self.remove = remove;
    self.reset = reset;

    getforumComments();


    function getforumComments() {
        forumCommentFactory
            .getforumComments()
            .then(function (d) {
                self.forumComments = d;
            }, function (errResponse) {
                console.error('Error while fetching the forumComments');
            })
    }


    function createforumComment(forumComment) {
        forumCommentFactory.createforumComment(forumComment)
            .then(
            getforumComments,
            function (d) {
                self.forumComment = d;
            },
            function (errResponse) {
                console.error('Error while creating forumComment');
            }
            );
    }

    function submit() {
        if (self.forumComment.id == '' || self.forumComment.id == undefined) {
            console.log('Saving New forumComment', self.forumComment);
            createforumComment(self.forumComment);
        }
        reset();
    }


    function reset() {
        self.forumComment = { id: null, userId: '', forumComment: '' };
        $scope.forumCommentForm.$setPristine(); //reset Form
    }

}]);
