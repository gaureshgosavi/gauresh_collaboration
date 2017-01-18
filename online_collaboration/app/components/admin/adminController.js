app.controller('adminController', ['adminFactory', '$rootScope', function (adminFactory, $rootScope) {

    var self = this;
    self.blogs = [];
    self.blog = {};
    self.none = false;

    function getUnapprovedBlogs() {
        adminFactory.getUnapprovedBlogs().
            then(function (data) {

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
        getUnapprovedBlogs;
        var length = self.blogs.length;
        for (var i = 0; i < length; i++) {
            if (self.blogs[i].blog.blogId == blogId) {
                self.blog = angular.copy(self.blogs[i]);
                break;
            }
        }
        adminFactory.approveBlog(blogId)
            .then(function (data) {
                self.blog = data;
                self.failed = false;
                console.log('approval successful')
            }, function (errResponse) {
                console.error(errResponse);
                self.failed = true;
            });

    }

    self.disapproveBlog = function (blogId) {
        getUnapprovedBlogs;
        var length = self.blogs.length;
        for (var i = 0; i < length; i++) {
            if (self.blogs[i].blog.blogId == blogId) {
                self.blog = angular.copy(self.blogs[i]);
                break;
            }

        }
        adminFactory.disapproveBlog(blogId)
            .then(function (data) {
                self.blog = data;
                self.failed = false;
                console.log('disapproval successful')
            }, function (errResponse) {
                console.error(errResponse);
                self.failed = true;
            });
    }

    function getUnapprovedUsers() {
        adminFactory.getUnapprovedUsers().
            then(function (data) {

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
        getUnapprovedUsers;
        var length = self.users.length;
        for (var i = 0; i < length; i++) {
            if (self.users[i].user.userId == userId) {
                self.user = angular.copy(self.users[i]);
                break;
            }
        }
        adminFactory.approveUser(userId)
            .then(function (data) {
                self.user = data;
                self.failed = false;
                console.log('approval successful')
            }, function (errResponse) {
                console.error(errResponse);
                self.failed = true;
            });

    }

    self.disapproveUser = function (userId) {
        getUnapprovedUsers;
        var length = self.users.length;
        for (var i = 0; i < length; i++) {
            if (self.users[i].user.userId == userId) {
                self.user = angular.copy(self.users[i]);
                break;
            }

        }
        adminFactory.disapproveUser(userId)
            .then(function (data) {
                self.user = data;
                self.failed = false;
                console.log('disapproval successful')
            }, function (errResponse) {
                console.error(errResponse);
                self.failed = true;
            });
    }

}]);
