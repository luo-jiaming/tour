package cn.edu.hlju.tour.common.generator.impl;

import cn.edu.hlju.tour.common.generator.XsrfTokenGenerator;
import cn.edu.hlju.tour.common.utils.CookieUtils;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * Created by Sole on 2017/3/3.
 */
public class SimpleXsrfTokenGenerator implements XsrfTokenGenerator {

    private final static String FORM_TOEKN_NAME = "_form_token";

    @Override
    public String save(HttpServletRequest request, HttpServletResponse response) {
        String value = StringUtils.remove(UUID.randomUUID().toString(), "-");
        if (value != null) {
            set(request, response, value);
        }
        return value;
    }

    @Override
    public void remove(HttpServletRequest request, HttpServletResponse response) {

    }

    @Override
    public boolean validate(HttpServletRequest request, HttpServletResponse response) {
        String param = request.getParameter(FORM_TOEKN_NAME);
        if (StringUtils.isBlank(param)) {
            return false;
        }
        String value = get(request);
        if (StringUtils.isBlank(value)) {
            return false;
        }
        return value.equals(param);
    }

    /**
     * 从cookie中获取TOKEN
     *
     * @param request
     * @return
     */
    private String get(HttpServletRequest request) {
        Cookie cookie = CookieUtils.getCookie(request, FORM_TOEKN_NAME);
        return cookie == null ? null : cookie.getValue();
    }

    /**
     * 设置 TOKEN 到 COOKIE 当中
     *
     * @param request
     * @param response
     * @param value
     */
    private void set(HttpServletRequest request, HttpServletResponse response,
                     String value) {
        if (value != null) {
            CookieUtils.addCookie(request, response, FORM_TOEKN_NAME, value,
                    -1, null);
            request.setAttribute(FORM_TOEKN_NAME, value);
        }
    }

}
