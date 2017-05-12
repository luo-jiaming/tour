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

    <style type="text/css">
        .logo {
            width: 180px;
            height: 50px;
            line-height: 50px;
            text-align: center;
            font-weight: bold;
            font-size: 20px;
            float: left;
            color: #fff;
        }

        .logout {
            float: right;
            padding: 30px 15px 0 0;
            color: #fff;
        }
    </style>

    <script>
        $(function () {
            $.ajax({
                type: "POST",
                url: "/manage/test",
                success: function(data) {
                    alert(data);
                }
            });
        })
    </script>
</head>
<body>


</body>
</html>

