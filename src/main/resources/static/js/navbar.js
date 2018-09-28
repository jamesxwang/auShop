var app = angular.module("auShop", []);
app.controller('navCtrl',function ($scope) {
    $scope.UserInfo = $.api.GetUserInfo();
    $scope.DoLogOut = function () {
        $.api.ClearAll();
        location.href = 'login.html'
    }
});
app.directive('nav4web',function () {
    return {
        templateUrl:"../web/navbar.html",
        restrict:"E"
    }
});