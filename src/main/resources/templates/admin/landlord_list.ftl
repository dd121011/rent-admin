<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>房屋出租 - by scrats</title>
    <#include "menu.ftl" />

    <div class="layui-body childrenBody">

        <blockquote class="layui-elem-quote">
            <form class="layui-form" lay-filter="landlordSearchFormFilter" action="">
                <div class="layui-inline">
                    <div class="layui-input-inline">
                        <input type="text" name="name" id="searchName" placeholder="请输入房东名称" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <div class="layui-input-inline">
                        <input type="text" name="phone" id="searchPhone" placeholder="请输入房东电话" autocomplete="off" class="layui-input">
                    </div>
                    <a class="layui-btn search_btn" data-method="search" data-type="auto">搜索</a>
                </div>
                <div class="layui-inline">
                    <a data-method="add" data-type="auto" class="layui-btn layui-btn-normal">添加</a>
                </div>
            </form>
        </blockquote>
        <hr class="layui-bg-green">
        <table class="layui-hide" id="lay_table_landlord" lay-filter="landlordTableFilter"></table>
    </div>

<#include "footer.ftl"/>

<script type="text/html" id="landlordListBar">
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>

<script src="${base}/static/js/admin/landlord.js" charset="utf-8"></script>

<#include "admin/landlord_edit.ftl"/>

</html>