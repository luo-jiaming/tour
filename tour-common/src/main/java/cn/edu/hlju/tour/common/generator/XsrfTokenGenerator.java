package cn.edu.hlju.tour.common.generator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Sole on 2017/3/3.
 */
public interface XsrfTokenGenerator {

    /**
     * 增加 TOKEN
     * @param request
     * @param response
     * @return
     */
    String save(HttpServletRequest request, HttpServletResponse response);

    /**
     * 删除TOKEN
     * @param request
     * @param response
     */
    void remove(HttpServletRequest request, HttpServletResponse response);

    /**
     * 验证TOKEN 是否有效 验证成功应当手动调用 remove 方法 手动从COOKIE中清除TOKEN
     * @param request
     * @param response
     * @return
     */
    boolean validate(HttpServletRequest request, HttpServletResponse response);

}
