package cn.edu.hlju.tour.core;

import cn.edu.hlju.tour.entity.User;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Created by lft on 2017/3/14.
 */
public interface UserService {

    User login(String email, String password);

    void regist(User user);

    void sendMail(HttpServletRequest request);

    void verifyCode(HttpServletRequest request, HttpServletResponse response) throws IOException;

    void updatePassword(String email, String password);

    boolean hasEmail(String email);

    boolean isVerifyCode(String verifyCode, String serverVerifyCode);

    boolean isEmailCode(String emailCode, String serverEmailCode);

    Map uploadNick(MultipartFile file, HttpServletRequest request) throws IOException;

    void update(User user);

    JSONObject selectUserByPage(int pageNum, int size, User user);

    void delUser(Long[] ids);

}
