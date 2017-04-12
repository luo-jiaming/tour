package cn.edu.hlju.tour.web.controller;

import cn.edu.hlju.tour.core.HotelService;
import cn.edu.hlju.tour.entity.HotelComment;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Sole on 2017/4/10.
 */
@Controller
public class HotelController {

    @Autowired
    private HotelService hotelService;

    /**
     * 查询全部酒店
     * @param request
     * @return
     */
    @RequestMapping(value = "hotels")
    @ResponseBody
    public JSONObject hotels(HttpServletRequest request) {
        String spotName = request.getParameter("spotname");
        return hotelService.query(spotName);
    }

    /**
     * 查询酒店进去酒店页面
     *
     * @param id 酒店ID
     * @param request
     * @return
     */
    @RequestMapping(value = "hotel")
    public String hotel(Long id, HttpServletRequest request) {
        JSONObject json = hotelService.hotelById(id);
        request.setAttribute("json", json);
        return "forward:hotel.jsp";
    }

    //评论分页
    @RequestMapping(value = "hotelComment")
    @ResponseBody
    public JSONObject spotComment(HttpServletRequest request) {
        String spotId = request.getParameter("hotelId");
        String pageNum = request.getParameter("pageNum");
        String size = request.getParameter("size");
        Long id = Long.parseLong(spotId);
        return hotelService.getHotelComment(id, Integer.parseInt(pageNum), Integer.parseInt(size));
    }

    @RequestMapping(value = "addHotelComment")
    @ResponseBody
    public void saveComment(HotelComment comment, HttpServletRequest request) {
        hotelService.saveComment(comment, request);
    }

}
