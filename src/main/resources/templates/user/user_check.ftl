<div id="userCheckDiv" style="padding: 10px 40px; display: none" >
    <form class="layui-form" lay-filter="userCheckFormFilter" action="">
        <table class="layui-table" style="margin: 0" lay-even lay-skin="line">
            <tbody id="userCheckTableTbody"></tbody>
        </table>
    </form>
</div>

<script type="text/html" id="userCheckTemplete">
    <tr>
        <th>姓名：</th>
        <td>{{ d.name }}</td>
        <th>性别：</th>
        <td>{{ d.sexName }}</td>
    </tr>
    <tr>
        <th>手机号码：</th>
        <td>{{ d.phone }}</td>
        <th>身份证号：</th>
        <td>{{ d.idCard }}</td>
    </tr>
    <tr>
        <th>身份证正面：</th>
        <td><img src="{{ d.idCardPic }}"></td>
        <th>身份证反面：</th>
        <td><img src="{{ d.idCardPicBack }}"></td>
    </tr>
    <tr>
        <th>qq：</th>
        <td>{{ d.qq }}</td>
        <th>微信：</th>
        <td>{{ d.wechat }}</td>
    </tr>
    <tr>
        <th>籍贯：</th>
        <td>{{ d.hometown }}</td>
        <th>地址：</th>
        <td>{{ d.address }}</td>
    </tr>
    <tr>
        <th>认证时间：</th>
        <td>{{ d.checkDate }}</td>
        <th>创建时间：</th>
        <td>{{ d.createDate }}</td>
    </tr>
    <tr>
        <th>备注：</th>
        <td colspan="3">{{ d.remark }}</td>
    </tr>
</script>