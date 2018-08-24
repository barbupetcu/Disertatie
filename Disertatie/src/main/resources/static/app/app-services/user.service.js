(function () {
    'use strict';

    angular
        .module('app')
        .factory('UserService', UserService);

    UserService.$inject = ['$http'];
    function UserService($http) {
        var service = {};

        service.getDepts = getDepts;
        service.GetUserById = GetUserById;
        service.EditUser = EditUser;
        service.Create = Create;
        service.ChangePassword = ChangePassword;
        


        return service;

        function getDepts() {
            return $http({
                url: '/api/depts',
                method: "GET"
            }).then(handleSuccess, handleError('Lista departamentelor nu poate fi incarcata'));
        }

        function Create(user) {
        	return $http({
                url: '/register',
                method: "POST",
                data: {appUser:user}
            }).then(handleSuccess, handleError('Eroare la crearea utilizatorului'));
        }

        function GetUserById(id) {
            return $http({
                url: '/api/user',
                method: "GET",
                params: {id: id}
            }).then(handleSuccess, handleError('Datele utilizatorului curent nu au putut fi incarcate'));
        }

        function EditUser(user) {
            return $http({
                url: '/api/editUser',
                method: "POST",
                data: {user: user}
            }).then(handleSuccess, handleError('Utilizatorul nu a putut fi modifcat'));
        }

        function ChangePassword(id, oldPw, newPw) {
            return $http({
                url: '/api/changepassword',
                method: "PUT",
                params: {id: id,
                    oldPw: oldPw,
                    newPw: newPw}
            }).then(handleSuccess, handleError('Utilizatorul nu a putut fi modifcat'));
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
