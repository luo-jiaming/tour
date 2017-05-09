package cn.edu.hlju.tour.core;

import cn.edu.hlju.tour.entity.Travel;
import cn.edu.hlju.tour.entity.TravelComment;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by lft on 2017/3/24.
 */
public interface TravelService {

    List<Map> limit5();

    Long saveTravel(Travel travel, HttpServletRequest request);

    void delTravel(Long id);

    JSONObject travelById(Long id);

    Map uploadIndexImg(MultipartFile file, HttpServletRequest request) throws IOException;

    Map uploadTravelImg(MultipartFile file, HttpServletRequest request) throws IOException;

    JSONObject queryByPage(String spotName, int pageNum, int size);

    List queryByUserId(Long id);

    JSONObject getTravelComment(Long id, int pageNum, int size);

    void saveComment(TravelComment comment, HttpServletRequest request);

    void delComment(Long id);

    //管理员查看未审核的游记
    //title 查询条件
    JSONObject selectNotAuditTravelByPage(int pageNum, int size, Travel travel);

    //审核
    void audit(String type, String opinion, Travel travel);
}
