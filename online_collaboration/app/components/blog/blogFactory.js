app.factory('BlogFactory', ['$http', '$q', function ($http, $q) {

	var blogUrl = "http://localhost:8090/collaboration_backend//blog/";

	var blogCommentUrl = "http://localhost:8090/collaboration_backend//blogComment/";

	var factory = {
		fetchAllBlogs: fetchAllBlogs,
		createBlog: createBlog,
		updateBlog: updateBlog,
		deleteBlog: deleteBlog,
		getBlogComments: getBlogComments,
		createBlogComment: createBlogComment,
		getBlog: getBlog,
		deleteBlogComment: deleteBlogComment
	};

	return factory;

	// get all blogs
	function fetchAllBlogs() {
		var deferred = $q.defer();

		$http.get(blogUrl + 'list')
			.then(function (response) {
				$log.info(response);
				deferred.resolve(response.data);
			}, function (errResponse) {
				console.error('Error while fetching blogs!');
				deferred.reject(errResponse);
			});

		return deferred.promise;
	}

	// get blog by blog id
	function getBlog(blogId) {

		var deferred = $q.defer();
		
		$log.info($q);
		$http.get(address + 'get/' + blogId)
			.then(function (response) {
				$log.info(response);
				deferred.resolve(response.data);
			},
			function (errResponse) {
				console.error('Error getting blog');
				deferred.reject(errResponse);
			});
		return deferred.promise;
	}


	//create blog
	function createBlog(blog) {

		var deferred = $q.defer();

		$http.post(address + 'create', blog)
			.then(
			function (response) {
				$log.info(response);
				deferred.resolve(response.data);
			},
			function (errResponse) {
				console.error('Error adding blog');
				deferred.reject(errResponse);
			});
		return deferred.promise;
	}


	//update blog
	function updateBlog(blog, blogId) {
		var deferred = $q.defer();
		$http.put(address + 'get/' + blogId, blog)
			.then(function (response) {
				$log.info(response);
				deferred.resolve(response.data);
			}, function (errResponse) {
				console.error('Error updating blog');
				deferred.reject(errResponse);
			});

		return deferred.promise;
	}


	// delete blog
	function deleteBlog(blogId) {
		var deferred = $q.defer();
		$http.delete(address + 'delete/' + blogId)
			.then(function (response) {
				$log.info(response);
				deferred.resolve(response.data);
			}, function (errResponse) {
				console.error('Error deleting blog');
				deferred.reject(errResponse);
			});
		return deferred.promise;
	}


	//all blog comments by blog id
	function getBlogComments(blogId) {
		var deferred = $q.defer();
		$http.get(blogCommentUrl + 'get/' + blogId).
			then(function (response) {
				deferred.resolve(response.data);
				console.log('fetched blogs comments');
			}, function (errResponse) {
				deferred.reject(errResponse);
				console.error('error fetching blogcomments');
			});
		return deferred.promise;
	}


	//create blog comment
	function createBlogComment(bc) {
		var deferred = $q.defer();
		$http.post(blogCommentUrl + 'create', bc).
			then(function (response) {
				deferred.resolve(response.data);
				console.log('created blog comment');
			}, function (errResponse) {
				deferred.reject(errResponse);
				console.error('error creating blogComment');
			});
		return deferred.promise;
	}

	//delete blog comment
	function deleteBlogComment(blogCommentId) {
		var deferred = $q.defer();
		$http.delete(blogCommentUrl + 'delete/' + blogCommentId).
			then(function (response) {
				deferred.resolve(response.data);
				console.log('deleted blog comment');
			}, function (errResponse) {
				deferred.reject(errResponse);
				console.error('error deleting blogcomment');
			});
		return deferred.promise;
	}


}]);