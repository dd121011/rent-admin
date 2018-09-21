<div id="addDiv" style="padding: 20px 0; padding-right: 40px; display: none" >
    <form class="layui-form" id="userEditForm" lay-filter="userFormFilter" action="">
        <div class="layui-form-item" style="display: none">
            <label class="layui-form-label">id</label>
            <div class="layui-input-block">
                <input type="hidden" name="userId" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div id="ff"></div>
        <div class="layui-form-item">
            <label class="layui-form-label">姓名</label>
            <div class="layui-input-block">
                <input type="text" name="name" required  lay-verify="required" placeholder="请输入房东名称" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">手机号码</label>
            <div class="layui-input-block">
                <input type="text" name="phone"  lay-verify="phone" placeholder="请输入手机号码" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">身份证</label>
            <div class="layui-input-block">
                <input type="text" name="idCard" required  lay-verify="identity" placeholder="请输入身份证号" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">备注</label>
            <div class="layui-input-block">
                <textarea name="remark" placeholder="请输入内容" class="layui-textarea"></textarea>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit="" lay-filter="userEditFormSubmitFilter">立即提交</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </form>
</div>

<script type="text/html" id="userEditUserRoleTemplete">
    <div class="layui-form-item" id="roleCodeDiv">
        <label class="layui-form-label">类型</label>
        <div class="layui-input-block">
            <select id="roleCode"  name="roleCode" lay-search="">
                    <#list roles as item>
                        <option value="${item.dicItermCode}">${item.value}</option>
                    </#list>
            </select>
        </div>
    </div>
</script>

<script src="${base}/static/js/user/user_edit.js" charset="utf-8"></script>