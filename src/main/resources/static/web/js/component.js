//************** navbar for index.html **************
avalon.component('ms-nav1',{
    template:'<div class="view"><!--  Navigation Bar -->\n' +
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
    '                        <button type="submit" class="btn btn-default m-l-sm"><a>Search</a></button>\n' +
    '                        <div class="search-result hidden"></div>\n' +
    '                    </form></li>\n' +
    '\n' +
    '                    <!--Register-->\n' +
    '                    <li><a href="web/register.html" ><p>Sign up</p></a></li>\n' +
    '                    <!--Login-->\n' +
    '                    <li><a href="web/login.html" ><p>Sign in</p></a></li>\n' +
    '                    <!--Welcome-->\n' +
    '                    <li><a href="web/user_info.html" ms-if="UserInfo.id != null"><p>Welcome, {{UserInfo.name}}</p></a></li>\n' +
    '\n' +
    '                    <!--Settings-->\n' +
    '                    <li class="dropdown">\n' +
    '                        <a class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"\n' +
    '                           aria-expanded="false">Settings <span class="caret"></span></a>\n' +
    '                        <ul class="dropdown-menu">\n' +
    '                            <li><a href="#">Account</a></li>\n' +
    '                            <li><a href="#">Safe settings</a></li>\n' +
    '                            <li><a href="#">Messages</a></li>\n' +
    '                            <li role="separator" class="divider"></li>\n' +
    '                            <!--<li class="dropdown-header">Nav header</li>-->\n' +
    '                            <li><a href="#">Log out</a></li>\n' +
    '                            <!--<li><a href="#">One more separated link</a></li>-->\n' +
    '                        </ul>\n' +
    '                    </li>\n' +
    '                </ul>\n' +
    '            </div>\n' +
    '        </div>\n' +
    '\n' +
    '    </div>\n' +
    '</nav>\n</div>',
    defaults: {
        content: ""
    }
})

//************** navbar for other html files **************
avalon.component('ms-nav',{
    template:'<div class="view"><!--  Navigation Bar -->\n' +
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
    '                        <button type="submit" class="btn btn-default m-l-sm"><a>Search</a></button>\n' +
    '                        <div class="search-result hidden"></div>\n' +
    '                    </form></li>\n' +
    '\n' +
    '                    <!--Register-->\n' +
    '                    <li><a href="register.html" ><p>Sign up</p></a></li>\n' +
    '                    <!--Login-->\n' +
    '                    <li><a href="login.html" ><p>Sign in</p></a></li>\n' +
    '                    <!--Welcome-->\n' +
    '                    <li><a href="user_info.html" ms-if="UserInfo.id != null"><p>Welcome, {{UserInfo.name}}</p></a></li>\n' +
    '\n' +
    '                    <!--Settings-->\n' +
    '                    <li class="dropdown">\n' +
    '                        <a class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"\n' +
    '                           aria-expanded="false">Settings <span class="caret"></span></a>\n' +
    '                        <ul class="dropdown-menu">\n' +
    '                            <li><a href="#">Account</a></li>\n' +
    '                            <li><a href="#">Safe settings</a></li>\n' +
    '                            <li><a href="#">Messages</a></li>\n' +
    '                            <li role="separator" class="divider"></li>\n' +
    '                            <!--<li class="dropdown-header">Nav header</li>-->\n' +
    '                            <li><a href="#">Log out</a></li>\n' +
    '                            <!--<li><a href="#">One more separated link</a></li>-->\n' +
    '                        </ul>\n' +
    '                    </li>\n' +
    '                </ul>\n' +
    '            </div>\n' +
    '        </div>\n' +
    '\n' +
    '    </div>\n' +
    '</nav>\n</div>',
    defaults: {
        content: ""
    }
})

var vm = avalon.define({
    $id: "wxBlog",
    UserInfo:$.api.GetUserInfo()
})