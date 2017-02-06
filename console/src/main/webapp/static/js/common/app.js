var app = angular.module("app", ["oc.lazyLoad", "ui.router", "ngRoute", "ngAnimate"]);
//常量  基本页面路径
app.constant("BASE_PATH", "static/page");
app.constant("BASE_JS_PATH", "static/page");
app.parseURL = function (url) {
    var items = url.split("/"), menu = {one_code: null, two_code: null, three_code: null, path: null};
    menu.one_code = items[1];
    menu.two_code = items[2];
    menu.three_code = items[3];
    menu.path = url;
    return menu;
};
app.config(function ($stateProvider, BASE_PATH, BASE_JS_PATH, $controllerProvider, $filterProvider, $urlRouterProvider, $provide) {
    app.register = {
        controller: $controllerProvider.register,
        filter: $filterProvider.register,
        factory: $provide.factory
    };
    /**
     * 根据一定的规则取出依赖
     * abc/def/hg.html 以hg为依赖
     * @param url
     */
    function getDeps(url) {
        var dep = null;
        if (url) {
            dep = app.parseURL(url).path;
        }
        return dep;
    }

    //处理url,添加后缀
    var suffix = ".html";

    function addSuffix(url) {
        if (url.indexOf(".") !== -1) {
            return url;
        }
        var index = url.indexOf("?");
        if (index === -1) {
            return url + suffix;
        } else {
            return url.substring(0, index) + suffix + url.substring(index);
        }
    }
    $urlRouterProvider.when(/^\/?$/, "/welcome");
    $stateProvider
        //默认规则配置  url: #/test
        .state("def", {
            url: "{url:[^@]*}",
            templateUrl: function ($stateParams) {
                return addSuffix(BASE_PATH + $stateParams.url) + "?datestamp=" + (new Date()).getTime();
            },
            resolve: {
                require: function ($ocLazyLoad, $stateParams) {
                    return $ocLazyLoad.load({
                        name: "app",
                        files: [BASE_JS_PATH + getDeps($stateParams.url) + ".js" + "?datestamp=" + (new Date()).getTime()]
                    });
                }
            }
        })
        //子路由 url: #/test@welcome
        .state("def.child", {
            url: "@{path:.+}",
            templateUrl: function ($stateParams) {
                var url = BASE_PATH + $stateParams.url + "/" + $stateParams.path + "?datestamp=" + (new Date()).getTime();
                return addSuffix(url);
            },
            resolve: {
                require: function ($ocLazyLoad, $stateParams) {
                    return $ocLazyLoad.load({
                        name: "app",
                        files: [BASE_JS_PATH + $stateParams.url + "/" + $stateParams.path + ".js" + "?datestamp=" + (new Date()).getTime()]
                    });
                }
            }
        });

    app.register.controller("RootController", function($scope, $http, $window, $rootScope) {

        $rootScope.loadEnum = function (enumName) {
            var result = [];
            $.ajax({
                async: false,
                url: "enum/" + enumName,
                type: "POST",
                success: function(response){
                    result = response;
                },
                error: function(){
                    alert("系统错误");
                }
            });
            return result;
        };
    });
});
angular.element(document).ready(function () {
    //阻止 # 导航
    $(document).delegate("a", "click", function (event) {
        var href = $(this).attr("href");
        if (href === "#") {
            event.preventDefault();
        }
    });
    //代替<html ng-app="app"
    angular.bootstrap(document, ["app"]);
});