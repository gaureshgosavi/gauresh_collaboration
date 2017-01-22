
ForurmModule.controller('ForumController', ['ForumFactory', '$http', '$scope', function (ForumFactory, $http, $scope) {

	var self = this;
	self.forums = [];
	self.forum = { forumId: '', userId: '', name: '', categoryId: '', status: '' };

	self.submit = submit;
	self.edit = edit;
	self.remove = remove;
	self.reset = reset;
	self.getForum = getForum;
	self.createForum = createForum;
	self.updateForum = updateForum;
	self.deleteForum = deleteForum;

	FetchAllForums();


	function FetchAllForums() {
		ForumFactory
			.then(function (d) {
				self.forums = d;
			}, function (errResponse) {
				console.error('Error while fetching the forums');
			})
	}

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
		console.log($rootScope.userId);
		self.forum.userId = $rootScope.userId;
		ForumFactory.createForum(forum)
			.then(
			function (d) {
				self.forum = d;
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
			console.log('Saving New forum', self.forum);
			createforum(self.forum);
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