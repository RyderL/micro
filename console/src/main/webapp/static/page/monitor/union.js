app.register.controller('MonitorUnionController', function($scope, $http, $rootScope) {
    $(".nav-sidebar li").removeClass("active");
    $("#sidebar-monitor-union").addClass("active");

    $scope.order = {};

    $scope.findOrderInfo = function () {
        $http.post("order/info/find", {
            orderNo: $scope.orderNo
        }).success(function(response){
            $scope.orders = response.content;
        }).error(function(){
            alert("系统错误");
        });
    };

    $scope.findPayInfo = function () {
        $http.post("pay/info/find", {
            orderNo: $scope.orderNo
        }).success(function(response){
            $scope.pays = response.content;
        }).error(function(){
            alert("系统错误");
        });
    };

    $scope.listPayChannel = function () {
        $http.post("pay/channel/list").success(function(response){
            $scope.channels = response.content;
        }).error(function(){
            alert("系统错误");
        });
    };

    $scope.orderStatus = $rootScope.loadEnum("orderStatus");
    $scope.listPayChannel();

    $scope.unionFind = function() {
        $scope.findOrderInfo();
        $scope.findPayInfo()
    }

});