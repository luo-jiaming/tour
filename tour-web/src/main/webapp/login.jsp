<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<link rel="stylesheet" href="resources/css/login.css"/>

<script>

    $(function () {
        /**
         * 登录框模态窗口
         */
        $('.login-modal').jqm({
            modal: true,
            overlay: 20
        });

        $('#signIn').click(function () {
            if (validate()) {
                $.ajax({
                    type: "POST",
                    url: "/tour/login",
                    data: $('#form').serialize(),
                    async: false,
                    success: function (data) {
                        if (data) {
                            location.reload();
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


<div class="login-modal">

    <div class="login-close">
        <span class="glyphicon glyphicon-remove jqmClose"></span>
    </div>

    <div class="login-form">
        <div class="login-title">
            <h4>登录途悠游</h4>
        </div>
        <div class="err-tip">
            <span id="tip-content"></span>
        </div>
        <form id="form" method="post">
            <div class="input-group login-input-email">
                <div class="input-group-addon"><span class="glyphicon glyphicon-user"></span></div>
                <input class="form-control" type="text" id="email" placeholder="您的邮箱" name="email"/>
            </div>
            <div class="input-group login-input-password">
                <div class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></div>
                <input class="form-control" type="password" id="password" placeholder="您的密码" name="password"/>
            </div>
            <div class="login-btn">
                <span class="forget-password"><a href="forgetpass.html">忘记密码？</a></span>
                <button type="button" id="signIn" class="btn btn-warning btn-sm sign-in">登录</button>
            </div>
        </form>
    </div>

    <div class="login-img">
        <img style="width:100%" src="resources/img/login-img.jpeg" alt=""/>
    </div>

</div>