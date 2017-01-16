app.controller('blogCommentController', ['blogCommentFactory', '$http', '$scope', function (blogCommentFactory, $http, $scope) {

	var self = this;
	self.blogComments = [];
	self.blogComment = { id: null, userId: '', blogComment: '' };

	self.submit = submit;
	//self.edit = edit;
	self.remove = remove;
	self.reset = reset;

	getBlogComments();


	function getBlogComments() {
		blogCommentFactory
			.getBlogComments()
			.then(function (d) {
				self.blogComments = d;
			}, function (errResponse) {
				console.error('Error while fetching the blogComments');
			})
	}


	function createBlogComment(blogComment) {
		blogCommentFactory.createBlogComment(blogComment)
			.then(
			getBlogComments,
			function (d) {
				self.blogComment = d;
			},
			function (errResponse) {
				console.error('Error while creating blogComment');
			}
			);
	}


	function deleteBlogComment(id) {
		blogCommentFactory.deleteBlogComment(id)
			.then(
			getBlogComments,
			function (d) {
				self.blogComment = d;
			},
			function (errResponse) {
				console.error('Error while deleting User');
			}
			);
	}

	function submit() {
		if (self.blogComment.id == '' || self.blogComment.id == undefined) {
			console.log('Saving New blogComment', self.blogComment);
			createblogComment(self.blogComment);
		} 
		reset();
	}


	function remove(id) {
		console.log('id to be deleted', id);
		if (self.blogComment.id === id) {
			reset();
		}
		deleteBlogComment(id);
	}

	function reset() {
		self.blogComment = { id: null, userId: '', blogComment: '' };
		$scope.blogCommentForm.$setPristine(); //reset Form
	}

}]);