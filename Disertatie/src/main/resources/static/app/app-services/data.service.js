(function () {
    'use strict';

    angular
        .module('app')
        .factory('DataService', DataService);

    function DataService() {
        var selectedUser = null;
  
        return {
    
            getSelectedId: function() {
                return selectedUser;
            },
            setSelectedId: function(userId) {
                selectedUser = userId;
            }
        };
    }
})();