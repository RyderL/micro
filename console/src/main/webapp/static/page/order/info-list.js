app.register.controller('OrderInfoListController', function($scope, $http, $rootScope) {
    $(".nav-sidebar li").removeClass("active");
    $("#sidebar-order-info-list").addClass("active");

    $scope.order = {};

    $scope.list = function () {
        $http.post("order/info/list").success(function(response){
            $scope.orders = response.content;
        }).error(function(){
            alert("系统错误");
        });
    };

    $scope.add = function () {
        $http.post("order/info/add", $scope.order).success(function(response){
            if (response.success) {
                $scope.orders.push(response.content);
                $("#order-modal").modal("hide");
            } else {
                alert(response.message);
            }
        }).error(function(){
            alert("系统错误");
        });
    };

    $scope.addAndPay = function () {
        $http.post("order/info/addAndPay", $scope.order).success(function(response){
            if (response.success) {
                $scope.orders.push(response.content);
                $("#order-modal").modal("hide");
            } else {
                alert(response.message);
            }
        }).error(function(){
            alert("系统错误");
        });
    };

    /**
     *
     * @param order
     */
    $scope.delete = function (order) {
        $http.post("order/info/delete/" + order.id).success(function(response){
            if (response.success) {
                $scope.orders.removeObj(order);
            } else {
                alert(response.message);
            }
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
    $scope.list();
    $scope.listPayChannel();

});