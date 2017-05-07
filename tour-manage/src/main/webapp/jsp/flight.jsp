<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

    <title>航班管理</title>

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
                $("#t_flight").datagrid('load', serializeForm($("#search")));
            });

            $("#clearbtn").click(function() {
                $("#search").form('clear');
                $("#t_flight").datagrid('load', {});
            });

            saveBtnClick();

        });

        //初始化数据表格
        function initDataGrid() {
            $('#t_flight').datagrid({

                //	title : '航班列表',
                url: '/manage/getFlightList',
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
                        field: 'name',
                        title: '航班名称',
                        width: 50,
                        sortable: true
                    }, {
                        field: 'fromLoc',
                        title: '起飞城市',
                        width: 50,
                    }, {
                        field: 'toLoc',
                        title: '到达城市',
                        width: 50
                    }, {
                        field: 'startTerminal',
                        title: '起点航站楼',
                        width: 50,
                    }, {
                        field: 'endTerminal',
                        title: '终点航站楼',
                        width: 50
                    }, {
                        field: 'startTime',
                        title: '起飞时间',
                        width: 50,
                    }, {
                        field: 'endTime',
                        title: '到达时间',
                        width: 50
                    }, {
                        field: 'price',
                        title: '价格',
                        width: 50,
                        sortable: true
                    }

                ]],
                toolbar: [
                    {
                        text: '新增航班',
                        iconCls: 'icon-add',
                        handler: add
                    }, {
                        text: '删除航班',
                        iconCls: 'icon-remove',
                        handler: del
                    }, {
                        text: '修改航班',
                        iconCls: 'icon-edit',
                        handler: edit
                    }
                ]
            });
        }

        var flag;		//判断新增和修改

        function add() {
            flag = 'add';
            $("#mydialog").dialog({
                title: '新增航班',
                iconCls: 'icon-add'
            });
            $("#mydialog").dialog('open');
            $("#myform").get(0).reset();
            $("#id").val("");		//设id为空
            validate();
        }

        function edit() {
            flag = 'edit';
            var arr = $("#t_flight").datagrid('getSelections');
            if (arr.length != 1) {
                $.messager.show({
                    title: '提示信息',
                    msg: '只能选择一行记录进行修改！'
                });
            } else {
                $("#mydialog").dialog({
                    title: '修改航班',
                    iconCls: 'icon-edit'
                });
                $('#mydialog').dialog('open'); //打开窗口
                $('#myform').get(0).reset();   //清空表单数据
                $('#myform').form('load', {	   //调用load方法把所选中的数据load到表单中
                    id: arr[0].id,
                    name: arr[0].name,
                    fromLoc: arr[0].fromLoc,
                    toLoc: arr[0].toLoc,
                    startTerminal: arr[0].startTerminal,
                    endTerminal: arr[0].endTerminal,
                    startTime: arr[0].startTime,
                    endTime: arr[0].endTime,
                    price: arr[0].price,
                });
                validate();
            }
        }

        //验证
        function validate() {
            $("#name").validatebox({
                required: true,
                missingMessage: '航班名称必填'
            });
            $("#fromLoc").validatebox({
                required: true,
                missingMessage: '起点城市必填'
            });
            $("#toLoc").validatebox({
                required: true,
                missingMessage: '到达城市必填'
            });
            $("#startTerminal").validatebox({
                required: true,
                missingMessage: '起点航站楼必填'
            });
            $("#endTerminal").validatebox({
                required: true,
                missingMessage: '终点航站楼必填'
            });
            $("#startTime").validatebox({
                required: true,
                missingMessage: '起飞时间必填'
            });
            $("#endTime").validatebox({
                required: true,
                missingMessage: '到达时间必填'
            });
            $("#price").validatebox({
                required: true,
                missingMessage: '价格必填'
            });
        }

        function del() {
            var arr = $("#t_flight").datagrid('getSelections');
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
                            url: '/manage/delFlight',
                            data: {'ids': ids},
                            success: function (data) {
                                //刷新数据表格
                                $("#t_flight").datagrid('reload');
                                //清空idField
                                $("#t_flight").datagrid('unselectAll');
                                $("#t_flight").datagrid('clearSelections');
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

        function saveBtnClick() {
            $("#saveBtn").click(function() {
                if (!$("#myform").form('validate')) {
                    $.messager.show({
                        title: '提示信息',
                        msg: '验证没有通过, 不能提交表单',
                    });
                } else {
                    $.ajax({
                        type: 'post',
                        url: flag == 'add' ? '/manage/addFlight' : '/manage/editFlight',
                        cache: false,
                        data: $("#myform").serialize(),
                        success: function(data) {
                            //关闭窗口
                            $("#mydialog").dialog('close');
                            //刷新datagrid
                            $("#t_flight").datagrid('reload');
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
        }

    </script>

</head>

<body>
<div id="panel" class="easyui-panel" title="查询入口" style="height:70; padding:6; overflow:hidden">

    <form id="search">
        <table style="width:70%">
            <tr>
                <td align="right">航班:</td>
                <td><input type="text" name="name" value=""/></td>
                <td align="right">起飞城市:</td>
                <td><input type="text" name="fromLoc" value=""/></td>
                <td align="right">到达城市:</td>
                <td><input type="text" name="toLoc" value=""/></td>
                <td align="right"><a id="searchbtn" class="easyui-linkbutton" iconCls="icon-search"></a></td>
                <td align="right"><a id="clearbtn" class="easyui-linkbutton">清空</a></td>
            </tr>
        </table>
    </form>

</div>

<table id="t_flight"></table>

<div id="mydialog" class="easyui-dialog" closed="true" modal="true" draggable="true" style="width:300px; margin: 20px 40px">

    <form id="myform" method="post">
        <input id="id" type="hidden" name="id" value="" />
        <table>
            <tr>
                <td>航班名称:</td>
                <td><input id="name" type="text" name="name" value="" /></td>
            </tr>

            <tr>
                <td>起飞城市:</td>
                <td><input id="fromLoc" type="text" name="fromLoc" value="" /></td>
            </tr>

            <tr>
                <td>到达城市:</td>
                <td><input id="toLoc" type="text" name="toLoc" value="" /></td>
            </tr>

            <tr>
                <td>起点航站楼:</td>
                <td><input id="startTerminal" type="text" name="startTerminal" value="" /></td>
            </tr>

            <tr>
                <td>终点航站楼:</td>
                <td><input id="endTerminal" type="text" name="endTerminal" value="" /></td>
            </tr>

            <tr>
                <td>起飞时间:</td>
                <td><input id="startTime" type="text" name="startTime" value="" /></td>
            </tr>

            <tr>
                <td>到达时间:</td>
                <td><input id="endTime" type="text" name="endTime" value="" /></td>
            </tr>

            <tr>
                <td>票价:</td>
                <td><input id="price" type="text" name="price" value="" /></td>
            </tr>

            <tr align="center">
                <td colspan="2"><a id="saveBtn" class="easyui-linkbutton" iconCls="icon-save">保存</a></td>
            </tr>

        </table>
    </form>
</div>

</body>

</html>
