<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>房屋出租管理后台 - by scrats</title>
    <#include "menu.ftl" />

    <div class="layui-body childrenBody">
        <!-- 内容主体区域 -->
        <div style="padding: 15px;">
            这个是管理员首页
            <br><br>
            <blockquote class="layui-elem-quote">
            <form class="layui-form" lay-filter="landlordSearchFormFilter" action="">
                <div class="layui-inline">
                    <div class="layui-input-inline">
                        <input class="layui-input" name="id" id="demoReload" autocomplete="off">
                    </div>
                    <a class="layui-btn search_btn" data-method="search" data-type="auto">搜索</a>
                </div>
                <div class="layui-inline">
                    <a data-method="add" data-type="auto" class="layui-btn layui-btn-normal">添加</a>
                </div>
            </form>
            </blockquote>

            <hr class="layui-bg-green">

            <table class="layui-hide" id="LAY_table_user" lay-filter="user"></table>

        </div>
    </div>

<#include "footer.ftl"/>

<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">查看</a>
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>

<script src="${base}/static/js/admin/home.js" charset="utf-8"></script>

</html>