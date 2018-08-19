(function () {
    'use strict';

    angular
        .module('app')
        .factory('UserService', UserService);

    UserService.$inject = ['$http'];
    function UserService($http) {
        var service = {};

        service.getDepts = getDepts;
        service.GetById = GetById;
        service.GetByUsername = GetByUsername;
        service.Create = Create;
        service.Update = Update;
        service.Delete = Delete;

        return service;

        function getDepts() {
            return $http({
                url: '/api/depts',
                method: "GET"
            }).then(handleSuccess, handleError('Lista departamentelor nu poate fi incarcata'));
        }

        function Create(user, perso) {
        	return $http({
                url: '/register',
                method: "POST",
                data: {appUser:user, appPerso:perso}
            }).then(handleSuccess, handleError('Eroare la crearea utilizatorului'));
        }

        function GetById(id) {
            return $http.get('/api/users/' + id).then(handleSuccess, handleError('Error getting user by id'));
        }

        function GetByUsername(username) {
            return $http({
                url: '/api/users/' + username,
                method: "POST",
                params: {
                    username: username
                }
            }).then(handleSuccess, handleError('Error getting user by username'));
        }
     
        function Update(user) {
            return $http.put('/api/users/' + user.id, user).then(handleSuccess, handleError('Error updating user'));
        }

        function Delete(id) {
            return $http.delete('/api/users/' + id).then(handleSuccess, handleError('Error deleting user'));
        }

        // private functions

        function handleSuccess(res) {
            return res.data;
        }

        function handleError(error) {
            return function () {
                return { success: false, message: error };
            };
        }
    }

})();
