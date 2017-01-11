app.controller('BlogController', ['BlogFactory','$http', function(BlogFactory, $http){
	
	var self = this;
	self.blogs = [];

	fetchAllBlogs();


	function fetchAllBlogs () {
		BlogFactory
		.fetchAllBlogs()
	    .then(function(d){
	   		self.blogs = d;
		 },function (errResponse) {
			console.error('Error while fetching the blogs');
		})
	}

	
}]);