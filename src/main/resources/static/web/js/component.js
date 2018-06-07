//************** navbar for index.html **************
avalon.component('ms-nav1',{
    template:'' +
    '<div class="view">' +
    '<!--  Navigation Bar -->\n' +
    '<nav class="navbar navbar-default navbar-fixed-top noBorder" role="navigation">\n' +
    '    <div class="container">\n' +
    '\n' +
    '        <div class="navbar-header">\n' +
    '            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">\n' +
    '                <span class="sr-only">Toggle navigation</span>\n' +
    '                <span class="icon-bar"></span>\n' +
    '                <span class="icon-bar"></span>\n' +
    '                <span class="icon-bar"></span>\n' +
    '            </button>\n' +
    '            <a class="navbar-brand brandStyle" href="index.html">WxBlog</a>\n' +
    '        </div>\n' +
    '\n' +
    '        <div class="collapse navbar-collapse noPadding"\tid="myNavbar">\n' +
    '            <div class="navbar-right menustyle">\n' +
    '                <ul class="nav navbar-nav">\n' +
    '                    <!--Search form-->\n' +
    '                    <li><form class="navbar-form" action="web/search.html">\n' +
    '                        <div class="form-group" >\n' +
    '                            <input type="text" class="form-control" placeholder="Search" autocomplete="off">\n' +
    '                        </div>\n' +
    '                        <button type="submit" class="btn btn-default m-l-sm"><a class="navbarFont">Search</a></button>\n' +
    '                        <div class="search-result hidden"></div>\n' +
    '                    </form></li>\n' +
    '\n' +
    '                    <!--Register-->\n' +
    '                    <li><a href="web/register.html" ms-if="showLoginStatus()" class="navbarFont"><p>Sign up</p></a></li>\n' +
    '                    <!--Login-->\n' +
    '                    <li><a href="web/login.html" ms-if="showLoginStatus()" class="navbarFont"><p>Sign in</p></a></li>\n' +
    '                    <!--Welcome-->\n' +
    '                    <li><a href="web/userInfo.html" ms-if="!showLoginStatus()" class="navbarFont"><p>Welcome, {{UserInfo.name}}</p></a></li>\n' +
    '\n' +
    '                    <!--Settings-->\n' +
    '                    <li class="dropdown">\n' +
    '                        <a class="dropdown-toggle navbarFont" data-toggle="dropdown" role="button" aria-haspopup="true"\n' +
    '                           aria-expanded="false" ms-if="!showLoginStatus()">Settings <span class="caret"></span></a>\n' +
    '                        <ul class="dropdown-menu">\n' +
    '                            <li><a href="#" class="navbarFont">Account</a></li>\n' +
    '                            <li><a href="#" class="navbarFont">Safe settings</a></li>\n' +
    '                            <li><a href="#" class="navbarFont">Messages <span class="badge">3</span></a></li>\n' +
    '                            <li role="separator" class="divider"></li>\n' +
    '                            <!--<li class="dropdown-header">Nav header</li>-->\n' +
    '                            <li><a href="#" ms-on-click="DoLogOut()" class="navbarFont">Log out</a></li>\n' +
    '                            <!--<li><a href="#">One more separated link</a></li>-->\n' +
    '                        </ul>\n' +
    '                    </li>\n' +
    '                </ul>\n' +
    '            </div>\n' +
    '        </div>\n' +
    '\n' +
    '    </div>\n' +
    '</nav>\n' +
    '</div>',
    defaults: {
        content: "",
        UserInfo: $.api.GetUserInfo(),
        showLoginStatus: function () {
            return vm.UserInfo == null;
        },
        DoLogOut: function () {
            $.api.DoLogOut();
        }
    }
})

//************** navbar for other html files **************
avalon.component('ms-nav',{
    template:'' +
    '<div class="view">' +
    '<!--  Navigation Bar -->\n' +
    '<nav class="navbar navbar-default navbar-fixed-top noBorder" role="navigation">\n' +
    '    <div class="container">\n' +
    '\n' +
    '        <div class="navbar-header">\n' +
    '            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">\n' +
    '                <span class="sr-only">Toggle navigation</span>\n' +
    '                <span class="icon-bar"></span>\n' +
    '                <span class="icon-bar"></span>\n' +
    '                <span class="icon-bar"></span>\n' +
    '            </button>\n' +
    '            <a class="navbar-brand brandStyle" href="../index.html">WxBlog</a>\n' +
    '        </div>\n' +
    '\n' +
    '        <div class="collapse navbar-collapse noPadding"\tid="myNavbar">\n' +
    '            <div class="navbar-right menustyle">\n' +
    '                <ul class="nav navbar-nav">\n' +
    '                    <!--Search form-->\n' +
    '                    <li><form class="navbar-form" action="search.html">\n' +
    '                        <div class="form-group" >\n' +
    '                            <input type="text" class="form-control" placeholder="Search" autocomplete="off">\n' +
    '                        </div>\n' +
    '                        <button type="submit" class="btn btn-default m-l-sm"><a class="navbarFont">Search</a></button>\n' +
    '                        <div class="search-result hidden"></div>\n' +
    '                    </form></li>\n' +
    '\n' +
    '                    <!--Register-->\n' +
    '                    <li><a href="register.html" ms-if="showLoginStatus()" class="navbarFont"><p>Sign up</p></a></li>\n' +
    '                    <!--Login-->\n' +
    '                    <li><a href="login.html" ms-if="showLoginStatus()" class="navbarFont"><p>Sign in</p></a></li>\n' +
    '                    <!--Welcome-->\n' +
    '                    <li><a href="userInfo.html" ms-if="!showLoginStatus()" class="navbarFont"><p>Welcome, {{UserInfo.name}}</p></a></li>\n' +
    '\n' +
    '                    <!--Settings-->\n' +
    '                    <li class="dropdown">\n' +
    '                        <a class="dropdown-toggle navbarFont" data-toggle="dropdown" role="button" aria-haspopup="true"\n' +
    '                           aria-expanded="false" ms-if="!showLoginStatus()">Settings <span class="caret"></span></a>\n' +
    '                        <ul class="dropdown-menu">\n' +
    '                            <li><a href="#" class="navbarFont">My Account</a></li>\n' +
    '                            <li><a href="#" class="navbarFont">Safe settings</a></li>\n' +
    '                            <li><a href="#" class="navbarFont">Messages <span class="badge">3</span></a></li>\n' +
    '                            <li role="separator" class="divider"></li>\n' +
    '                            <!--<li class="dropdown-header">Nav header</li>-->\n' +
    '                            <li><a href="#" ms-on-click="DoLogOut()" class="navbarFont">Log out</a></li>\n' +
    '                            <!--<li><a href="#">One more separated link</a></li>-->\n' +
    '                        </ul>\n' +
    '                    </li>\n' +
    '                </ul>\n' +
    '            </div>\n' +
    '        </div>\n' +
    '\n' +
    '    </div>\n' +
    '</nav>\n' +
    '</div>',
    defaults: {
        content: "",
        UserInfo: $.api.GetUserInfo(),
        showLoginStatus: function () {
            return vm.UserInfo == null;
        },
        DoLogOut: function () {
                $.api.DoLogOut();
        }
    }
})

//************** footer with black words **************
avalon.component('ms-footer-black',{
    template:'' +
    '<div class="view">' +
    '<div class="container footer">\n' +
    '    <div class="col-xs-12 col-md-8 noPadding">\n' +
    '        <p id="footerLogoText" class="black">&copy; 2018-2019 www.amazingxu.xyz</p>\n' +
    '    </div>\n' +
    '    <div class="col-xs-12 col-md-4 noPadding">\n' +
    '        <div class="responsiveAlign">\n' +
    '            <a href="http://www.miitbeian.gov.cn" target="_blank" class="noPadding"><p class="black">湘ICP备18010549号-1<p></a>\n' +
    '        </div>\n' +
    '    </div>\n' +
    '</div>' +
    '</div>',
    defaults: {
        content: ""
    }
})

//************** footer with white words **************
avalon.component('ms-footer-white',{
    template:'' +
    '<div class="view">' +
    '<div class="container footer">\n' +
    '    <div class="col-xs-12 col-md-8 noPadding">\n' +
    '        <p id="footerLogoText" class="white">&copy; 2018-2019 www.amazingxu.xyz</p>\n' +
    '    </div>\n' +
    '    <div class="col-xs-12 col-md-4 noPadding">\n' +
    '        <div class="responsiveAlign">\n' +
    '            <a href="http://www.miitbeian.gov.cn" target="_blank" class="noPadding"><p class="white">湘ICP备18010549号-1</p></a>\n' +
    '        </div>\n' +
    '    </div>\n' +
    '</div>' +
    '</div>',
    defaults: {
        content: ""
    }
})