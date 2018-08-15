(function () {
    'use strict';

    angular
        .module('app')
        .controller('RegisterController', RegisterController);

    RegisterController.$inject = ['UserService', '$location', '$rootScope', 'FlashService'];
    function RegisterController(UserService, $location, $rootScope, FlashService) {
        var vm = this;
        
        vm.depts = [];

        vm.loadDept = function(){
            if(vm.depts.length ==0){
                vm.depts =[{id: '1', name: 'Action 1'},
                {id: '2', name: 'Action 2'},
                {id: '3', name: 'Action 3'}
            ];
            }
        }
        
        vm.register = register;

        function register() {
            vm.dataLoading = true;
            UserService.Create(vm.user, vm.perso)
                .then(function (response) {
                    if (response.success) {
                        FlashService.Success('Ingistrarea a fost efectuata cu succes', true);
                        $location.path('/login');
                    } else {
                        FlashService.Error(response.message);
                        vm.dataLoading = false;
                    }
                });
        }
    }

})();
