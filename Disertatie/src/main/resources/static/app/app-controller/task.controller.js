(function () {
    'use strict';

    angular
        .module('app')
        .controller('TaskController', TaskController);

    TaskController.$inject = ['UserService'];
    function TaskController(UserService) {
        var vm = this;
        
        vm.selectTask = function(value){
            vm.activeTask = value;
        }

        vm.isActive = function(value){
            if(vm.activeTask == value){
                return true;
            }
            else {
                return false;
            }
        }

        /*
        (function initController() {
            loadTasks();
            
        })();

        function loadTasks() {
            
        }*/

       
    }

})();