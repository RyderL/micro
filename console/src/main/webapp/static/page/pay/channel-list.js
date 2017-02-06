app.register.controller('PayChannelListController', function($scope, $http, $rootScope) {
    $(".nav-sidebar li").removeClass("active");
    $("#sidebar-pay-channel-list").addClass("active");

    $scope.channel = {};

    $scope.list = function () {
        $http.post("pay/channel/list").success(function(response){
            $scope.channels = response.content;
        }).error(function(){
            alert("系统错误");
        });
    };

    $scope.list();

});