<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

    <title>酒店管理</title>

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
    <script type="text/javascript" src="../resources/js/jquery-easyui-1.2.6/jquery.form.min.js"></script>
    <script type="text/javascript" src="../resources/js/jquery-easyui-1.2.6/locale/easyui-lang-zh_CN.js"></script>

    <script type="text/javascript">
        $(function () {

            initDataGrid();

            initAllSpot();

            $("#searchbtn").click(function() {
                $("#t_hotel").datagrid('load', serializeForm($("#search")));
            });

            $("#clearbtn").click(function() {
                $("#search").form('clear');
                $("#t_hotel").datagrid('load', {});
            });

            saveBtnClick();

        });

        // 加载所有景点
        function initAllSpot() {
            $.ajax({
                type: "POST",
                url: "/manage/getAllSpot",
                success: function(data) {
                    var inner = "";
                    for (var i = 0; i<data.length; i++) {
                        inner += " <option value='" + data[i].id + "'>" + data[i].spotName + "</option>";
                    }
                    $('.spotId').append(inner);
                }
            });
        }

        //初始化数据表格
        function initDataGrid() {
            $('#t_hotel').datagrid({

                //	title : '酒店列表',
                url: '/manage/getHotelList',
                idField: 'id',

                height: 500,
                striped: true,
                rownumbers: true,
                fitColumns: false,
                pagination: true,
                pageSize: 10,
                pageList: [5, 10, 15, 20],
                remoteSort: false,
                columns: [[
                    {
                        checkbox: true,
                        width: 10
                    }, {
                        field: 'spotId',
                        hidden: true
                    }, {
                        field: 'hotelName',
                        title: '酒店名称',
                        width: 200,
                    }, {
                        field: 'hotelIntroduce',
                        title: '酒店介绍',
                        width: 200,
                    }, {
                        field: 'hotelLevel',
                        title: '酒店星级',
                        width: 100,
                        sortable: true
                    }, {
                        field: 'phone',
                        title: '联系电话',
                        width: 100
                    }, {
                        field: 'wifi',
                        title: 'wifi',
                        width: 100,
                        formatter : function(value, record, index) {
                            if (value == 1) {
                                return "有";
                            } else {
                                return "无";
                            }
                        }
                    }, {
                        field: 'bath',
                        title: '淋浴设施',
                        width: 100,
                        formatter : function(value, record, index) {
                            if (value == 1) {
                                return "有";
                            } else {
                                return "无";
                            }
                        }
                    }, {
                        field: 'park',
                        title: '停车场',
                        width: 100,
                        formatter : function(value, record, index) {
                            if (value == 1) {
                                return "有";
                            } else {
                                return "无";
                            }
                        }
                    }, {
                        field: 'restaurant',
                        title: '餐厅',
                        width: 100,
                        formatter : function(value, record, index) {
                            if (value == 1) {
                                return "有";
                            } else {
                                return "无";
                            }
                        }
                    }, {
                        field: 'price',
                        title: '最低房间价格',
                        width: 150,
                        sortable: true
                    }, {
                        field: 'location',
                        title: '地址',
                        width: 250
                    }, {
                        field: 'coordinate',
                        title: '坐标',
                        width: 200,
                    }, {
                        field: 'indexImg',
                        title: '图片',
                        width: 200,
                        align: 'center',
                        formatter : function(value, record, index) {
                            var str = '';
                            str = "<img style='width:50%; height:60px; border-radius: 5%;' src='" + value + "'>";
                            return str;
                        }
                    }
                ]],
                toolbar: [
                    {
                        text: '新增酒店',
                        iconCls: 'icon-add',
                        handler: add
                    }, {
                        text: '删除酒店',
                        iconCls: 'icon-remove',
                        handler: del
                    }, {
                        text: '修改酒店',
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
                title: '新增酒店',
                iconCls: 'icon-add'
            });
            $("#mydialog").dialog('open');
            $("#myform").get(0).reset();
            $("#id").val("");		//设id为空
            validate();
        }

        function edit() {
            flag = 'edit';
            var arr = $("#t_hotel").datagrid('getSelections');
            if (arr.length != 1) {
                $.messager.show({
                    title: '提示信息',
                    msg: '只能选择一行记录进行修改！'
                });
            } else {
                $("#mydialog").dialog({
                    title: '修改酒店',
                    iconCls: 'icon-edit'
                });
                $('#mydialog').dialog('open'); //打开窗口
                $('#myform').get(0).reset();   //清空表单数据
                $('#myform').form('load', {	   //调用load方法把所选中的数据load到表单中
                    id: arr[0].id,
                    hotelName: arr[0].hotelName,
                    hotelIntroduce: arr[0].hotelIntroduce,
                    phone: arr[0].phone,
                    wifi: arr[0].wifi,
                    park: arr[0].park,
                    bath: arr[0].bath,
                    restaurant: arr[0].restaurant,
                    price: arr[0].price,
                    location: arr[0].location,
                    coordinate: arr[0].coordinate,
                    hotelLevel: arr[0].hotelLevel,
                    spotId: arr[0].spotId
                });
            }
            validate();
        }

        //验证
        function validate() {
            $("#hotelName").validatebox({
                required: true,
                missingMessage: '酒店名称必填'
            });
            $("#hotelIntroduce").validatebox({
                required: true,
                missingMessage: '酒店介绍必填'
            });
            $("#phone").validatebox({
                required: true,
                missingMessage: '联系电话必填'
            });
            $("#location").validatebox({
                required: true,
                missingMessage: '位置必填'
            });
            $("#coordinate").validatebox({
                required: true,
                missingMessage: '坐标必填'
            });
        }

        function del() {
            var arr = $("#t_hotel").datagrid('getSelections');
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
                            url: '/manage/delHotel',
                            data: {'ids': ids},
                            success: function (data) {
                                //刷新数据表格
                                $("#t_hotel").datagrid('reload');
                                //清空idField
                                $("#t_hotel").datagrid('unselectAll');
                                $("#t_hotel").datagrid('clearSelections');
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
                    if (flag == 'add' && $('#file').val() == "") {
                        $.messager.show({
                            title: '提示信息',
                            msg: '请上传图片',
                        });
                        return;
                    }
                    ajaxSubmitForm();
                }
            });
        }

        function ajaxSubmitForm() {
            var option = {
                url: flag == 'add' ? '/manage/addHotel' : '/manage/editHotel',
                type: 'POST',
                dataType: 'json',
                headers: {"ClientCallMode" : "ajax"}, //添加请求头部
                success : function(data) {
                    //关闭窗口
                    $("#mydialog").dialog('close');
                    //刷新datagrid
                    $("#t_hotel").datagrid('reload');
                    $.messager.show({
                        title: '提示信息',
                        msg: '操作成功！'
                    });
                },
                error: function(data) {
                    $.messager.show({
                        title: '提示信息',
                        msg: '操作失败！'
                    });
                }
            };
            $("#myform").ajaxSubmit(option);
        }

    </script>

</head>

<body>
<div id="panel" class="easyui-panel" title="查询入口" style="height:70; padding:6; overflow:hidden">

    <form id="search">
        <table style="width:70%">
            <tr>
                <td align="right">酒店:</td>
                <td><input type="text" name="hotelName" value=""/></td>
                <td align="right">景点:</td>
                <td>
                    <select class="spotId" name="spotId">
                        <option value=""></option>
                    </select>
                </td>
                <td align="right"><a id="searchbtn" class="easyui-linkbutton" iconCls="icon-search"></a></td>
                <td align="right"><a id="clearbtn" class="easyui-linkbutton">清空</a></td>
            </tr>
        </table>
    </form>

</div>

<table id="t_hotel"></table>

<div id="mydialog" class="easyui-dialog" closed="true" modal="true" draggable="true" style="width:550px; margin: 20px 40px">

    <form id="myform" method="post" enctype="multipart/form-data">
        <input id="id" type="hidden" name="id" value="" />
        <table>
            <tr>
                <td>酒店名称:</td>
                <td><input id="hotelName" type="text" name="hotelName" value="" style="width:200px" /></td>
            </tr>

            <tr>
                <td>酒店星级:</td>
                <td>
                    <select id="hotelLevel" name="hotelLevel" style="width:200px">
                        <option value="1">一星级</option>
                        <option value="2">二星级</option>
                        <option value="3">三星级</option>
                        <option value="4">四星级</option>
                        <option value="5">五星级</option>
                    </select>
                </td>
            </tr>

            <tr>
                <td>联系电话:</td>
                <td><input id="phone" type="text" name="phone" value="" style="width:200px" /></td>
            </tr>

            <tr>
                <td>Wi-Fi:</td>
                <td>
                    <select id="wifi" name="wifi" style="width:200px">
                        <option value="1">有</option>
                        <option value="0">无</option>
                    </select>
                </td>
            </tr>

            <tr>
                <td>淋浴设施:</td>
                <td>
                    <select id="bath" name="bath" style="width:200px">
                        <option value="1">有</option>
                        <option value="0">无</option>
                    </select>
                </td>
            </tr>

            <tr>
                <td>停车场:</td>
                <td>
                    <select id="park" name="park" style="width:200px">
                        <option value="1">有</option>
                        <option value="0">无</option>
                    </select>
                </td>
            </tr>

            <tr>
                <td>餐厅:</td>
                <td>
                    <select id="restaurant" name="restaurant" style="width:200px">
                        <option value="1">有</option>
                        <option value="0">无</option>
                    </select>
                </td>
            </tr>

            <tr>
                <td>酒店最低价格:</td>
                <td><input id="price" type="text" name="price" value="0" style="width:200px" readonly/></td>
            </tr>

            <tr>
                <td>坐标:</td>
                <td>
                    <input id="coordinate" type="text" name="coordinate" value="" style="width:200px" />
                    <a href="http://lbs.amap.com/console/show/picker" target="_blank">坐标拾取器</a>
                </td>
            </tr>

            <tr>
                <td>地址:</td>
                <td><input id="location" type="text" name="location" value="" style="width:370px" /></td>
            </tr>

            <tr>
                <td>酒店介绍:</td>
                <td>
                    <textarea id="hotelIntroduce" rows="8" cols="50" name="hotelIntroduce"></textarea>
                </td>
            </tr>

            <tr>
                <td>附近景点名称:</td>
                <td>
                    <select id="spotId" class="spotId" name="spotId" style="width:200px"></select>
                </td>
            </tr>

            <tr>
                <td>主页照片:</td>
                <td>
                    <input id="file" type="file" name="file" accept="image/*" />
                </td>
            </tr>

            <tr align="center">
                <td colspan="2"><a id="saveBtn" class="easyui-linkbutton" iconCls="icon-save">保存</a></td>
            </tr>

        </table>
    </form>
</div>

</body>

</html>
