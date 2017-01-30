ForumCommentModule.controller('ForumCommentController', ['$rootScope', '$scope', '$http', '$routeParams', 'ForumFactory', 'ForumCommentFactory', function ($rootScope, $scope, $http, $routeParams, ForumFactory, ForumCommentFactory) {

    var self = this;
    self.singleForum = {};
	self.user = [];
	self.forumRequest = [];
    self.forumComment = {id: null, userId: '', forumComment: ''};

    self.submit = submit;
    self.reset = reset;

	getForum();
	getForumRequest();
	getForumMember();
  
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
				self.forumRequest = d;
				console.log('Request'+self.forumRequest);
				getForum();
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
				self.user = d;
				console.log('member'+self.user);
			},
			function (errResponse) {
				console.error('error while fetching blog.')
			}
			);
	}

    
    function createforumComment(forumComment, forumId) {
        ForumCommentFactory.createforumComment(forumComment, forumId)
            .then(
            function (d) {
                self.forumComment = d;
				getForum();
				console.log(d);
            },
            function (errResponse) {
                console.error('Error while creating forumComment');
            }
            );
    }

    function submit() {
        if (self.forumComment.id == '' || self.forumComment.id == undefined) {
            console.log('Saving New forumComment', self.forumComment);
			self.forumComment.userId = $rootScope.userId;
			console.log($rootScope.userId);
            console.log(self.singleForum.forum.forumId);
			createforumComment(self.forumComment, self.singleForum.forum.forumId);
        }
    }


    function reset() {
        self.forumComment = { id: null, userId: '', forumComment: '' };
        $scope.forumCommentForm.$setPristine(); //reset Form
    }

}]);
