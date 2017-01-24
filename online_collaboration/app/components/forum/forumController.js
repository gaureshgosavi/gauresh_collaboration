
ForurmModule.controller('ForumController', ['ForumFactory', '$http', '$scope', '$rootScope', function (ForumFactory, $http, $scope, $rootScope) {

	var self = this;
	self.forums = [];
	self.forum = { forumId: undefined, userId: '', name: '' };

	self.submit = submit;
	self.edit = edit;
	self.remove = remove;
	self.reset = reset;
	self.getForum = getForum;
	self.createForum = createForum;
	self.updateForum = updateForum;
	self.deleteForum = deleteForum;

	function FetchAllForums() {
		ForumFactory.fetchAllForums()
			.then(function (d) {
				self.forums = d;
				console.log(self.forums)
			}, function (errResponse) {
				console.error('Error while fetching the forums');
			});
	}

	FetchAllForums();

	function getForum(forumId) {
		ForumFactory.getForum(forumId)
			.then(
			function (d) {
				self.forum = d;
			},
			function (errResponse) {
				console.error('error while fetching forum.')
			}
			);

	}

	function createForum(forum) {
		ForumFactory.createForum(forum)
			.then(
			function (d) {
				self.forum = d;
				console.log(self.forum)
			},
			function (errResponse) {
				console.error('Error while creating forum');
			}
			);
	}

	function updateForum(forum, forumId) {
		ForumFactory.updateForum(forum, forumId)
			.then(
			function (d) {
				self.forum = d;
			},
			function (errResponse) {
				console.error('Error while updating forum');
			}
			);
	}

	function deleteForum(forumId) {
		ForumFactory.deleteForum(forumId)
			.then(
			function (d) {
				self.forum = d;
			},
			function (errResponse) {
				console.error('Error while deleting forum');
			}
			);
	}

	function submit() {
		if (self.forum.forumId == '' || self.forum.forumId == undefined) {
			console.log($rootScope.userId);
			self.forum.userId = $rootScope.userId;
			console.log('Saving New forum', self.forum);
			createForum(self.forum);
		} else {
			updateforum(self.forum, self.forum.forumId);
			console.log('forum updated with id ', self.forum.forumId);
		}
	}

	function edit(forumId) {
		console.log('id to be edited', forumId);
		for (var i = 0; i < self.forums.length; i++) {
			if (self.forums[i].forumId === forumId) {
				self.forum = angular.copy(self.forums[i]);
				break;
			}
		}
	}

	function remove(forumId) {
		console.log('id to be deleted', forumId);
		if (self.forum.forumId === forumId) {
			reset();
		}
		deleteforum(forumId);
	}

	function reset() {
		self.forum = { forumId: '', userId: '', name: '', categoryId: '', status: '' };
		$scope.forumForm.$setPristine(); //reset Form
	}

}]);