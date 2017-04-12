<html><head><meta http-equiv="Content-Type" content="text/html; charset=utf-8"/></head><body>
<form id="alipaysubmit" name="qform" action="${pay_url!''}" method="post">
    <input type="hidden" name="app_id" value="${app_id!''}"/>
    <input type="hidden" name="method" value="${method!''}"/>
    <input type="hidden" name="format" value="${format!''}"/>
    <input type="hidden" name="charset" value="${charset!''}"/>
    <input type="hidden" name="timestamp" value="${timestamp!''}"/>
    <input type="hidden" name="version" value="${version!''}"/>
    <input type="hidden" name="sign" value="${sign!''}"/>
    <input type="hidden" name="sign_type" value="${sign_type!''}"/>
    <input type="hidden" name="biz_content" value="${biz_content!''}"/>
    <input type="submit" value="提交" style="display:none;">
</form><script>document.forms['qform'].submit();
</script>
</body></html>

您的邮箱动态验证码 ${code!''} ,您正在修改途悠游密码,需要进行验证,请勿向任何单位或个人泄漏.