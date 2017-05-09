package cn.edu.hlju.tour.web.controller;

import cn.edu.hlju.tour.core.TravelService;
import cn.edu.hlju.tour.entity.Travel;
import cn.edu.hlju.tour.entity.User;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by lft on 2017/5/8.
 */
@Controller
public class TravelController {

    @Autowired
    private TravelService travelService;

    @RequestMapping(value= "getTravelList")
    @ResponseBody
    public String getUserList(int page, int rows, Travel travel) {
        //{"total":10, "row":[{},{}]}
        JSONObject json = travelService.selectNotAuditTravelByPage(page, rows, travel);
        PageInfo pageInfo = (PageInfo)json.get("pageinfo");
        List<User> list = (List)json.get("list");
        long total = pageInfo.getTotal();
        String str = JSON.toJSONString(list);
        String jsonStr = "{\"total\":" + total + ", \"rows\":" + str + "}";
        return jsonStr;
    }

    /**
     * 审核
     * @param opinion
     * @param type   pass refuse
     * @param travel
     */
    @RequestMapping(value= "audit")
    @ResponseBody
    public void audit(String type, String opinion, Travel travel) {
        travelService.audit(type, opinion, travel);
    }


}
