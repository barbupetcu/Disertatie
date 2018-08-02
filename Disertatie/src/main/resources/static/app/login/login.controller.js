(function () {
    'use strict';

    angular
        .module('app')
        .controller('LoginController', LoginController);

    LoginController.$inject = ['$location', 'AuthenticationService', 'FlashService'];
    function LoginController($location, AuthenticationService, FlashService) {
        var vm = this;

        vm.login = login;

        (function initController() {
            // reset login status
            AuthenticationService.ClearCredentials();
        })();

        function login() {
            vm.dataLoading = true;
            AuthenticationService.Login(vm.username, vm.password, function (response) {
                if (response.data.success) {
                	var authdata = response.data.token
                    AuthenticationService.SetCredentials(vm.username, authdata);
                    $location.path('/');
                } else {
                    FlashService.Error(response.data.message);
                    vm.dataLoading = false;
                }
            });
        };
    }

})();
