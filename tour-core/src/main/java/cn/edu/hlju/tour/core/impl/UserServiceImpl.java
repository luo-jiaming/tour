package cn.edu.hlju.tour.core.impl;

import cn.edu.hlju.tour.common.utils.MD5Utils;
import cn.edu.hlju.tour.common.utils.MailUtils;
import cn.edu.hlju.tour.common.utils.UploadUtils;
import cn.edu.hlju.tour.common.utils.VerifyCodeUtils;
import cn.edu.hlju.tour.core.UserService;
import cn.edu.hlju.tour.core.utils.MailConfig;
import cn.edu.hlju.tour.dao.UserMapper;
import cn.edu.hlju.tour.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sole on 2017/3/14.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MailConfig mailConfig;

    /**
     * 登录页面按钮的提交
     * @param email
     * @param password
     * @return
     */
    @Override
    public User login(String email, String password) {
        String sign = MD5Utils.sign(password, "utf-8");
        return userMapper.selectByEmailAndPassword(email, sign);
    }

    /**
     * 注册页面按钮的提交
     * @param user
     * @return
     */
    @Override
    public void regist(User user) {
        String sign = MD5Utils.sign(user.getPassword(), "utf-8");
        user.setPassword(sign);
        user.setPermission(0L);
        userMapper.insertSelective(user);
    }

    /**
     * 发送邮箱验证码
     * @param request
     * @return
     */
    @Override
    public void sendMail(HttpServletRequest request) {
        String emailCode = VerifyCodeUtils.generateVerifyCode(6);
        request.getSession().setAttribute("emailCode", emailCode.toLowerCase());
        System.out.println(emailCode);
        String subject = "验证码";
        String content = "您的验证码 " + emailCode;
        String senderEmail = mailConfig.getSenderEmail();
        String senderPassword = mailConfig.getSenderPassword();
        String senderEmailSMTPHost = mailConfig.getSenderEmailSMTPHost();
        String smtpAuth = mailConfig.getSmtpAuth();
        String transportProtocol = mailConfig.getTransportProtocol();
        MailUtils.send(transportProtocol, senderEmailSMTPHost, smtpAuth, senderEmail, "途悠游", senderPassword,
            request.getParameter("email"), request.getParameter("nick"), subject, content, "utf-8");
    }

    /**
     * 发送验证码图片
     * @param request
     * @param response
     * @throws IOException
     */
    @Override
    public void verifyCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");

        //生成随机字串
        String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
        //存入会话session
        request.getSession().setAttribute("verifyCode", verifyCode.toLowerCase());
        //生成图片
        int w = 96, h = 33;
        VerifyCodeUtils.outputImage(w, h, response.getOutputStream(), verifyCode);
    }

    /**
     * 更新密码
     * @param email
     * @param password
     */
    @Override
    public void updatePassword(String email, String password) {
        String sign = MD5Utils.sign(password, "utf-8");
        userMapper.updatePasswordByEmail(email, sign);
    }

    /**
     * email是否存在
     * @param email
     * @return
     */
    @Override
    public boolean hasEmail(String email) {
        User userDto = userMapper.selectByEmail(email);
        if (userDto == null) {
            return false;
        }
        return true;
    }

    /**
     * 验证码是否正确
     * @param verifyCode
     * @param serverVerifyCode
     * @return
     */
    @Override
    public boolean isVerifyCode(String verifyCode, String serverVerifyCode) {
        return verifyCode.equals(serverVerifyCode);
    }

    /**
     * 邮箱验证码是否正确
     * @param emailCode
     * @param serverEmailCode
     * @return
     */
    @Override
    public boolean isEmailCode(String emailCode, String serverEmailCode) {
        return emailCode.equals(serverEmailCode);
    }

    /**
     * 上传头像
     * @param file
     * @param request
     * @return
     * @throws IOException
     */
    @Override
    public Map uploadNick(MultipartFile file, HttpServletRequest request) throws IOException {
        User user = (User)request.getSession().getAttribute("user");
        String contextPath = UploadUtils.uploadFile(file, request, "user/avatar/");
        Map<String, String> map = new HashMap<>();
        map.put("src", contextPath);
        if (user != null) {
            user.setAvatar(contextPath);
            userMapper.updateByPrimaryKeySelective(user);
        }
        return map;
    }

    @Override
    public void update(User user) {
        userMapper.updateByPrimaryKeySelective(user);
    }

}
