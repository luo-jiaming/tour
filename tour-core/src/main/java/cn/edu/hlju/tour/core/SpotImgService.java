package cn.edu.hlju.tour.core;

import cn.edu.hlju.tour.entity.SpotImg;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by lft on 2017/5/7.
 */
public interface SpotImgService {

    JSONObject selectSpotImgByPage(int pageNum, int size, String spotName);

    void update(SpotImg spotImg);

    String uploadImg(MultipartFile file, HttpServletRequest request) throws IOException;

    void delSpotImg(Long[] ids);

    void addSpotImg(SpotImg spotImg);
}
