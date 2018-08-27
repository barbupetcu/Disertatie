(function () {
    'use strict';

    angular
        .module('app')
        .controller('HomeControllerMan', HomeControllerMan);

    HomeControllerMan.$inject = ['DataService', 'UserService', '$rootScope'];
    function HomeControllerMan(DataService, UserService, $rootScope) {
        var vm = this;
        vm.setSelectedId=setSelectedId;

        vm.disabledUsers = null;

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