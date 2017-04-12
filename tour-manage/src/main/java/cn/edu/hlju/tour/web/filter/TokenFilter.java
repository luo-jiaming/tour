package cn.edu.hlju.tour.web.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by Sole on 2017/3/3.
 */
public class TokenFilter implements Filter {

    private final static String TOEKN_NAME = "token";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("filter init");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

//        HttpServletRequest req = (HttpServletRequest) request;
//        HttpServletResponse resp = (HttpServletResponse) response;
//        String indexUrl = AddrUtils.getIndexUrl(req);
//
//        String action = req.getParameter("action");
//
//        System.out.println(req.getRequestURL().toString());
//
//        if (indexUrl.equals(req.getRequestURL().toString())) {
//            chain.doFilter(request, response);
//            return;
//        }
//
////        if ("login".equals(action) || "logsout".equals(action)) {
////            chain.doFilter(request, response);
////            return;
////        }
//
//        //检查token
//        Cookie cookie = CookieUtils.getCookie(req, TOEKN_NAME);
//        if (cookie == null) {
//            resp.sendRedirect(indexUrl);
//            return;
//        }
//
//        //验证token
//        String token = cookie.getValue();
//        if (!JWTUtils.validate(token, "tuyouyou", "user")) {
//            resp.sendRedirect(indexUrl);
//            return;
//        }
//        req.setAttribute("token", token);
        chain.doFilter(request, response);

    }

    @Override
    public void destroy() {
        System.out.println("filter destroy");
    }

}
