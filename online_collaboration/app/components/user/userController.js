myApp.controller('UserController', ['$scope', 'userFactory', function ($scope, userFactory) {
    var self = this;
    self.user = { id: undefined, firstName: '', lastName: '', username: '', email: '', password: '', confirmPassword: '', role:'', gender: ''};
    self.users = [];

    self.submit = submit;
    self.edit = edit;
    //self.remove = remove;
    self.reset = reset;

    fetchAllUsers();

    function fetchAllUsers() {
        userFactory.fetchAllUsers()
            .then(
            function (d) {
                self.users = d;
            },
            function (errResponse) {
                console.error('Error while fetching Users');
            }
            );
    }


    function createUser(user) {
        userFactory.createUser(user)
            .then(
            fetchAllUsers,
            function (d) {
                self.user = d;
            },
            function (errResponse) {
                console.error('Error while creating User');
            }
            );
    }

    function updateUser(user, id) {
        userFactory.updateUser(user, id)
            .then(
            fetchAllUsers,
            function (d) {
                self.user = d;
            },
            function (errResponse) {
                console.error('Error while updating User');
            }
            );
    }
    function submit() {
        if (self.user.id == '' || self.user.id == undefined) {
            console.log('Saving New User', self.user);
            createUser(self.user);
        } else {
            updateUser(self.user, self.user.id);
            console.log('User updated with id ', self.user.id);
        }
        reset();
    }

    function edit(id) {
        console.log('id to be edited', id);
        for (var i = 0; i < self.users.length; i++) {
            if (self.users[i].id === id) {
                self.user = angular.copy(self.users[i]);
                break;
            }
        }
    }

    function reset() {
        self.user = { id: null, firstName: '', lastName: '', username: '', email: '', password: '', confirmPassword: '', role:'', status:'', isOnline:'' };
        $scope.userForm.$setPristine(); //reset Form
    }

}]);

