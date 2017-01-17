AuthenticationModule.controller('AuthenticationController', ['AuthenticationFactory', '$rootScope', '$location', function (AuthenticationFactory, $rootScope, $location) {
    var self = this;
    self.credentials = {};
    self.error = false;

    self.login = function () {
        AuthenticationFactory.login(self.credentials)
            .then(
            function (user) {
                AuthenticationFactory.setUserIsAuthenticated(true);
                AuthenticationFactory.setRole(user.role);
                $rootScope.authenticated = true;
                $rootScope.message = 'Welcome ' + user.firstName;

                switch (user.role) {
                    case 'ADMIN':
                        $location.path('/admin/home');
                        break;
                    case 'USER':
                        $location.path('/user/home');
                        break;
                    default:
                        $location.path('/error/403');
                }
            },
            function (errorResponse) {
                console.log(errorResponse);
                AuthenticationFactory.setUserIsAuthenticated(false);
                $rootScope.authenticated = false;
                self.error = true;
            }
            )
    }
}]);