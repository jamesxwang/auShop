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
        template : "<nav ng-controller='navIndexCtrl' class='navbar navbar-inverse navbar-fixed-top' role='navigation'>" +
        "  <div class='container'>" +
        "    <div class='navbar-header'>" +
        "      <button type='button' class='navbar-toggle' data-toggle='collapse' data-target='#navbar-ex1-collapse'>" +
        "        <span class='sr-only'>Toggle navigation</span>" +
        "        <span class='icon-bar'></span>" +
        "        <span class='icon-bar'></span>" +
        "        <span class='icon-bar'></span>" +
        "      </button>" +
        "      <a class='navbar-brand' href='index.html'>澳澳代购</a>" +
        "    </div>" +
        "" +
        "    <div class='collapse navbar-collapse noPadding' id='navbar-ex1-collapse' role='navigation'>" +
        "      <ul class='nav navbar-nav'>" +
        "        <li><a href='https://cn-wx.github.io/about' target='_blank'>关于我们</a></li>" +
        "        <li><a href='mailto:xuwang2@student.unimelb.edu.au'>联系我们</a></li>" +
        "        <li><a href='#' onclick='$.api.Developing()'>网站地图</a></li>" +
        "      </ul>" +
        "      <ul class='nav navbar-nav navbar-right'>" +
        "        <li>" +
        "          <form class='navbar-form'>" +
        "            <div class='form-group'>" +
        "              <input type='text' class='form-control' placeholder='请输入商品关键字查询' autocomplete='off'>" +
        "            </div>" +
        "            <a href='#'><button type='submit' class='btn btn-default' onclick='$.api.Developing()'>搜索</button></a>" +
        "            <div class='hidden'></div>" +
        "          </form>" +
        "       </li>" +
        "       <li><a ng-if='!UserInfo' href='web/login.html'>登录</a></li>" +
        "       <li><a ng-if='!UserInfo' href='web/register.html'>注册</a></li>" +
        "       <li><a ng-if='UserInfo' href='#' onclick='$.api.Developing()'>购物车</a></li>" +
        "       <li class='dropdown' ng-if='UserInfo'>" +
        "         <a href='#' class='dropdown-toggle' data-toggle='dropdown'>我的账户<b class='caret'></b></a>" +
        "         <ul class='dropdown-menu'>" +
        "         <li>" +
        "           <div class='navbar-content'>" +
        "             <div class='row'>" +
        "               <div class='col-md-5'>" +
        "                 <img src='http://placehold.it/120x120' alt='Alternate Text' class='img-responsive' />" +
        "                   <p class='text-center small'><a href='#' onclick='$.api.Developing()'>更换照片</a></p>" +
        "               </div>" +
        "               <div class='col-md-7'>" +
        "                 <span>{{UserInfo.name}}</span>" +
        "                 <p class='text-muted small'>{{UserInfo.email}}</p>" +
        "               <div class='divider'></div>" +
        "                 <a href='web/account.html' class='btn btn-primary btn-sm active'>账户信息</a>" +
        "               </div>" +
        "             </div>" +
        "           </div>" +
        "" +
        "           <div class='navbar-footer'>" +
        "             <div class='navbar-footer-content'>" +
        "               <div class='row'>" +
        "                 <div class='col-md-6'>" +
        "                   <a href='web/account.html' class='btn btn-default btn-sm'>更改密码</a>" +
        "                 </div>" +
        "                 <div class='col-md-6'>" +
        "                   <a href='web/login.html' class='btn btn-default btn-sm pull-right' ng-click='DoLogOut()'>退出登录</a>" +
        "                 </div>" +
        "               </div>" +
        "             </div>" +
        "           </div>" +
        "" +
        "         </li>" +
        "         </ul>" +
        "       </li>" +
        "      </ul>" +
        "    </div>" +
        "  </div>" +
        "</nav>"
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
        "       <li class='dropdown' ng-if='UserInfo'>" +
        "         <a href='#' class='dropdown-toggle' data-toggle='dropdown'>我的账户<b class='caret'></b></a>" +
        "         <ul class='dropdown-menu'>" +
        "         <li>" +
        "           <div class='navbar-content'>" +
        "             <div class='row'>" +
        "               <div class='col-md-5'>" +
        "                 <img src='http://placehold.it/120x120' alt='Alternate Text' class='img-responsive' />" +
        "                   <p class='text-center small'><a href='#' onclick='$.api.Developing()'>更换照片</a></p>" +
        "               </div>" +
        "               <div class='col-md-7'>" +
        "                 <span>{{UserInfo.name}}</span>" +
        "                 <p class='text-muted small'>{{UserInfo.email}}</p>" +
        "               <div class='divider'></div>" +
        "                 <a href='account.html' class='btn btn-primary btn-sm active'>账户信息</a>" +
        "               </div>" +
        "             </div>" +
        "           </div>" +
        "" +
        "           <div class='navbar-footer'>" +
        "             <div class='navbar-footer-content'>" +
        "               <div class='row'>" +
        "                 <div class='col-md-6'>" +
        "                   <a href='account.html' class='btn btn-default btn-sm'>更改密码</a>" +
        "                 </div>" +
        "                 <div class='col-md-6'>" +
        "                   <a href='login.html' class='btn btn-default btn-sm pull-right' ng-click='DoLogOut()'>退出登录</a>" +
        "                 </div>" +
        "               </div>" +
        "             </div>" +
        "           </div>" +
        "" +
        "         </li>" +
        "         </ul>" +
        "       </li>" +
        "     </ul>" +
        "   </div>\n" +
        "  </div>\n" +
        "</nav>\n"
    };
});

