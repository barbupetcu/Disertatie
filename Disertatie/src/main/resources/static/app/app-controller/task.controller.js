(function () {
    'use strict';

    angular
        .module('app')
        .controller('TaskController', TaskController);

    TaskController.$inject = ['UserService', '$rootScope', 'FlashService','$uibModal', 'DataService'];
    function TaskController(UserService, $rootScope, FlashService, $uibModal, DataService) {
        var vm = this;
        vm.activeTask = '';

        (function initController() {
            UserService.getTasksByDept($rootScope.globals.currentUser.dept).then(function(response){
                vm.tasksData = response.data;
            });
            
        })();
        
        vm.selectTask = function(value){
            vm.activeTask = value;
        };

        vm.isActive = function(value){
            if(vm.activeTask == value){
                return true;
            }
            else {
                return false;
            }
        };

        vm.saveTask = function(isEdit){
            if (isEdit){
                DataService.setActiveTask(vm.activeTask);
            }
            $uibModal.open({
                templateUrl: 'app/app-modal/task.adaugare.html',
                controller: 'AddTaskController',
                controllerAs: 'vm'
            }).result.then(function(){}, function(res){})
        };
    }

})();