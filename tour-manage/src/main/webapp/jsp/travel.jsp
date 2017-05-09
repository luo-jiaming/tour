<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

    <title>游记审核</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">

    <link rel="stylesheet" type="text/css" href="../resources/css/common.css">
    <link rel="stylesheet" type="text/css" href="../resources/js/jquery-easyui-1.2.6/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="../resources/js/jquery-easyui-1.2.6/themes/icon.css">

    <script type="text/javascript" src="../resources/js/jquery-easyui-1.2.6/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="../resources/js/jquery-easyui-1.2.6/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="../resources/js/jquery-easyui-1.2.6/locale/easyui-lang-zh_CN.js"></script>

    <script type="text/javascript">
        $(function () {

            initDataGrid();

            $("#searchbtn").click(function() {
                $("#t_travel").datagrid('load', serializeForm($("#search")));
            });

            $("#clearbtn").click(function() {
                $("#search").form('clear');
                $("#t_travel").datagrid('load', {});
            });

            auditBtnClick();

        });

        //初始化数据表格
        function initDataGrid() {
            $('#t_travel').datagrid({

                //  title : '待审核游记列表',
                url: '/manage/getTravelList',
                idField: 'id',

                height: 500,
                striped: true,
                rownumbers: true,
                fitColumns: true,
                pagination: true,
                pageSize: 10,
                pageList: [5, 10, 15, 20],
                remoteSort: false,
                columns: [[
                    {
                        checkbox: true,
                        width: 10
                    }, {
                        field: 'userId',
                        hidden: true
                    }, {
                        field: 'title',
                        title: '游记标题(点击标题进入)',
                        width: 50,
                        align: 'center',
                        formatter : function(value, record, index) {
                            var str = '';
                            str = "<a href='http://localhost:8081/tour/travel?id=" + record.id + "' target='_blank'>" + value + "</a>";
                            return str;
                        }
                    }
                ]],
                toolbar: [
                    {
                        text: '审核游记',
                        iconCls: 'icon-edit',
                        handler: audit
                    }
                ]
            });
        }

        function audit() {
            var arr = $("#t_travel").datagrid('getSelections');
            if (arr.length != 1) {
                $.messager.show({
                    title: '提示信息',
                    msg: '只能选择一行记录进行修改！'
                });
            } else {
                $("#mydialog").dialog({
                    title: '审核游记',
                    iconCls: 'icon-edit'
                });
                $('#mydialog').dialog('open'); //打开窗口
                $('#myform').get(0).reset();   //清空表单数据
                $('#myform').form('load', {    //调用load方法把所选中的数据load到表单中
                    id: arr[0].id,
                    userId: arr[0].userId
                });
                $("#opinion").validatebox({
                    required: true,
                    missingMessage: '审核意见必填'
                });
            }
        }

        //表单的序列化
        function serializeForm(form){
            var obj = {};
            $.each(form.serializeArray(), function(index){
                if (obj[this['name']]) {
                    obj[this['name']] = obj[this['name']] + ',' + this['value'];
                } else {
                    obj[this['name']] = this['value'];
                }
            });
            return obj;
        }

        function auditBtnClick() {
            $(".auditBtn").each(function() {
                $(this).click(function() {
                    if (!$("#myform").form('validate')) {
                        $.messager.show({
                            title: '提示信息',
                            msg: '验证没有通过, 不能提交表单',
                        });
                    } else {
                        var type = $(this).attr('name');
                        $.ajax({
                            type: 'post',
                            url: '/manage/audit?type=' + type,
                            cache: false,
                            data: $("#myform").serialize(),
                            success: function(data) {

                                //刷新数据表格
                                $("#t_travel").datagrid('reload');
                                //清空idField
                                $("#t_travel").datagrid('unselectAll');
                                $("#t_travel").datagrid('clearSelections');

                                //关闭窗口
                                $("#mydialog").dialog('close');

                                $.messager.show({
                                    title: '提示信息',
                                    msg: '操作成功！'
                                });
                            },
                            error : function(data) {
                                $.messager.show({
                                    title: '提示信息',
                                    msg: '操作失败！'
                                });
                            }
                        });
                    }
                });
            });
        }


    </script>

</head>

<body>
<div id="panel" class="easyui-panel" title="查询入口" style="height:70; padding:6; overflow:hidden">

    <form id="search">
        <table style="width:70%">
            <tr>
                <td align="right">游记标题:</td>
                <td><input type="text" name="title" value=""/></td>
                <td align="right"><a id="searchbtn" class="easyui-linkbutton" iconCls="icon-search"></a></td>
                <td align="right"><a id="clearbtn" class="easyui-linkbutton">清空</a></td>
            </tr>
        </table>
    </form>

</div>

<table id="t_travel"></table>

<div id="mydialog" class="easyui-dialog" closed="true" modal="true" draggable="true" style="width:360px; margin: 20px 40px">

    <form id="myform" method="post">
        <input id="id" type="hidden" name="id" value="" />
        <input id="userId" type="hidden" name="userId" value="" />
        <table>
            <tr>
                <td>审核意见:</td>
                <td><input id="opinion" type="text" name="opinion" value="" style="width:200px" /></td>
            </tr>

            <tr align="center">
                <td colspan="2">
                    <a id="passBtn" class="easyui-linkbutton auditBtn" name="pass">通过</a>
                    <a id="refuseBtn" class="easyui-linkbutton auditBtn" name="refuse">不通过</a>
                </td>
            </tr>
        </table>
    </form>

</div>

</body>

</html>
