(function () {
    'use strict';

    angular
        .module('app')
        .controller('IterationController', IterationController);

    IterationController.$inject = ['UserService', '_'];
    function IterationController(UserService, _) {

        var vm=this;
        //lista cu toate task-urile
        vm.tasksData = [];
        
        //listele de selectie
        vm.selectedA = [];
        vm.selectedB = [];
        vm.selectedC = [];
        //listele de task-uri divizate dupa status
        vm.listA = [];
        vm.listB = [];
        vm.listC = [];


        (function initController() {
            UserService.getTasks(1).then(function(response){
                    vm.tasksData = response;
                    splitTasks();
            });     
        })();

        function splitTasks(){
            vm.listA = vm.tasksData.filter(function (item){
                return item.status.id === 2;
            });
            vm.listB = vm.tasksData.filter(function (item){
                return item.status.id === 3;
            });
            vm.listC = vm.tasksData.filter(function (item){
                return item.status.id === 4;
            });
        };

        
        
        function arrayObjectIndexOf(myArray, searchTerm, property) {
            for(var i = 0;  i < myArray.length; i++) {
                if (myArray[i][property] === searchTerm) return i;
            }
            return -1;
        };
        
        vm.aToB = function() {
          for (var i = 0; i < vm.selectedA.length; i++) {
            var moveId = arrayObjectIndexOf(vm.tasksData, vm.selectedA[i], "id");
            vm.tasksData[moveId].status.id = 3;
            vm.listB.push(vm.tasksData[moveId]);
            var delId = arrayObjectIndexOf(vm.listA, vm.selectedA[i], "id"); 
            vm.listA.splice(delId,1);
          }
          reset();
          vm.dataModif=true;
        };
        
        vm.bToA = function() {
          for (var i = 0; i < vm.selectedB.length; i++) {
            var moveId = arrayObjectIndexOf(vm.tasksData, vm.selectedB[i], "id");
            vm.tasksData[moveId].status.id = 2;
            vm.listA.push(vm.tasksData[moveId]);
            var delId = arrayObjectIndexOf(vm.listB, vm.selectedB[i], "id"); 
            vm.listB.splice(delId,1);
          }
          reset();
          vm.dataModif=true;
        };

        vm.bToC = function() {
            for (var i = 0; i < vm.selectedB.length; i++) {
              var moveId = arrayObjectIndexOf(vm.tasksData, vm.selectedB[i], "id");
              vm.tasksData[moveId].status.id = 4;
              vm.listC.push(vm.tasksData[moveId]);
              var delId = arrayObjectIndexOf(vm.listB, vm.selectedB[i], "id"); 
              vm.listB.splice(delId,1);
            }
            reset();
            vm.dataModif=true;
        };

        vm.cToB = function () {
            for (var i = 0; i < vm.selectedC.length; i++) {
                var moveId = arrayObjectIndexOf(vm.tasksData, vm.selectedC[i], "id"); 
                vm.tasksData[moveId].status.id = 3;
                vm.listB.push(vm.tasksData[moveId]);
                var delId = arrayObjectIndexOf(vm.listC, vm.selectedC[i], "id"); 
                vm.listC.splice(delId,1);
            }
            reset();
            vm.dataModif=true;
        };
        
        function reset(){
            vm.selectedA=[];
            vm.selectedB=[];
            vm.selectedC=[];
        };

        vm.selectAll = function(selected, list){
            for (var i = 0; i < list.length; i++) {
                var id = list[i].id;
                if (!(selected.indexOf(id) > -1)){
                    selected.push(list[i].id);
                }
                
            }
        };
        
        vm.toggleSelectionA = function toggleSelectionA(id) {
            vm.selectedC=[];
            vm.selectedB=[];

            var idx = vm.selectedA.indexOf(id);
        
            // Este deja selectat
            if (idx > -1) {
                vm.selectedA.splice(idx, 1);
            }
        
            // este nou selectat
            else {
                vm.selectedA.push(id);
            }
        };

        vm.toggleSelectionB = function toggleSelectionB(id) {
            vm.selectedC=[];
            vm.selectedA=[];
            var idx = vm.selectedB.indexOf(id);
        
            // Este deja selectat
            if (idx > -1) {
                vm.selectedB.splice(idx, 1);
            }
        
            // este nou selectat
            else {
                vm.selectedB.push(id);
            }
        };

        vm.toggleSelectionC = function toggleSelectionC(id) {
            vm.selectedA=[];
            vm.selectedB=[];

            var idx = vm.selectedC.indexOf(id);
        
            // Este deja selectat
            if (idx > -1) {
                vm.selectedC.splice(idx, 1);
            }
        
            // este nou selectat
            else {
                vm.selectedC.push(id);
            }
        };

        vm.sendStatus = function sendStatus(){
            vm.dataLoading=true;
            UserService.sendTasks(vm.tasksData).then(function(response){
                if(response.success){
                    vm.tasksData = response.data;
                    splitTasks();
                    vm.dataLoading=false;
                    vm.dataModif=false;
                } else {
                    FlashService.Error(response.message);
                    vm.dataLoading=false;
                }
            });

            
        };
       
    }
})();