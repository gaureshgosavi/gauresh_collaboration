UserModule.controller('UserController', ['$scope', 'UserFactory', 'AuthenticationFactory', '$routeParams',  function ($scope, UserFactory, AuthenticationFactory, $routeParams) {
    var self = this;
    self.user = {};
    self.users = [];
    self.client = {};
    self.submit = submit;

    getUser = function () {
        getUserId = $routeParams.userId;
        console.log(getUserId);
        UserFactory.getUser(getUserId)
            .then(
            function (d) {
                self.client = d;
                console.log(self.client);
            },
            function (errResponse) {
                console.error('Error while updating User');
            }
            );
    }

    getUser();

    function updateUser(user, userId) {
        UserFactory.updateUser(user, userId)
            .then(
            function (d) {
                self.user = d;
                AuthenticationFactory.saveUser(user);
                AuthenticationFactory.loadUserFromCookie();
                console.log(self.user);
            },
            function (errResponse) {
                console.error('Error while updating User');
            }
            );
    }
    function submit() {
        if (self.client.userId != '' || self.client.userId != undefined) {
            updateUser(self.client, self.client.userId);
            console.log('User updated with id ', self.client.userId);
        }
    }

}]);

