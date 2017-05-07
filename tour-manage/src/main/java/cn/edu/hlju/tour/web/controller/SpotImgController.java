package cn.edu.hlju.tour.web.controller;

import cn.edu.hlju.tour.core.SpotImgService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * Created by lft on 2017/5/7.
 */
@Controller
public class SpotImgController {

    @Autowired
    private SpotImgService spotImgService;

    @RequestMapping(value= "getSpotImgList")
    @ResponseBody
    public String getSpotImgList(int page, int rows, String spotName) {

        //{"total":10, "row":[{},{}]}
        JSONObject json = spotImgService.selectSpotImgByPage(page, rows, spotName);
        PageInfo pageInfo = (PageInfo)json.get("pageinfo");
        List<Map> list = (List)json.get("list");
        long total = pageInfo.getTotal();
        String str = JSON.toJSONString(list);
        String jsonStr = "{\"total\":" + total + ", \"rows\":" + str + "}";
        return jsonStr;

    }

}
