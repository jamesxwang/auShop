$.api = {
    token: localStorage.getItem("token"),
    // root:"http://localhost:8082", //开发环境
    root:"https://"+location.host, //生产环境

    ClearAll: function () {
        localStorage.clear();
    },
    SetToken: function (token) {
        localStorage.setItem("token", token);
        $.api.token = localStorage.getItem("token");
    },
    SetNewToken:function (newToken) {
        localStorage.setItem("newToken",newToken);
        $.api.token = localStorage.getItem("newToken");
        $.api.SetUserInfo();
    },
    SetUserInfo: function (userinfo) {
        localStorage.setItem("userinfo", JSON.stringify(userinfo));
    },
    GetDays:function(dateA,dateB){
        var days = dateB.getTime() - dateA.getTime();
        var d = parseInt(days / (1000 * 60 * 60 * 24));
        return d;
    },
    Developing: function () {
        $.api.Fail("Developing！");
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
            location.href = "/login.html";
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
                if (callback) {
                    if (data.code == 0) {
                        callback(data);
                    } else {
                        $.api.Fail(data.message);
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
                $.api.Fail("请求失败,请检查网络&nbsp;"+url);
            }
        };
        if ($.api.token != null) {
            ajaxParam.headers = {'Authorization': 'Bearer ' + $.api.token};
        }
        $.ajax(ajaxParam);
    },
    DoLogin: function (uid, pwd) {
        $.api.Post('/user/login', {username: uid, password: pwd}, function (result) {
            setTimeout(function () {
                $.api.SetToken(result.data);
                setTimeout(function(){
                    $.api.Post('/user/getMyUserContext',{},function(user){
                        $.api.SetUserInfo(user.data);
                        location.href = "../index.html";
                    });
                });
            });
        });
    },
    DoRegister: function (username,name,email,password) {
        $.api.Post('/user/register',{username:username,name:name,email:email,password:password},function (result) {
            setTimeout(function () {
                $.api.DoLogin(username,password);
            })
        })
    },
    DoRefreshUserInfo:function () {
        $.api.Post('/user/refresh',{},function (result) {
            setTimeout(function () {
                $.api.SetNewToken(result.data);
                setTimeout(function () {
                    $.api.Post('/user/getMyUserContext',{},function (userinfo) {
                        $.api.SetUserInfo(userinfo.data);
                        $.api.Success("更改成功，请刷新页面。");
                        // location.reload(true);

                    })
                });
            })
        });
    },
    ChangePassword:function (oldpwd,newpwd) {
      $.api.Post('/user/changePassword',{oldPassword:oldpwd,newPassword:newpwd},function () {
          setTimeout(function () {
              $.api.DoRefreshUserInfo();
          })
      })
    },
    ChangePhone:function (oldPhone,newPhone) {
        $.api.Post('/user/changePhone',{oldPhone:oldPhone,newPhone:newPhone},function () {
            setTimeout(function () {
                $.api.DoRefreshUserInfo();
            })
        })
    },
    ChangeGender:function (newGender) {
      $.api.Post('/user/changeGender',{newGender:newGender},function () {
          setTimeout(function () {
              $.api.DoRefreshUserInfo();
          })
      })
    },
    ChangeEmail:function (newEmail) {
        $.api.Post('/user/changeEmail',{newEmail:newEmail},function () {
            setTimeout(function(){
                $.api.DoRefreshUserInfo();
            })
        })
    },
    ListProduct: function () {
        $.api.Post('/product/query',{},function (result) {
            setTimeout(function () {
                $.api.SetProdInfo(result.data);
                setTimeout(function () {
                    $.api.GetProdInfo();
                })
            });
        });
    },
    SetProdInfo: function (prodInfo) {
        localStorage.setItem("prodInfo", JSON.stringify(prodInfo));
    },
    GetProdInfo: function () {
        try{
            return JSON.parse(localStorage.getItem("prodInfo"));
        } catch (e){
            $.api.ListProduct();
        }
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
            btn: ['YES', 'NO'] //按钮
        }, function () {
            if (callback) callback();
            layer.close(me);
        }, function () {

        });
    },
    HideLoad: function () {
        layer.close(loader);
    },
    Fail: function (msg) {
        layer.msg("<span style=\"color:#000\">" + msg + "</span>", { icon: 5, zIndex: 99999999});
    },
    Success:function (msg) {
        layer.msg("<span style=\"color:#000\">" + msg + "</span>", { icon: 6, zIndex: 99999999});
    },
    Alert: function (title,msg,callback) {
        layer.alert(title,msg,callback);
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
