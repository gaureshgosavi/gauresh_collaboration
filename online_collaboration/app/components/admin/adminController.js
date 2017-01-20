AdminModule.controller('AdminController', ['adminFactory', '$rootScope', function (adminFactory, $rootScope) {

    var self = this;
    self.blogs = [];
    self.blog = {};
    self.users = [];
    self.user = {};
    self.none = false;

    function getUnapprovedBlogs() {
        adminFactory.getUnapprovedBlogs().
            then(function (data) {
                console.log(data);
                self.blogs = data;
                if (self.blogs.length <= 0) {
                    self.none = true;
                }
                self.failed = false;
            }, function (errResponse) {
                console.error(errResponse);
                self.failed = true;
            });

    }

    getUnapprovedBlogs();


    self.approveBlog = function (blogId) {
        console.log(blogId);
        adminFactory.approveBlog(blogId)
            .then(function (data) {
                self.blog = data;
                self.failed = false;
                console.log('approval successful');
                getUnapprovedBlogs();
            }, function (errResponse) {
                console.error(errResponse);
                self.failed = true;
                getUnapprovedBlogs();
            });

    }

    self.disapproveBlog = function (blogId) {
        console.log(blogId);
        adminFactory.disapproveBlog(blogId)
            .then(function (data) {
                self.blog = data;
                self.failed = false;
                console.log('disapproval successful');
                getUnapprovedBlogs();
            }, function (errResponse) {
                console.error(errResponse);
                self.failed = true;
                getUnapprovedBlogs();
            });
    }

    function getUnapprovedUsers() {
        adminFactory.getUnapprovedUsers().
            then(function (data) {
                console.log(data);
                self.users = data;
                if (self.users.length <= 0) {
                    self.none = true;
                }
                self.failed = false;
            }, function (errResponse) {
                console.error(errResponse);
                self.failed = true;
            });

    }

    getUnapprovedUsers();


    self.approveUser = function (userId) {
        console.log(userId);
        adminFactory.approveUser(userId)
            .then(function (data) {
                self.user = data;
                self.failed = false;
                console.log('approval successful');
                getUnapprovedUsers();
            }, function (errResponse) {
                console.error(errResponse);
                self.failed = true;
                getUnapprovedUsers();
            });

    }

    self.disapproveUser = function (userId) {
        console.log(userId);
        adminFactory.disapproveUser(userId)
            .then(function (data) {
                self.user = data;
                self.failed = false;
                console.log('disapproval successful');
                getUnapprovedUsers();
            }, function (errResponse) {
                console.error(errResponse);
                self.failed = true;
                getUnapprovedUsers();
            });
    }

}]);
