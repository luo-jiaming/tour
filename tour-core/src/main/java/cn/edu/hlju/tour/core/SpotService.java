package cn.edu.hlju.tour.core;

import cn.edu.hlju.tour.entity.Spot;
import cn.edu.hlju.tour.entity.SpotComment;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Sole on 2017/3/31.
 */
public interface SpotService {

    String haveSpot(String spotname);

    JSONObject spotByName(String spotname);

    JSONObject spotById(Long id);

    JSONObject getSpotComment(Long id, int pageNum, int size);

    List<Spot> getAllSpot();

    void saveComment(SpotComment comment, HttpServletRequest request);
}
