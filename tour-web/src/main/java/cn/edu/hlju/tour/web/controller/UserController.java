package cn.edu.hlju.tour.web.controller;

import cn.edu.hlju.tour.core.UserService;
import cn.edu.hlju.tour.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Created by lft on 2017/3/2.
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 登录
     * @param email
     * @param password
     * @param request
     * @return
     */
    @RequestMapping(value = "login")
    @ResponseBody
    public boolean login(@RequestParam("email")String email, @RequestParam("password") String password, HttpServletRequest request) {
        User user = userService.login(email, password, 0L);
        if (user == null) {
            return false;
        }
        request.getSession().setAttribute("user", user);
        return true;
    }

    /**
     * 退出
     * @param request
     */
    @RequestMapping(value = "logout")
    @ResponseBody
    public void logout(HttpServletRequest request) {
        request.getSession().invalidate();
    }

    /**
     * 注册
     * @param user
     * @param request
     * @return
     */
    @RequestMapping(value = "regist")
    public String regist(User user, HttpServletRequest request) {
        userService.regist(user);
        request.setAttribute("email", user.getEmail());
        return "forward:registerSignin.jsp";
    }

    /**
     * 发送邮件
     * @param request
     */
    @RequestMapping(value = "sendMail")
    @ResponseBody
    public void sendMail(HttpServletRequest request) {
        userService.sendMail(request);
    }

    /**
     * 生成验证码
     * @param request
     * @param response
     */
    @RequestMapping(value = "verifyCode")
    @ResponseBody
    public void verifyCode(HttpServletRequest request, HttpServletResponse response) {
        try {
            userService.verifyCode(request, response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 注册时判断验证码以及邮箱存在
     * @param request
     * @return
     */
    @RequestMapping(value = "confirmVerifyCodeAndEmailExist")
    @ResponseBody
    public String confirmVerifyCodeAndEmailExist(HttpServletRequest request) {
        String serverVerifyCode = (String)request.getSession().getAttribute("verifyCode");
        String verifyCode = request.getParameter("verifyCode");
        if (!userService.isVerifyCode(verifyCode, serverVerifyCode)) {
            return "verifyCodeError";
        }
        String email = request.getParameter("email");
        if (userService.hasEmail(email)) {
            return "emailExist";
        }
        return "success";
    }

    /**
     * 更新密码时判断验证码以及邮箱不存在
     * @param request
     * @return
     */
    @RequestMapping(value = "confirmVerifyCodeAndEmailNotExist")
    @ResponseBody
    public String confirmVerifyCodeAndEmailNotExist(HttpServletRequest request) {
        String serverVerifyCode = (String)request.getSession().getAttribute("verifyCode");
        String verifyCode = request.getParameter("verifyCode");
        if (!userService.isVerifyCode(verifyCode, serverVerifyCode)) {
            return "verifyCodeError";
        }
        String email = request.getParameter("email");
        if (!userService.hasEmail(email)) {
            return "emailNotExist";
        }
        return "success";
    }

    /**
     * 更新密码时的跳转
     * @param request
     * @return
     */
    @RequestMapping(value = "findPassword")
    public String findPassword(HttpServletRequest request) {
        userService.sendMail(request);
        request.setAttribute("email", request.getParameter("email"));
        return "forward:newpass.jsp";
    }

    /**
     * 判断邮箱验证码是否正确
     * @param request
     * @return
     */
    @RequestMapping(value = "confirmEmailCode")
    @ResponseBody
    public String confirmEmailCode(HttpServletRequest request) {
        String serverEmailCode = (String)request.getSession().getAttribute("emailCode");
        String emailCode = request.getParameter("emailCode");
        if (!userService.isEmailCode(emailCode, serverEmailCode)) {
            return "emailCodeError";
        }
        return "success";
    }

    /**
     * 更新密码
     * @param request
     * @return
     */
    @RequestMapping(value = "updatePassword")
    public String updatePassword(HttpServletRequest request) {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        userService.updatePassword(email, password);
        request.setAttribute("email", email);
        return "forward:signin.jsp";
    }

    @RequestMapping(value = "uploadNick")
    @ResponseBody
    public Object uploadNick(@RequestParam("file")MultipartFile file, HttpServletRequest request) throws IOException {
        Map map = userService.uploadNick(file, request);
        return map;
    }

    @RequestMapping(value = "updateUser")
    @ResponseBody
    public void updateUser(HttpServletRequest request) {
        String nick = request.getParameter("nick");
        String gender = request.getParameter("gender");
        User user = (User)request.getSession().getAttribute("user");
        user.setNick(nick);
        user.setGender(gender);
        userService.update(user);
    }

}
