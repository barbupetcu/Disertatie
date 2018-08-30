(function () {
    'use strict';

    angular
        .module('app', ['ngRoute', 'ngCookies', 'ui.bootstrap', 'ngMaterial'])
        .config(config)
        .run(run);

    config.$inject = ['$routeProvider', '$locationProvider'];
    function config($routeProvider, $locationProvider) {
        $routeProvider
            .when('/', {
                controller: 'HomeController',
                templateUrl: 'app/app-deploy/home/ROLE_USER/home.view.html',
                controllerAs: 'vm'
            })
            .when('/homeManager', {
                controller: 'HomeControllerMan',
                templateUrl: 'app/app-deploy/home/ROLE_MANAGER/home.view.html',
                controllerAs: 'vm'
            })

            .when('/editUser', {
                controller: 'EditUserController',
                templateUrl: 'app/app-deploy/users/editUser.view.html',
                controllerAs: 'vm'
            })

            .when('/approveUser', {
                templateUrl: 'app/app-deploy/users/approveUser.view.html'
            })

            .when('/task', {
                templateUrl: 'app/app-deploy/task/task.view.html'
            })

            .when('/taskIteration', {
                templateUrl: 'app/app-deploy/task/taskIteration.view.html'
            })

            .when('/descTask', {
                templateUrl: 'app/app-deploy/task/descTask.view.html'
            })

            .when('/descTask2', {
                templateUrl: 'app/app-deploy/task/descTask2.view.html'
            })

            .when('/login', {
                controller: 'LoginController',
                templateUrl: 'app/login/login.view.html',
                controllerAs: 'vm'
            })

            .when('/register', {
                controller: 'RegisterController',
                templateUrl: 'app/register/register.view.html',
                controllerAs: 'vm'
            })

            .otherwise({ redirectTo: '/login' });
    }

    run.$inject = ['$rootScope', '$location', '$cookies', '$http'];
    function run($rootScope, $location, $cookies, $http) {
        // keep user logged in after page refresh
        $rootScope.globals = $cookies.getObject('globals') || {};
        if ($rootScope.globals.currentUser) {
            $http.defaults.headers.common['Authorization'] = 'Bearer ' + $rootScope.globals.currentUser.authdata;
        }

        $rootScope.$on('$locationChangeStart', function (event, next, current) {
            // redirect to login page if not logged in and trying to access a restricted page
            $rootScope.isRestrictedPage = $.inArray($location.path(), ['/login', '/register']) === -1;

            var restrictedPage = $.inArray($location.path(), ['/login', '/register']) === -1;
            var loggedIn = $rootScope.globals.currentUser;
            if (restrictedPage && !loggedIn) {
                $location.path('/login');
            }

            if ($rootScope.globals.currentUser=== undefined) {
                $rootScope.globals.currentUser ={};
            }

            if(!restrictedPage && $rootScope.globals.currentUser.roles!= undefined){
                if ($rootScope.globals.currentUser.roles.indexOf("ROLE_MANAGER")>=0 && $location.path()==="/"){
                    $location.path('/homeManager');
                }
            }
            

            $rootScope.isActiveNavBar = function (viewLocation) {
                var active = (viewLocation === $location.path());
                return active;
            };

        });
    }

})();