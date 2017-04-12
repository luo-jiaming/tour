package cn.edu.hlju.tour.core;

import cn.edu.hlju.tour.entity.HotelComment;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Sole on 2017/4/10.
 */
public interface HotelService {

    JSONObject query(String spotName);

    JSONObject hotelById(Long id);

    JSONObject getHotelComment(Long id, int pageNum, int size);

    void saveComment(HotelComment comment, HttpServletRequest request);

}
