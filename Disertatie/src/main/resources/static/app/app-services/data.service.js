(function () {
    'use strict';

    angular
        .module('app')
        .factory('DataService', DataService);

    function DataService() {
        var data = {
        };
  
        return {
    
            getSelectedId: function() {
                return localStorage.getItem('selectedUser');
            },
            setSelectedId: function(id) {
                localStorage.setItem('selectedUser', id);
            }
        };
    }
})();