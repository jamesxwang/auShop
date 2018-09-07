var app = angular.module("auShop", []);
app.controller('navIndexCtrl',function ($scope) {
    $scope.UserInfo = $.api.GetUserInfo();
    $scope.DoLogOut = function () {
        $.api.ClearAll();
        location.href = 'web/login.html'
    }
});
app.controller('navCtrl',function ($scope) {
    $scope.UserInfo = $.api.GetUserInfo();
    $scope.DoLogOut = function () {
        $.api.ClearAll();
        location.href = 'login.html'
    }
});

app.directive("navbarindex", function() {
    return {
        template : "<nav ng-controller='navIndexCtrl' class='navbar navbar-inverse navbar-fixed-top' role='navigation'>\n" +
        "  <div class='container'>\n" +
        "    <div class='navbar-header'>\n" +
        "      <button type='button' class='navbar-toggle' data-toggle='collapse' data-target='#navbar-ex1-collapse'>\n" +
        "        <span class='sr-only'>Toggle navigation</span>" +
        "        <span class='icon-bar'></span>\n" +
        "        <span class='icon-bar'></span>\n" +
        "        <span class='icon-bar'></span>\n" +
        "      </button>\n" +
        "      <a class='navbar-brand' href='index.html'>澳澳代购</a>\n" +
        "    </div>\n" +
        "   <div class='collapse navbar-collapse noPadding' id='navbar-ex1-collapse' role='navigation'>" +
        "     <ul class='nav navbar-nav'>\n" +
        "       <li><a href='https://cn-wx.github.io/about' target='_blank'>关于我们</a></li>\n" +
        "       <li><a href='mailto:xuwang2@student.unimelb.edu.au'>联系我们</a></li>\n" +
        "       <li><a href='#' onclick='$.api.Developing()'>网站地图</a></li>\n" +
        "     </ul>\n" +
        "\n" +
        "     <ul class='nav navbar-nav navbar-right'>\n" +
        "       <li>" +
        "        <form class='navbar-form'>" +
        "         <div class='form-group'>" +
        "          <input type='text' class='form-control' placeholder='请输入商品关键字查询' autocomplete='off'>" +
        "         </div>" +
        "         <a href='#'><button type='submit' class='btn btn-default' onclick='$.api.Developing()'>搜索</button></a>" +
        "         <div class='hidden'></div>" +
        "        </form>" +
        "       </li>" +
        "       <li><a ng-if='!UserInfo' href='web/login.html'>登录</a></li>\n" +
        "       <li><a ng-if='!UserInfo' href='web/register.html'>注册</a></li>\n" +
        "       <li><a ng-if='UserInfo' href='#' onclick='$.api.Developing()'>购物车</a></li>" +
        "       <li class='dropdown' ng-click='dropdown()'><a class='dropdown-toggle' role='button' aria-haspopup='true' aria-expanded='false' data-toggle='dropdown' ng-if='UserInfo'>欢迎您，{{UserInfo.name}}<span class='caret'></span></a>" +
        "       <ul class='dropdown-menu'>" +
        "         <li><a href='web/account.html'>账号信息</a></li>" +
        "         <li><a onclick='$.api.Developing()'>设置</a></li>" +
        "       </ul>" +
        "       </li>" +
        "       <li><a ng-if='UserInfo' href='#' ng-click='DoLogOut()'>退出登录</a></li>" +
        "     </ul>" +
        "   </div>\n" +
        "  </div>\n" +
        "</nav>\n"
    };
});

app.directive("navbar", function() {
    return {
        template : "<nav ng-controller='navCtrl' class='navbar navbar-inverse navbar-fixed-top' role='navigation'>\n" +
        "  <div class='container'>\n" +
        "    <div class='navbar-header'>\n" +
        "      <button type='button' class='navbar-toggle' data-toggle='collapse' data-target='#navbar-ex1-collapse'>\n" +
        "        <span class='sr-only'>Toggle navigation</span>" +
        "        <span class='icon-bar'></span>\n" +
        "        <span class='icon-bar'></span>\n" +
        "        <span class='icon-bar'></span>\n" +
        "      </button>\n" +
        "      <a class='navbar-brand' href='../index.html'>澳澳代购</a>\n" +
        "    </div>\n" +
        "   <div class='collapse navbar-collapse noPadding' id='navbar-ex1-collapse' role='navigation'>" +
        "     <ul class='nav navbar-nav'>\n" +
        "       <li><a href='https://cn-wx.github.io/about' target='_blank'>关于我们</a></li>\n" +
        "       <li><a href='mailto:xuwang2@student.unimelb.edu.au'>联系我们</a></li>\n" +
        "       <li><a href='#' onclick='$.api.Developing()'>网站地图</a></li>\n" +
        "     </ul>\n" +
        "\n" +
        "     <ul class='nav navbar-nav navbar-right'>\n" +
        "       <li>" +
        "        <form class='navbar-form'>" +
        "         <div class='form-group'>" +
        "          <input type='text' class='form-control' placeholder='请输入商品关键字查询' autocomplete='off'>" +
        "         </div>" +
        "         <a href='#'><button type='submit' class='btn btn-default' onclick='$.api.Developing()'>搜索</button></a>" +
        "         <div class='hidden'></div>" +
        "        </form>" +
        "       </li>" +
        "       <li><a ng-if='!UserInfo' href='login.html'>登录</a></li>\n" +
        "       <li><a ng-if='!UserInfo' href='register.html'>注册</a></li>\n" +
        "       <li><a ng-if='UserInfo' href='#' onclick='$.api.Developing()'>购物车</a></li>" +
        "       <li class='dropdown' ng-click='dropdown()'><a class='dropdown-toggle' role='button' aria-haspopup='true' aria-expanded='false' data-toggle='dropdown' ng-if='UserInfo'>欢迎您，{{UserInfo.name}}<span class='caret'></span></a>" +
        "       <ul class='dropdown-menu'>" +
        "         <li><a href='account.html'>账号信息</a></li>" +
        "         <li><a onclick='$.api.Developing()'>设置</a></li>" +
        "       </ul>" +
        "       </li>" +
        "       <li><a ng-if='UserInfo' href='#' ng-click='DoLogOut()'>退出登录</a></li>" +
        "     </ul>" +
        "   </div>\n" +
        "  </div>\n" +
        "</nav>\n"
    };
});

