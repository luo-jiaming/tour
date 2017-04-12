<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title></title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <link rel="stylesheet" href="resources/css/bootstrap.min.css">
    <script src="resources/js/jquery.min.js"></script>
    <script src="resources/js/bootstrap.min.js"></script>
    <style>

        .btn:focus, .btn:active:focus, .btn.active:focus, .btn.focus, .btn:active.focus, .btn.active.focus {
            outline: none;
        }

        body {
            background-image: url(resources/img/regist-img.jpeg);
            background-size: 100%;
            margin: 0px;
            padding: 0px;
            overflow: hidden;
        }

        .check-modal {
            z-index: 9999;
            position: fixed;
            top: 30%;
            left: 36%;
            width: 350px;
            height: 200px;
            border-radius: 5px;
            border: solid 1px #bbbbbb;
            background-color: #ffffff;
            box-shadow: 0 0 10px #bbbbbb;
            text-align: center;
        }

        .check-icon {
            width: 50%;
            height: 50px;
            margin: 0 auto;
            font-size: 50px;
            color: #a1a1a1;
        }

        .check-tip {
            margin: 20px auto;
            color: #ff9d00;
            font-size: 20px;
        }

        .btn-style {
            background-color: #ff8d00;
            color: white;
        }

    </style>
</head>
<body>

<div class="container">

    <div class="panel panel-default check-modal">

        <div class="panel-body">
            <div class="check-icon">
                <span class="glyphicon glyphicon-tree-deciduous"></span>
            </div>
            <div class="check-tip"><strong>文章正在审核中……</strong></div>
            <div class="btn-div">
                <a href="/tour" class="btn btn-default" role="button">&lt; 返回首页</a>
                <a href="/tour/travel?id=${travelid}" class="btn btn-style btn-warning" role="button">查看我的文章 &gt;</a>
            </div>
        </div>

    </div>

</div>

</body>
</html>
