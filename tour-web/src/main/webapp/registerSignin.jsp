<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>账号登录 - 农家乐</title>
    <link rel="stylesheet" href="resources/css/bootstrap.min.css">
    <script src="resources/js/jquery.min.js"></script>
    <script src="resources/js/bootstrap.min.js"></script>
    <script src="resources/js/holder.min.js"></script>
    <style>
        body {
            background-image: url(resources/img/12.jpeg);
            background-size: 100%;
            margin: 0px;
            padding: 0px;
            overflow: hidden;
        }

        .btn:focus, .btn:active:focus, .btn.active:focus, .btn.focus, .btn:active.focus, .btn.active.focus {
            outline: none;
        }

        .regist-modal {
            z-index: 9999;
            position: fixed;
            top: 23%;
            left: 36%;
            width: 350px;
            height: 260px;
            border-radius: 5px;
            border: solid 1px #bbbbbb;
            background-color: #ffffff;
            box-shadow: 0 0 10px #bbbbbb;
        }

        .regist-title {
            height: 55px;
            font-size: 20px;
            padding: 15px;
        }

        .err-tip {
            height: 35px;
            color:red;
            font-size: 12px;
            padding-top:10px;
        }

        .regist-form {
            padding-top: 0px;
            padding-left: 30px;
            padding-right: 30px;
        }

        .submit-btn {
            width: 100%;
            height: 42px;
            background-color: #ffa800;
            color: white;
            font-size: 18px;
            font-weight: 800;
        }

        .submit-btn:hover {
            background-color: #ffb400;
            color: white;
        }

    </style>
    <script>

        $(function() {

            $('#signin').click(function() {
                if (validate()) {
                    $.ajax({
                        type: "POST",
                        url: "/tour/login",
                        data: $('#form').serialize(),
                        async: false,
                        success: function(data) {
                            if (data) {
                                $('#tip-content').html('');
                                window.location.href = 'http://localhost:8081/tour';
                            } else {
                                $('#tip-content').html('用户名或密码不正确');
                            }
                        }
                    });
                }
            });

        });

        function validate() {
            if ($('#email').val() == '') {
                $('#tip-content').html('请输入用户名');
                return false;
            }
            if ($('#password').val() == '') {
                $('#tip-content').html('请输入密码');
                return false;
            }
            return true;
        }

    </script>
</head>
<body>

<div class="container">

    <div class="panel panel-default regist-modal">
        <div class="panel-heading regist-title">注册成功，请登录</div>

        <div class="panel-body regist-form">

            <div class="err-tip">
                <span id="tip-content"></span>
            </div>

            <form id="form" method="post">
                <div class="form-group">
                    <input type="text" class="form-control" id="email" value="${email}" name="email">
                </div>

                <div class="form-group">
                    <input type="password" class="form-control" id="password" placeholder="您的密码" name="password">
                </div>

                <div>
                    <input id="signin" type="button" class="btn btn-warning submit-btn" value="登录" />
                </div>
            </form>

        </div>
    </div>


</div>

</body>
</html>