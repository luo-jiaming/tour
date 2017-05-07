package cn.edu.hlju.tour.common.utils;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * Created by Sole on 2017/3/27.
 */
public class UploadUtils {

    /**
     *
     * @param file
     * @param request
     * @param path      a/b/
     * @return  返回该文件在该项目中的位置
     * @throws IOException
     */
    public static String uploadFile(MultipartFile file, HttpServletRequest request, String path) throws IOException {
        String relativePath = "";
        if (!file.isEmpty()) {
            String[] str = file.getOriginalFilename().split("\\.");
            String filepath = request.getSession().getServletContext().getRealPath("/") + path;
            File f = new File(filepath);
            if (!f.exists()) {
                f.mkdirs();
            }
            f = File.createTempFile("upload", "."+str[str.length-1], f);
            file.transferTo(f);
            String contextPath = request.getSession().getServletContext().getContextPath();
            relativePath = AddrUtils.getUrl(request) + contextPath + "/" + path + f.getName();
        }
        return relativePath;
    }

}
