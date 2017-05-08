<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

    <title>首页</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">

    <link rel="stylesheet" type="text/css" href="resources/css/common.css">
    <link rel="stylesheet" type="text/css" href="resources/js/jquery-easyui-1.2.6/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="resources/js/jquery-easyui-1.2.6/themes/icon.css">

    <script type="text/javascript" src="resources/js/jquery-easyui-1.2.6/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="resources/js/jquery-easyui-1.2.6/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="resources/js/jquery-easyui-1.2.6/locale/easyui-lang-zh_CN.js"></script>

    <script type="text/javascript">
        $(function() {

            $("a[title]").click(function() {

                var src = $(this).attr("title");
                var title = $(this).html();
                if ($("#tabs").tabs('exists', title)) {
                    $("#tabs").tabs('select', title);
                } else {
                    $("#tabs").tabs('add', {
                        title : title,
                        content : '<iframe frameborder=0 src=' + src + ' style=width:100%;height:100%></iframe>',
                        closable : true
                    });
                }

            });

        });
    </script>

</head>

<body>

<div class="easyui-layout" fit=true style="width:100%;height:100%">

    <div region="north" title="后台管理系统"  split="false" style="height:100px;">

    </div>

    <div region="west" iconCls="icon-ok" split="false" title="菜单" style="width:200px">

        <div class="easyui-accordion" >
            <div title="用户管理" style="overflow:auto" collapsed="true" selected="true">
                <ul>
                    <li><a title="jsp/user.jsp" class="easyui-linkbutton">用户列表</a></li>
                </ul>
            </div>

            <div title="航班管理" style="overflow:auto">
                <ul>
                    <li><a title="jsp/flight.jsp" class="easyui-linkbutton">航班列表</a></li>
                </ul>
            </div>

            <div title="景点管理" style="overflow:auto">
                <ul>
                    <li><a title="jsp/spot.jsp" class="easyui-linkbutton">景点列表</a></li>
                </ul>
            </div>

            <div title="景点图片管理" style="overflow:auto">
                <ul>
                    <li><a title="jsp/spotImg.jsp" class="easyui-linkbutton">景点图片列表</a></li>
                </ul>
            </div>

            <div title="景点评论管理" style="overflow:auto">
                <ul>
                    <li><a title="jsp/spotComment.jsp" class="easyui-linkbutton">景点评论列表</a></li>
                </ul>
            </div>

            <div title="酒店管理" style="overflow:auto">
                <ul>
                    <li><a title="jsp/hotel.jsp" class="easyui-linkbutton">酒店列表</a></li>
                </ul>
            </div>

            <div title="房间类型管理" style="overflow:auto">
                <ul>
                    <li><a title="jsp/roomType.jsp" class="easyui-linkbutton">房间类型列表</a></li>
                </ul>
            </div>

            <div title="酒店评论管理" style="overflow:auto">
                <ul>
                    <li><a title="jsp/hotelComment.jsp" class="easyui-linkbutton">酒店评论列表</a></li>
                </ul>
            </div>

        </div>

    </div>

    <div region="center" style="padding:0px;">
        <div id="tabs" class="easyui-tabs" fit=true >
            <div title="欢迎" closable=true >
                <img src="resources/img/welcome.gif" style="width:100%; height:100%" />
            </div>
        </div>
    </div>

</div>

</body>

</html>
