app.register.controller('PayInfoListController', function($scope, $http, $rootScope) {
    $(".nav-sidebar li").removeClass("active");
    $("#sidebar-pay-info-list").addClass("active");

    $scope.pay = {};

    $scope.list = function () {
        $http.post("pay/info/list").success(function(response){
            $scope.pays = response.content;
        }).error(function(){
            alert("系统错误");
        });
    };

    $scope.add = function () {
        $http.post("pay/info/add", $scope.pay).success(function(response){
            if (response.success) {
                $scope.pays.push(response.content);
                $("#pay-modal").modal("hide");
            } else {
                alert(response.message);
            }
        }).error(function(){
            alert("系统错误");
        });
    };


    /**
     *
     * @param pay
     */
    $scope.delete = function (pay) {
        $http.post("pay/info/delete/" + pay.id).success(function(response){
            if (response.success) {
                $scope.pays.removeObj(pay);
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