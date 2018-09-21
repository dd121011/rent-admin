<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>房屋出租 - by scrats</title>
    <#include "menu.ftl" />

    <div class="layui-body childrenBody">
        <blockquote class="layui-elem-quote">
            <form class="layui-form" lay-filter="userSearchFormFilter" action="">
                <div class="layui-inline">
                    <div class="layui-input-inline">
                        <input type="text" name="name" id="searchName" placeholder="请输入姓名" autocomplete="off" class="layui-input">
                    </div>
                    <div class="layui-input-inline">
                        <input type="text" name="phone" id="searchPhone" placeholder="请输入电话" autocomplete="off" class="layui-input">
                    </div>
                    <div class="layui-input-inline">
                        <select id="searchRoleCode"  lay-search="">
                            <option value="" selected>全部</option>
                            <#list roles as item>
                                <option value="${item.dicItermCode}">${item.value}</option>
                            </#list>
                        </select>
                    </div>
                    <div class="layui-input-inline">
                        <select id="searchCheckTs"  lay-search="">
                            <option value="-1" selected>全部</option>
                            <option value="1">已认证</option>
                            <option value="0">未认证</option>
                        </select>
                    </div>
                </div>
                <div class="layui-inline">
                    <a class="layui-btn search_btn" data-method="search" data-type="auto">搜索</a>
                    <a data-method="add" data-type="auto" class="layui-btn layui-btn-normal">添加</a>
                </div>
            </form>
        </blockquote>
        <hr class="layui-bg-green">
        <table class="layui-hide" id="lay_table_user" lay-filter="userTableFilter"></table>
    </div>

<#include "footer.ftl"/>

<script type="text/html" id="userListBar">
    <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="detail">详情</a>
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
    {{#  if(d.checkTs == 0){ }}
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="realCheck">实名认证</a>
    {{#  } }}
</script>

<script src="${base}/static/js/user/user.js" charset="utf-8"></script>

<#include "user/user_edit.ftl"/>
<#include "user/user_detail.ftl"/>

</html>