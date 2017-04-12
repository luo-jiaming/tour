package cn.edu.hlju.tour.web.controller;

import cn.edu.hlju.tour.core.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Sole on 2017/4/1.
 */
@Controller
public class FlightController {

    @Autowired
    private FlightService flightService;

    @RequestMapping(value = "flight")
    @ResponseBody
    public List flight(HttpServletRequest request) {
        String from = request.getParameter("from");
        String to = request.getParameter("to");
        return flightService.getFlightList(from, to);
    }

}
