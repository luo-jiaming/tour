<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

    <title>用户管理</title>

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

            //初始化数据表格

            $('#t_user').datagrid({

                //	title : '用户列表',
                url: '/manage/getUserList',
                idField: 'id',

                height: 390,
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
                        field: 'nick',
                        title: '昵称',
                        width: 50
                    }, {
                        field: 'email',
                        title: '邮箱',
                        width: 50,
                        sortable: true
                    }, {
                        field: 'gender',
                        title: '性别',
                        width: 50
                    }

                ]],
                toolbar: [
                    {
                        text: '新增用户',
                        iconCls: 'icon-add',
                        handler: add
                    }, {
                        text: '删除用户',
                        iconCls: 'icon-remove',
                        handler: del
                    }, {
                        text: '修改用户',
                        iconCls: 'icon-edit',
                        handler: edit
                    }
                ]

            });

        });

        var flag;		//判断新增和修改

        function add() {
            flag = 'add';
            $("#mydialog").dialog({
                title: '新增用户',
                iconCls: 'icon-add'
            });
            $("#mydialog").dialog('open');
            $("#myform").get(0).reset();
            $("#id").val("");		//设id为空
        }

        function edit() {
            flag = 'edit';
            var arr = $("#t_user").datagrid('getSelections');
            if (arr.length != 1) {
                $.messager.show({
                    title: '提示信息',
                    msg: '只能选择一行记录进行修改！'
                });
            } else {
                $("#mydialog").dialog({
                    title: '修改用户',
                    iconCls: 'icon-edit'
                });
                $('#mydialog').dialog('open'); //打开窗口
                $('#myform').get(0).reset();   //清空表单数据
                $('#myform').form('load', {	   //调用load方法把所选中的数据load到表单中
                    id: arr[0].id,
                    username: arr[0].username,
                    age: arr[0].age,
                    sex: arr[0].sex,
                    degree: arr[0].degree,
                    university: arr[0].university,
                    mobile: arr[0].mobile,
                    email: arr[0].email,
                });
            }
        }

        function del() {
            var arr = $("#t_user").datagrid('getSelections');
            if (arr.length <= 0) {
                $.messager.show({
                    title: '提示信息',
                    msg: '至少选择一条记录进行删除操作！'
                });
            } else {
                var ids = '';
                for (var i = 0; i < arr.length; i++) {
                    ids += arr[i].id + ',';
                }
                ids = ids.substring(0, ids.length - 1);
                $.messager.confirm('提示信息', '确认删除？', function (r) {
                    if (r) {
                        $.ajax({
                            type: 'post',
                            cache: false,
                            url: '/manage/delUser',
                            data: {'ids': ids},
                            success: function (data) {
                                //刷新数据表格
                                $("#t_user").datagrid('reload');
                                //清空idField
                                $("#t_user").datagrid('unselectAll');
                                $("#t_user").datagrid('clearSelections');
                                $.messager.show({
                                    title: '提示信息',
                                    msg: '删除成功！'
                                });
                            },
                            error: function (data) {
                                $.messager.show({
                                    title: '提示信息',
                                    msg: '删除失败！'
                                });
                            }
                        });
                    } else {
                        return;
                    }
                });
            }
        }
    </script>

</head>

<body>
<div id="panel" class="easyui-panel" title="查询入口" style="height:70; padding:6; overflow:hidden">

    <form id="search" action="">
        <table style="width:70%">
            <tr>
                <td align="right">昵称:</td>
                <td><input type="text" name="username" value=""/></td>
                <td align="right">邮箱:</td>
                <td><input type="text" name="university" value=""/></td>
                <td align="right"><a id="searchbtn" class="easyui-linkbutton" iconCls="icon-search"></a></td>
                <td align="right"><a id="clearbtn" class="easyui-linkbutton">清空</a></td>
            </tr>
        </table>
    </form>

</div>


<table id="t_user"></table>


</body>

</html>
