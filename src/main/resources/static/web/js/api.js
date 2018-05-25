$.api = {
    token: localStorage.getItem("token"),
    // root: "http://localhost:8082",
    root:"http://"+location.host,
    ClearAll: function () {
        localStorage.clear();
    },
    SetToken: function (token) {
        localStorage.setItem("token", token);
        $.api.token = localStorage.getItem("token");
    },
    SetUserInfo: function (userinfo) {
        localStorage.setItem("userinfo", JSON.stringify(userinfo));
    },
    GetDays:function(dateA,dateB){
        var days = dateB.getTime() - dateA.getTime();
        var d = parseInt(days / (1000 * 60 * 60 * 24));
        return d;
    },
    GetUserInfo: function () {
        try {
            return JSON.parse(localStorage.getItem("userinfo"));
        } catch (e) {
            return null;
        }
    },
    CheckLogin: function () {
        if ($.api.token == null) {
            location.href = 'login.html';
        }
    },
    Copy: function (obj) {
        return $.extend(true, {}, obj);
    },
    Req: function (name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) return unescape(r[2]);
        return null;
    },
    Post: function (url, data, callback, hideLoad) {
        if (!hideLoad) {
            $.api.ShowLoad();
        }
        var source = url;
        url = $.api.root + url;
        var pars = null
        if (typeof(data) == "object" && Object.prototype.toString.call(data).toLowerCase() == "[object object]" && !data.length) {
            pars = JSON.stringify(data);
        } else if (typeof(data) == "object" && Object.prototype.toString.call(data).toLowerCase() == "[object array]") {
            pars = JSON.stringify(data);
        } else {
            pars = data;
        }
        console.log("$.api.Post>>URL:" + source + ",PAR:" + pars);
        var ajaxParam = {
            type: 'post',
            url: url,
            contentType: 'application/json',
            data: pars,
            dataType: "json",
            success: function (data) {
                console.log(data);
                if (callback) {
                    if (data.code == 0) {
                        callback(data);
                    } else {
                        $.api.Msg(data.message);
                    }
                }
                if (!hideLoad) {
                    $.api.HideLoad();
                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                if (!hideLoad) {
                    $.api.HideLoad();
                }
                console.log(XMLHttpRequest);
                $.api.Msg("请求失败,请检查网络&nbsp;"+url);
            }
        };
        if ($.api.token != null) {
            ajaxParam.headers = {'Authorization': 'Bearer ' + $.api.token};
        }
        $.ajax(ajaxParam);
    },
    DoLogin: function (uid, pwd) {
        var userinfo = localStorage.getItem("userinfo");
        $.api.Post('/user/login', {username: uid, password: pwd}, function (result) {
            setTimeout(function () {
                $.api.SetToken(result.data);
                setTimeout(function(){
                    $.api.Post('/user/getMyUserContext',{},function(user){
                        $.api.SetUserInfo(user.data);
                        location.href = "../../index.html";
                    });
                });
            });
        });
    },
    ShowLoad: function (text) {
        if (text) {
            loader = layer.msg('&nbsp;' + text + '...', {
                shift: -1,
                icon: 16,
                shade: 0.2,
                time: 50 * 1000,
                isOutAnim: false
            });
        } else {
            //loader = layer.load(1, {time: 10*1000});
            loader = layer.msg('&nbsp;<span style="color:#000">请稍后...</span>', {
                shift: -1,
                icon: 16,
                shade: 0.2,
                time: 50 * 1000,
                isOutAnim: false,
                zIndex: 99999999
            });
        }
    },
    Confirm: function (title, callback) {
        if (title == null) {
            title = "确认操作吗?";
        }
        var me = layer.confirm(title, {
            btn: ['确定', '取消'] //按钮
        }, function () {
            if (callback) callback();
            layer.close(me);
        }, function () {

        });
    },
    HideLoad: function () {
        layer.close(loader);
    },
    Msg: function (msg) {
        layer.msg(msg, {offset: '0px',area: ['100%'],anim:6,zIndex: 99999999});
    },
    Alert: function (msg,callback) {
        layer.alert(msg,callback);
    },
    CloseAll:function(){
        layer.closeAll();
    },
    Open: function (title, w, h, elem, callback) {
        if (title == null) {
            title = "窗口";
        }
        if (w == null) {
            w = "800px";
        }
        if (h == null) {
            h = "600px";
        }
        if (elem == null) {
            elem = $(this);
        }
        return layer.open({
            type: 1,
            area: [w, h], //宽高
            maxmin: true,
            shadeClose: true,
            title: title, //不显示标题
            zIndex: 99891014,
            shade: .3,
            anim:-1,
            content: elem,
            success: function (layero, index) {
                if (callback) callback();
            }
        });
    }
};
