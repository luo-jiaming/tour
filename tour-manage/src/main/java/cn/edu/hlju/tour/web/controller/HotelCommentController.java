package cn.edu.hlju.tour.web.controller;

import cn.edu.hlju.tour.core.HotelCommentService;
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
public class HotelCommentController {

    @Autowired
    private HotelCommentService hotelCommentService;

    @RequestMapping(value= "getHotelCommentList")
    @ResponseBody
    public String getHotelCommentList(int page, int rows, String hotelName, String nick) {

        //{"total":10, "row":[{},{}]}
        Map<String, String> map = new HashMap();
        map.put("hotelName", hotelName);
        map.put("nick", nick);
        JSONObject json = hotelCommentService.selectHotelCommentByPage(page, rows, map);
        PageInfo pageInfo = (PageInfo)json.get("pageinfo");
        List<Map> list = (List)json.get("list");
        long total = pageInfo.getTotal();
        String str = JSON.toJSONString(list);
        String jsonStr = "{\"total\":" + total + ", \"rows\":" + str + "}";
        return jsonStr;

    }

    @RequestMapping(value= "delHotelComment")
    @ResponseBody
    public void delHotelComment(@RequestParam("ids") String idsTemp) {
        String[] tempArray = idsTemp.split(",");
        Long[] ids = new Long[tempArray.length];
        for (int i = 0; i<tempArray.length; i++) {
            ids[i] = Long.parseLong(tempArray[i]);
        }
        hotelCommentService.delHotelComment(ids);
    }


}
