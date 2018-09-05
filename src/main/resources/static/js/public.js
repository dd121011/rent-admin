var requestBaseUrl = getRootPath();
var countdown = 120;

//js获取项目根路径，如： http://localhost:8083/rent
function getRootPath(){
    //获取当前网址，如： http://localhost:8083/rent/admin/news.action
    var curWwwPath=window.document.location.href;
    //获取主机地址之后的目录，如： rent/admin/news.action
    var pathName=window.document.location.pathname;
    var pos=curWwwPath.indexOf(pathName);
    //获取主机地址，如： http://localhost:8083
    var localhostPaht=curWwwPath.substring(0,pos);
    //获取带"/"的项目名，如：/rent
    var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
    return(localhostPaht+projectName);
}

$.fn.serializeObject = function()
{
    var o = {};
    var a = this.serializeArray();
    $.each(a, function() {
        if (o[this.name]) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
};

//----------------------------------------------------------
//    功能：根据身份证号获得出生日期
//  参数：身份证号 psidno
//    返回值：
//    出生日期
//----------------------------------------------------------
function GetBirthday(psidno){
    var birthdayno,birthdaytemp
    if(psidno.length==18){
        birthdayno=psidno.substring(6,14)
    }else if(psidno.length==15){
        birthdaytemp=psidno.substring(6,12)
        birthdayno="19"+birthdaytemp
    }else{
        alert("错误的身份证号码，请核对！")
        return false
    }
    var birthday=birthdayno.substring(0,4)+"-"+birthdayno.substring(4,6)+"-"+birthdayno.substring(6,8)
    return birthday
}

//----------------------------------------------------------
//    功能：根据身份证号获得性别
//  参数：身份证号 psidno
//    返回值：
//    性别
//----------------------------------------------------------
function Getsex(psidno){
    var sexno,sex
    if(psidno.length==18){
        sexno=psidno.substring(16,17)
    }else if(psidno.length==15){
        sexno=psidno.substring(14,15)
    }else{
        alert("错误的身份证号码，请核对！")
        return false
    }
    var tempid=sexno%2;
    if(tempid==0){
        // sex='F'
        sex='2'
    }else{
        // sex='M'
        sex='1'
    }
    return sex
}

//---------------------------------------------------
//日期格式化
//格式 YYYY/yyyy/YY/yy 表示年份
//MM/M 月份
//W/w 星期
//dd/DD/d/D 日期
//hh/HH/h/H 时间
//mm/m 分钟
//ss/SS/s/S 秒
//---------------------------------------------------
Date.prototype.Format = function(formatStr)
{
    var str = formatStr;
    var Week = ['日','一','二','三','四','五','六'];

    str=str.replace(/yyyy|YYYY/,this.getFullYear());
    str=str.replace(/yy|YY/,(this.getYear() % 100)>9?(this.getYear() % 100).toString():'0' + (this.getYear() % 100));

    str=str.replace(/MM/,(this.getMonth()+1)>9?(this.getMonth()+1).toString():'0' + (this.getMonth()+1));
    str=str.replace(/M/g,this.getMonth());

    str=str.replace(/w|W/g,Week[this.getDay()]);

    str=str.replace(/dd|DD/,this.getDate()>9?this.getDate().toString():'0' + this.getDate());
    str=str.replace(/d|D/g,this.getDate());

    str=str.replace(/hh|HH/,this.getHours()>9?this.getHours().toString():'0' + this.getHours());
    str=str.replace(/h|H/g,this.getHours());
    str=str.replace(/mm/,this.getMinutes()>9?this.getMinutes().toString():'0' + this.getMinutes());
    str=str.replace(/m/g,this.getMinutes());

    str=str.replace(/ss|SS/,this.getSeconds()>9?this.getSeconds().toString():'0' + this.getSeconds());
    str=str.replace(/s|S/g,this.getSeconds());

    return str;
};

function isEmpty(str) {
    if(undefined == str || null == str || str.trim().length == 0){
        return true;
    }
    return false;
}

function isEmptyArray(str) {
    if(undefined == str || null == str || str.length == 0){
        return true;
    }
    return false;
}

//校验密码
var checkPwd = function (ff2) {
    // if (f2 && typeof(f2) === "function") {
    //     return false;//写上判断是个好习惯f2()
    // }
    layui.use(['layer', 'form'], function () {
        var $ = layui.jquery;
        var layer = layui.layer;
        var form = layui.form;
        form.val("userPwdCheckFormFilter", {
            "pwd": ""
        });
        layer.open({
            type: 1//0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
            ,title: "校验密码"
            , area: '500px'
            , offset: 'auto' //具体配置参考：http://www.layui.com/doc/modules/layer.html#offset
            , id: 'layerUserPwdCheck' //防止重复弹出
            , content: $('#userPwdCheckDiv')
            , btn: ['取消', '确定']
            , btnAlign: 'c' //按钮居中
            //                    ,shade: 0 //不显示遮罩
            , yes: function () {
                layer.closeAll();
            }
            , btn2: function(index, layero){
                //按钮【按钮二】的回调
                var params = {};
                var bodyParams = {};
                bodyParams.pwd = $('#checkPwd').val();
                params.body = bodyParams;
                console.log(params);
                var jhxhr = $.ajax({url: requestBaseUrl + "/user/checkPwd", data: JSON.stringify(params), headers: header, contentType: 'application/json', type: "POST"});
                jhxhr.done(function (res) {
                    console.log(res);
                    if(res.code == 1){
                        layer.close(1);
                        ff2 && ff2();
                    }else{
                        layer.alert(res.message);
                    }
                });
            }
        });
    });
};

//绑定click点击事件
var smsCodeGenerateClick = function (phoneObj, codeObj) {
    layui.use(['layer'], function () {
        var $ = layui.jquery;
        var layer = layui.layer;
        var form = layui.form;
        codeObj.on('click', function () {
            var smsCodeButton = this;
            var sendPhone = phoneObj.val();
            if(isEmpty(sendPhone)){
                return layer.alert("请输入正确的手机号码!");
            }
            var jhxhr = $.ajax({url: requestBaseUrl + "/api/sms/send/" + sendPhone, headers: header, contentType: 'application/json', type: "GET"});
            jhxhr.done(function (res) {
                console.log(res);
                if(res.code == 1){
                    settime(smsCodeButton);
                }else{
                    layer.alert(res.message);
                }
            });
        });
    });
};


function settime(obj) {
    if (countdown == 0) {
        obj.removeAttribute("disabled");
        obj.innerHTML = "获取验证码";
        countdown = 120;
        return;
    } else {
        obj.setAttribute("disabled", true);
        obj.innerHTML = "" + countdown + " 秒后再获取";
        countdown--;
    }
    setTimeout(function () {
        settime(obj)
    }, 1000)
}