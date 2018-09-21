layui.use(['layer', 'table', 'form', 'laytpl'], function () {
    var $ = layui.$;
    var layer = layui.layer;
    var table = layui.table;
    var form = layui.form;
    var laytpl = layui.laytpl;

    //方法级渲染
    table.render({
        elem: '#lay_table_user'//指定原始表格元素选择器（
        , url: requestBaseUrl + '/user/list'//数据接口
        , method: 'post'
        , contentType: 'application/json'
        , where: {
            body: {
                name: $('#searchName').val(),
                phone: $('#searchPhone').val(),
                roleCode: $('#searchRoleCode').val(),
                checkTs: $('#searchCheckTs').val()
            }
        }//传参*/
        , headers: header
        , request: {
            pageName: 'page' //页码的参数名称，默认：page
            , limitName: 'rows' //每页数据量的参数名，默认：limit
        } //如果无需自定义请求参数，可不加该参数
        , response: {
            statusName: 'code' //数据状态的字段名称，默认：code
            , statusCode: 1 //成功的状态码，默认：0
            , msgName: 'msg' //状态信息的字段名称，默认：msg
            , countName: 'count' //数据总数的字段名称，默认：count
            , dataName: 'data' //数据列表的字段名称，默认：data
        } //如果无需自定义数据响应名称，可不加该参数
        , id: 'lay_table_user'
        , page: true//开启分页
//            ,height: 315//容器高度
        , cols: [[//表头
            {field: 'name', title: '姓名', sort: true, width: 100}
            , {field: 'phone', title: '电话', sort: true, width: 150}
            , {field: 'idCard', title: '身份证号', width: 200}
            , {field: 'check', title: '实名认证', sort: true, width: 100, templet: function(d){
                    if(d.checkTs > 0){
                        return "已实名";
                    }
                    if(isNotEmpty(d.idCardPic) && isNotEmpty(d.idCardPicBack)){
                        return "待认证";
                    }
                    return  '未认证';
                }}
            , {field: 'wechat', title: '微信'}
            , {field: '', title: '操作', align: 'left', toolbar: '#userListBar'}
        ]]
        , done: function (res, curr, count) {
        }
    });

    //监听行单击事件
    table.on('rowDouble(userFormFilter)', function(obj){
        console.log(obj.tr); //得到当前行元素对象
        console.log(obj.data); //得到当前行数据
        active.detail(obj.data);
        //obj.del(); //删除当前行
        //obj.update(fields); //修改当前行数据
    });

    //监听工具条
    table.on('tool(userTableFilter)', function (obj) {
        var data = obj.data;
        if (obj.event === 'detail') {
            active.realCheck(data);
        } else if (obj.event === 'del') {
            layer.confirm('真的删除行么', function (index) {
                var jhxhr = $.ajax({url: requestBaseUrl + "/user/delete", data:{"ids": data.userId}, headers: header, type: "POST"});
                jhxhr.done(function (res) {
                    if(res.code == 1){
                        layer.msg("删除成功");
                        obj.del();
                    }else{
                        layer.alert(res.message);
                    }
                });
                layer.close(index);
            });
        } else if (obj.event === 'edit') {
            $('#userEditForm #roleCodeDiv').remove();
            form.val("userFormFilter", {
                "userId": data.userId
                ,"name": data.name
                ,"phone": data.phone
                ,"idCard": data.idCard
                ,"remark": data.remark
            });
            active.edit();
        } else if (obj.event === 'realCheck') {
            active.realCheck(data);
        }
    });

    var active = {
        search: function () {
            //执行重载
            table.reload('lay_table_user', {
                page: {
                    curr: 1 //重新从第 1 页开始
                }
                , where: {
                    body: {
                        name: $('#searchName').val(),
                        phone: $('#searchPhone').val(),
                        roleCode: $('#searchRoleCode').val(),
                        checkTs: $('#searchCheckTs').val()
                    }
                }//传参*/
            });
        },
        add: function (othis) {
            var getTpl = userEditUserRoleTemplete.innerHTML;
            $('#ff').html(getTpl);
            form.render();
            $("input").val("");
            // var type = othis.data('type');
            layer.open({
                type: 1//0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
                ,title: "新增用户"
                , area: '500px'
                , offset: 'auto' //具体配置参考：http://www.layui.com/doc/modules/layer.html#offset
                , id: 'layerLandlordAdd' //防止重复弹出
                , content: $('#addDiv')
                , btn: '关闭全部'
                , btnAlign: 'c' //按钮居中
//                    ,shade: 0 //不显示遮罩
                , yes: function () {
                    layer.closeAll();
                }
                , success: function(layero, index){

                    console.log(layero, index);
                }
            });
        },
        realCheck: function (data) {
            data.createDate = new Date(data.createTs).Format('yyyy-MM-dd HH:mm:ss');
            data.checkDate = data.checkTs > 0 ?new Date(data.checkTs).Format('yyyy-MM-dd HH:mm:ss') : '未认证';
            var getTpl = userCheckTemplete.innerHTML;
            var view = document.getElementById('userCheckTableTbody');
            laytpl(getTpl).render(data, function(html){
                view.innerHTML = html;
            });
            layer.open({
                type: 1//0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
                ,title: "实名认证"
                , area: '700px'
                , offset: 'auto' //具体配置参考：http://www.layui.com/doc/modules/layer.html#offset
                , id: 'layerUserCheck' //防止重复弹出
                , content: $('#userCheckDiv')
                , btn: ['取消', '认证通过']
                , btnAlign: 'c'
                , yes: function () {
                    layer.closeAll();
                }
                , btn2: function(index, layero){
                    //按钮【按钮二】的回调
                    layer.confirm('请再次确认实名认证通过', function (index) {
                        var jhxhr = $.ajax({url: requestBaseUrl + "/user/realConfirm/" + data.userId, headers: header, contentType: 'application/json', type: "GET"});
                        jhxhr.done(function (res) {
                            console.log(res);
                            if(res.code == 1){
                                layer.close(1);
                                layer.msg('实名认证成功!')
                            }else{
                                layer.alert(res.message);
                            }
                        });
                        layer.close(index);
                    });

                }
            });
        },
        edit: function () {
            layer.open({
                type: 1//0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
                ,title: "编辑用户"
                , area: '500px'
                , offset: 'auto' //具体配置参考：http://www.layui.com/doc/modules/layer.html#offset
                , id: 'layerLandlordEdit'  //防止重复弹出
                , content: $('#addDiv')
                , btn: '关闭全部'
                , btnAlign: 'c' //按钮居中
//                    ,shade: 0 //不显示遮罩
                , yes: function () {
                    layer.closeAll();
                }
            });
        },
    };

    //绑定click点击事件
    $('.childrenBody .layui-btn').on('click', function () {
        var othis = $(this), method = othis.data('method');
        active[method] ? active[method].call(this, othis) : '';
    });

});