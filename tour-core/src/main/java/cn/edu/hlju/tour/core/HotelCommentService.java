package cn.edu.hlju.tour.core;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

/**
 * Created by lft on 2017/5/7.
 */
public interface HotelCommentService {

    JSONObject selectHotelCommentByPage(int pageNum, int size, Map map);

    void delHotelComment(Long[] ids);

}
