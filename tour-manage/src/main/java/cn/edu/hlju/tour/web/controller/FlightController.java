package cn.edu.hlju.tour.web.controller;

import cn.edu.hlju.tour.core.FlightService;
import cn.edu.hlju.tour.entity.Flight;
import cn.edu.hlju.tour.entity.User;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by lft on 2017/5/7.
 */
@Controller
public class FlightController {

    @Autowired
    private FlightService flightService;

    @RequestMapping(value= "getFlightList")
    @ResponseBody
    public String getList(int page, int rows, Flight flight) {

        //{"total":10, "row":[{},{}]}
        JSONObject json = flightService.selectFlightByPage(page, rows, flight);
        PageInfo pageInfo = (PageInfo)json.get("pageinfo");
        List<User> list = (List)json.get("list");
        long total = pageInfo.getTotal();
        String str = JSON.toJSONString(list);
        String jsonStr = "{\"total\":" + total + ", \"rows\":" + str + "}";
        return jsonStr;

    }

    @RequestMapping(value= "editFlight")
    @ResponseBody
    public void editFlight(Flight flight) {
        flightService.update(flight);
    }

    @RequestMapping(value= "delFlight")
    @ResponseBody
    public void delFlight(@RequestParam("ids") String idsTemp) {
        String[] tempArray = idsTemp.split(",");
        Long[] ids = new Long[tempArray.length];
        for (int i = 0; i<tempArray.length; i++) {
            ids[i] = Long.parseLong(tempArray[i]);
        }
        flightService.delFlight(ids);
    }

    @RequestMapping(value= "addFlight")
    @ResponseBody
    public void addFlight(Flight flight) {
        flightService.addFlight(flight);
    }

}
