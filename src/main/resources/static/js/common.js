var tokenId = $.cookie('rent_admin_tokenId');
var userId = $.cookie('rent_admin_userId');

var header = {}; //或者 var obj=new Object();
header["tokenId"] = tokenId;
header["userId"] = userId;

layui.use(['element', 'layer', 'table', 'form'], function () {
    var $ = layui.$;

    var scratMenu = {
        userLogout: function () {
            $.cookie("rent_admin_tokenId", null, {path: '/rent'})
            $.cookie("rent_admin_userId", null, {path: '/rent'})
            tokenId = null;
            window.location.href = requestBaseUrl +  "/";
        },
        dashboardManage: function () {
            window.location.href = requestBaseUrl +  "/landlord/goHome/" + userId + "?tokenId=" + tokenId;
        },
        landlordManage: function () {
            window.location.href = requestBaseUrl +  "/landlord/goLandlord"+ "?userId=" + userId + "&tokenId=" + tokenId;
        },
        roomManage: function () {
            window.location.href = requestBaseUrl +  "/room/goRoom/" + userId + "?tokenId=" + tokenId;
        },
        rentManage: function () {
            window.location.href = requestBaseUrl +  "/rent/goRent/" + userId + "?tokenId=" + tokenId;
        },

    };

    //绑定click点击事件
    $('.scratMenu').on('click', function () {
        var othis = $(this), method = othis.data('method');
        scratMenu[method] ? scratMenu[method].call(this, othis) : '';
    });

});

