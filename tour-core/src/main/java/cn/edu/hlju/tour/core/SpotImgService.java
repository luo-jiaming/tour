package cn.edu.hlju.tour.core;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by lft on 2017/5/7.
 */
public interface SpotImgService {

    JSONObject selectSpotImgByPage(int pageNum, int size, String spotName);

}
