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
        service.LoadDisabledUsers = LoadDisabledUsers;
        service.DeleteUser = DeleteUser;
        service.getPriority = getPriority;
        service.getTeamUsers = getTeamUsers;
        service.addTask = addTask;
        service.getTask = getTask;
        service.getTasks = getTasks;
        service.sendTasks = sendTasks;
        service.getDifficulty=getDifficulty;
        service.getIteration=getIteration;
        service.getTasksByDept=getTasksByDept;
        
        
        return service;

        function sendTasks(tasks) {
            return $http.put('/api/sendtasks', JSON.stringify(tasks)).then(handleSuccessWithFlag, handleError('Eroare la crearea task-ului'));
        }

        function getTasks(sprintId) {
            return $http({
                url: '/api/gettasks',
                method: "GET",
                params: {sprintId: sprintId}
            }).then(handleSuccessWithFlag, handleError('Task-urile nu au putut fi incarcate'));
        }

        function getTasksByDept(deptId) {
            return $http({
                url: '/api/gettasksByDept',
                method: "GET",
                params: {deptId: deptId}
            }).then(handleSuccessWithFlag, handleError('Task-urile nu au putut fi incarcate'));
        }

        function getTask(taskId) {
            return $http({
                url: '/api/gettask',
                method: "GET",
                params: {id: taskId}
            }).then(handleSuccess, handleError('Detaliile task-ului nu au putut fi incarcate'));
        }

        function addTask(task) {
        	return $http.post('/api/addtask', JSON.stringify(task)).then(handleSuccess, handleError('Eroare la crearea task-ului'));
        }

        function getPriority() {
            return $http({
                url: '/api/priority',
                method: "GET"
            }).then(handleSuccess, handleError('Lista prioritatilor nu poate fi incarcata'));
        }

        function getDifficulty() {
            return $http({
                url: '/api/difficulty',
                method: "GET"
            }).then(handleSuccess, handleError('Lista dificultatilor nu poate fi incarcata'));
        }

        function getIteration() {
            return $http({
                url: '/api/iteration',
                method: "GET"
            }).then(handleSuccess, handleError('Lista iteratiilor nu poate fi incarcata'));
        }

        function getTeamUsers(dept) {
            return $http({
                url: '/api/getusers',
                method: "GET",
                params: {dept: dept}
            }).then(handleSuccess, handleError('Utilizatorii nu a putut fi incarcati'));
        }


        function getDepts() {
            return $http({
                url: '/api/depts',
                method: "GET"
            }).then(handleSuccess, handleError('Lista departamentelor nu poate fi incarcata'));
        }

        function Create(user) {
        	return $http.post('/register', JSON.stringify(user)).then(handleSuccess, handleError('Eroare la crearea utilizatorului'));
        }

        function GetUserById(id) {
            return $http({
                url: '/api/user',
                method: "GET",
                params: {id: id}
            }).then(handleSuccess, handleError('Datele utilizatorului curent nu au putut fi incarcate'));
        }

        function EditUser(user) {
            
            return $http.put('/api/editUser', JSON.stringify(user)).then(handleSuccess, handleError('Utilizatorul nu a putut fi modifcat'));
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

        function LoadDisabledUsers(dept) {
            return $http({
                url: '/api/disabledUsers', 
                method:"GET",
                params: {dept: dept}
            }).then(handleSuccess, handleError('Eroare la incarcarea utilizatorilor neactivati'));
        }

        function DeleteUser(id) {
            return $http({
                url: '/api/deleteUser',
                method: "DELETE",
                params: {id: id}
            })
                .then(handleSuccess, handleError('Utilizatorul nu a putut fi sters'));
        }



        

        function handleSuccessWithFlag(res) {
            return {success:true, data: res.data};
        }

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
