app.controller('BlogController', ['BlogFactory', '$http', '$scope', function (BlogFactory, $http, $scope) {

	var self = this;
	self.blogs = [];
	self.blog = { blogId: null, title: '', description: '', likes: '', status: '' };

	self.submit = submit;
	self.edit = edit;
	self.remove = remove;
	self.reset = reset;

	fetchAllBlogs();


	function fetchAllBlogs() {
		blogFactory
			.fetchAllBlogs()
			.then(function (d) {
				self.blogs = d;
			}, function (errResponse) {
				console.error('Error while fetching the blogs');
			})
	}

	function getBlog(blogId) {
		blogFactory.getBlog(blogId)
			.then(
			fetchAllBlogs,
			function (d) {
				self.blog = d;
			},
			function (errResponse) {
				console.error('error while fetching blog.')
			}
			);

	}


	function createBlog(blog) {
		blogFactory.createBlog(blog)
			.then(
			fetchAllBlogs,
			function (d) {
				self.blog = d;
			},
			function (errResponse) {
				console.error('Error while creating Blog');
			}
			);
	}

	function updateBlog(blog, blogId) {
		blogFactory.updateBlog(blog, blogId)
			.then(
			fetchAllBlogs,
			function (d) {
				self.blog = d;
			},
			function (errResponse) {
				console.error('Error while updating Blog');
			}
			);
	}

	function deleteBlog(blogId) {
		blogFactory.deleteBlog(blogId)
			.then(
			fetchAllUsers,
			function (d) {
				self.blog = d;
			},
			function (errResponse) {
				console.error('Error while deleting blog');
			}
			);
	}

	function submit() {
		if (self.blog.blogId == '' || self.blog.blogId == undefined) {
			console.log('Saving New Blog', self.blog);
			createBlog(self.Blog);
		} else {
			updateBlog(self.Blog, self.Blog.blogId);
			console.log('Blog updated with id ', self.Blog.blogId);
		}
		reset();
	}

	function edit(blogId) {
		console.log('id to be edited', blogId);
		for (var i = 0; i < self.Blogs.length; i++) {
			if (self.blogs[i].blogId === blogId) {
				self.blog = angular.copy(self.blogs[i]);
				break;
			}
		}
	}

	function remove(blogId) {
		console.log('id to be deleted', blogId);
		if (self.blog.blogId === blogId) {
			reset();
		}
		deleteBlog(blogId);
	}

	function reset() {
		self.blog = { blogId: null, title: '', description: '', likes: '', status: '' };
		$scope.blogForm.$setPristine(); //reset Form
	}

}]);