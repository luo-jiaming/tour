package cn.edu.hlju.tour.web.controller;

import cn.edu.hlju.tour.core.SpotCommentService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lft on 2017/5/7.
 */
@Controller
public class SpotCommentController {

    @Autowired
    private SpotCommentService spotCommentService;

    @RequestMapping(value= "getSpotCommentList")
    @ResponseBody
    public String getSpotCommentList(int page, int rows, String spotName, String nick) {

        //{"total":10, "row":[{},{}]}
        Map<String, String> map = new HashMap();
        map.put("spotName", spotName);
        map.put("nick", nick);
        JSONObject json = spotCommentService.selectSpotCommentByPage(page, rows, map);
        PageInfo pageInfo = (PageInfo)json.get("pageinfo");
        List<Map> list = (List)json.get("list");
        long total = pageInfo.getTotal();
        String str = JSON.toJSONString(list);
        String jsonStr = "{\"total\":" + total + ", \"rows\":" + str + "}";
        return jsonStr;

    }

    @RequestMapping(value= "delSpotComment")
    @ResponseBody
    public void delSpotComment(@RequestParam("ids") String idsTemp) {
        String[] tempArray = idsTemp.split(",");
        Long[] ids = new Long[tempArray.length];
        for (int i = 0; i<tempArray.length; i++) {
            ids[i] = Long.parseLong(tempArray[i]);
        }
        spotCommentService.delSpotComment(ids);
    }


}
