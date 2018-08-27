(function () {
    'use strict';

    angular
        .module('app')
        .controller('TaskController', TaskController);

    TaskController.$inject = ['UserService'];
    function TaskController(UserService) {
        var vm = this;
        

        (function initController() {
            loadTasks();
            
        })();

        function loadTasks() {
            
        }

       
    }

})();