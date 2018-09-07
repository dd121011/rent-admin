layui.use(['layer', 'form'], function () {
    var $ = layui.jquery;
    var layer = layui.layer;
    var form = layui.form;

    //监听提交
    form.on('submit(landlordEditFormSubmitFilter)', function(data){
        var params = {};
        var fromParams = $(data.form).serializeObject();
        params.body = fromParams;
        console.log(params);
        var jhxhr = $.ajax({url: requestBaseUrl + "/landlord/edit", data: JSON.stringify(params), headers: header, contentType: 'application/json', type: "POST"});
        jhxhr.done(function (res) {
            if(res.code == 1){
                layer.close(1);
                if(params.landlordId){
                    layer.msg("修改成功");

                }else{
                    layer.msg("添加成功");
                }
                table.reload('lay_table_landlord', {
                    page: {
                        curr: 1 //重新从第 1 页开始
                    }
                    , where: {
                        body: {
                            name: $('#searchName').val(),
                            phone: $('#searchPhone').val()
                        }
                    }//传参*/
                });
            }else{
                layer.alert(res.message);
            }
        });

        return false;//阻止表单跳转。如果需要表单跳转，去掉这段即可。
    });

});