UserModule.controller('UserController', ['$scope', '$rootScope','UserFactory', 'AuthenticationFactory', 'UploadFactory','$routeParams', '$timeout', function ($scope, $rootScope,UserFactory, AuthenticationFactory, UploadFactory, $routeParams, $timeout) {
    var self = this;
    self.user = {};
    self.users = [];
    self.client = {};
    self.submit = submit;

    getUser = function () {
        debugger;
        getUserId = $rootScope.user.userId;
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

    self.picture = undefined;
    debugger;
    self.customer = AuthenticationFactory.loadUserFromCookie();
    console.log(self.customer);
    // the decached technique is used to see the updated image immediately with out page refresh
    self.customer.profileId = self.customer.profileId + '?decached=' + Math.random();

    // once the controller loads call the jQuery
    $timeout(function () {
        load();
    }, 100);

    // to upload the file    
    self.uploadFile = function () {
        if (self.picture == undefined) {
            return;
        }
        // self.picture will get the value from the directive created previously
        // it is bind to the HTML input  
        UploadFactory.uploadFile(self.picture)
            .then(
            function (response) {
                $rootScope.message = 'Profile picture updated successfully!';
                //message contains the profileId updated in the backend too
                self.customer.profileId = response.message + '?decached=' + Math.random();
                // update the controller user too
                $rootScope.user.profileId = response.message + '?decached=' + Math.random();
                // need to update the cookie value too
                AuthenticationFactory.saveUser($rootScope.user);
                debugger;
                console.log($rootScope.user);
                // hide the card panel by setting the rootScope.message as undefined
                $timeout(function () {
                    $rootScope.message = undefined;
                }, 2000);

            },
            function (error) {
                console.log(error);
            }
            )
    };

}]);

