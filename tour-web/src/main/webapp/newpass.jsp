<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>设置新密码 - 农家乐</title>
    <link rel="stylesheet" href="resources/css/bootstrap.min.css">
    <script src="resources/js/jquery.min.js"></script>
    <script src="resources/js/bootstrap.min.js"></script>
    <script src="resources/js/holder.min.js"></script>
    <style>
        body {
            background-image: url(resources/img/30.jpeg);
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
            height: 300px;
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

            $('#finish').click(function() {
                if (validate()) {
                    $.ajax({
                        type: "POST",
                        url: "/tour/confirmEmailCode",
                        data: $('#form').serialize(),
                        async: false,
                        success: function(data) {
                            if (data == "success") {
                                $('#tip-content').html('');
                                $('#form').submit();
                                return;
                            }
                            if (data == "emailCodeError") {
                                $('#tip-content').html('邮箱验证码错误');
                                return;
                            }
                        }
                    });
                }
            });

        });

        function validate() {
            if ($('#password').val() == '') {
                $('#tip-content').html('请输入新密码');
                return false;
            }
            if ($('#password').val() != $('#repassword').val()) {
                $('#tip-content').html('密码不一致');
                return false;
            }
            if ($('#emailCode').val() == '') {
                $('#tip-content').html('请输入邮箱验证码');
                return false;
            }
            return true;
        }

    </script>
</head>
<body>

<div class="container">

    <div class="panel panel-default regist-modal">
        <div class="panel-heading regist-title">设置新密码</div>

        <div class="panel-body regist-form">

            <div class="err-tip">
                <span id="tip-content">邮箱验证码已下发</span>
            </div>

            <form id="form" action="/tour/updatePassword" method="post">

                <input type="text" name="email" value="${email}" hidden />

                <div class="form-group">
                    <input type="password" class="form-control" id="password" placeholder="您的密码" name="password">
                </div>

                <div class="form-group">
                    <input type="password" class="form-control" id="repassword" placeholder="确认密码" name="repassword">
                </div>

                <div class="form-group">
                    <input class="form-control" type="text" id="emailCode" placeholder="邮箱验证码" name="emailCode" />
                </div>

                <div>
                    <input id="finish" type="button" class="btn submit-btn btn-warning" value="完成" />
                </div>
            </form>

        </div>
    </div>


</div>

</body>
</html>