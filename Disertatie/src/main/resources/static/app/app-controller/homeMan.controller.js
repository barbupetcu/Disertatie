(function () {
    'use strict';

    angular
        .module('app')
        .controller('HomeControllerMan', HomeControllerMan);

    HomeControllerMan.$inject = ['DataService', 'UserService', '$rootScope', '$interval'];
    function HomeControllerMan(DataService, UserService, $rootScope,$interval) {
        var vm = this;
        vm.setSelectedId=setSelectedId;

        vm.disabledUsers = null;

        vm.maxValue = 90;
        vm.determinateValue = 60;

        vm.value = 60;
        
        /*
        $interval(function() {
            vm.determinateValue += 1;

            if (vm.determinateValue > vm.maxValue) {
                vm.determinateValue = 1;
            }
         }, 150, 0, true);
         */
        (function initController() {
            loadDisabledUsers();
            
        })();

        function loadDisabledUsers() {
            UserService.LoadDisabledUsers($rootScope.globals.currentUser.dept)
            .then(function (response){
                if(response){
                    vm.disabledUsers = response
                }
            });
        }

        function setSelectedId(selectedId) {
            DataService.setSelectedId(selectedId);
        }
    }

})();