layui.use('table', function(){
    var $ = layui.$;
    var table = layui.table;

    //方法级渲染
    table.render({
        elem: '#LAY_table_user'//指定原始表格元素选择器（
        , url: requestBaseUrl + '/user/list'//数据接口
        , method: 'post'
        , contentType: 'application/json'
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
        , id: 'LAY_table_user'
        , page: true//开启分页
//            ,height: 315//容器高度
        , cols: [[//表头
            {field: 'name', title: '姓名', sort: true, width: 200}
            , {field: 'phone', title: '电话', sort: true, width: 150}
            , {field: 'idCard', title: '身份证号', width: 200}
            , {field: 'qq', title: 'QQ', width: 120}
            , {field: 'wechat', title: '微信'}
            , {field: 'email', title: 'email'}
            , {field: '', title: '操作', align: 'left', toolbar: '#barDemo'}
        ]]
        , done: function (res, curr, count) {
        }
    });


    var active = {
        reload: function(){
            var demoReload = $('#demoReload');

            //执行重载
            table.reload('testReload', {
                page: {
                    curr: 1 //重新从第 1 页开始
                }
                ,where: {
                    key: {
                        id: demoReload.val()
                    }
                }
            });
        }
    };

    //绑定click点击事件
    $('.childrenBody .layui-btn').on('click', function () {
        var othis = $(this), method = othis.data('method');
        // active.add();
        active[method] ? active[method].call(this, othis) : '';
    });

});