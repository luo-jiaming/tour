package cn.edu.hlju.tour.web.controller;

import cn.edu.hlju.tour.core.HotelService;
import cn.edu.hlju.tour.entity.Hotel;
import cn.edu.hlju.tour.entity.User;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * Created by lft on 2017/5/7.
 */
@Controller
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @RequestMapping(value= "getHotelList")
    @ResponseBody
    public String getHotelList(int page, int rows, Hotel hotel) {

        //{"total":10, "row":[{},{}]}
        JSONObject json = hotelService.selectHotelByPage(page, rows, hotel);
        PageInfo pageInfo = (PageInfo)json.get("pageinfo");
        List<User> list = (List)json.get("list");
        long total = pageInfo.getTotal();
        String str = JSON.toJSONString(list);
        String jsonStr = "{\"total\":" + total + ", \"rows\":" + str + "}";
        return jsonStr;

    }

    @RequestMapping(value= "editHotel")
    @ResponseBody
    public void editHotel(Hotel hotel, @RequestParam(value="file", required=false) MultipartFile file, HttpServletRequest request) throws IOException {
        if (file != null) {
            String str = hotelService.uploadIndexImg(file , request);
            hotel.setIndexImg(str);
        }
        hotelService.update(hotel);
    }

    @RequestMapping(value= "delHotel")
    @ResponseBody
    public void delHotel(@RequestParam("ids") String idsTemp) {
        String[] tempArray = idsTemp.split(",");
        Long[] ids = new Long[tempArray.length];
        for (int i = 0; i<tempArray.length; i++) {
            ids[i] = Long.parseLong(tempArray[i]);
        }
        hotelService.delHotel(ids);
    }

    @RequestMapping(value= "addHotel")
    @ResponseBody
    public void addHotel(Hotel hotel,@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {
        String str = hotelService.uploadIndexImg(file , request);
        hotel.setIndexImg(str);
        hotelService.addHotel(hotel);
    }

    @RequestMapping(value= "getAllHotel")
    @ResponseBody
    public List getAllHotel() throws IOException {
        List<Hotel> list = hotelService.getAllHotel();
        return list;
    }


}
