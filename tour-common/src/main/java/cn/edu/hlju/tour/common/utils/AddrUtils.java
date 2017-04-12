package cn.edu.hlju.tour.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;

/**
 * Created by Sole on 2017/3/3.
 */
public class AddrUtils {

    private static Logger logger = LoggerFactory.getLogger(AddrUtils.class);

    public static String getIndexUrl(HttpServletRequest request) {
        String url = request.getRequestURL().toString();
        String context = request.getContextPath();
        URI uri = URI.create(url);
        String indexUrl = String.format("%s://%s%s/", uri.getScheme(), uri.getAuthority(), context);
        System.out.println(uri.getAuthority());
        System.out.println(context);
        logger.info("############### index 页面 为" + indexUrl);
        return indexUrl;
    }

}
