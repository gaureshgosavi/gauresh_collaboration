ForumCommentModule.controller('ForumCommentController', ['ForumCommentFactory', '$http', '$scope', '$routeParams', function (ForumCommentFactory, $http, $scope, $routeParams) {

    var self = this;
    self.forumComments = [];
    self.forumComment = {id: null, userId: '', forumComment: ''};

    self.submit = submit;
    self.singleForum = singleForum;
    self.getForumRequest = getForumRequest;
    self.getForumMember = self.getForumMember;
    self.reset = reset;


    getForum();
  
	function getForum(forumId) {
		var getForumId=$routeParams.forumId;
		console.log(getForumId);

		ForumFactory.getForum(getForumId)
			.then(
			function (d) {
				self.singleForum = d;
				console.log(self.singleForum);
			},
			function (errResponse) {
				console.error('error while fetching blog.')
			}
			);
	}


    function getForumRequest(forumId) {
		var getForumId=$routeParams.forumId;
		console.log(getForumId);

		ForumFactory.getForum(getForumId)
			.then(
			function (d) {
				self.singleForum = d;
				console.log(self.singleForum);
			},
			function (errResponse) {
				console.error('error while fetching blog.')
			}
			);
	}

    function getForumMember(forumId) {
		var getForumId=$routeParams.forumId;
		console.log(getForumId);

		ForumFactory.getForum(getForumId)
			.then(
			function (d) {
				self.singleForum = d;
				console.log(self.singleForum);
			},
			function (errResponse) {
				console.error('error while fetching blog.')
			}
			);
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
