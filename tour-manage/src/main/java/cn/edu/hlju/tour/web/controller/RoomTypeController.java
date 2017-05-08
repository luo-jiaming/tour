package cn.edu.hlju.tour.web.controller;

import cn.edu.hlju.tour.core.RoomTypeService;
import cn.edu.hlju.tour.entity.RoomType;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lft on 2017/5/7.
 */
@Controller
public class RoomTypeController {

    @Autowired
    private RoomTypeService roomTypeService;

    @RequestMapping(value= "getRoomTypeList")
    @ResponseBody
    public String getRoomTypeList(int page, int rows, String typeName, String hotelName) {

        //{"total":10, "row":[{},{}]}
        Map<String, String> map = new HashMap();
        map.put("typeName", typeName);
        map.put("hotelName", hotelName);
        JSONObject json = roomTypeService.selectRoomTypeByPage(page, rows, map);
        PageInfo pageInfo = (PageInfo)json.get("pageinfo");
        List<Map> list = (List)json.get("list");
        long total = pageInfo.getTotal();
        String str = JSON.toJSONString(list);
        String jsonStr = "{\"total\":" + total + ", \"rows\":" + str + "}";
        return jsonStr;

    }

    @RequestMapping(value= "editRoomType")
    @ResponseBody
    public void editRoomType(RoomType roomType, @RequestParam(value="file", required=false) MultipartFile file, HttpServletRequest request) throws IOException {
        if (file != null) {
            String str = roomTypeService.uploadImg(file , request);
            roomType.setIndexImg(str);
        }
        roomTypeService.update(roomType);
    }

    @RequestMapping(value= "delRoomType")
    @ResponseBody
    public void delRoomType(@RequestParam("ids") String idsTemp) {
        String[] tempArray = idsTemp.split(",");
        Long[] ids = new Long[tempArray.length];
        for (int i = 0; i<tempArray.length; i++) {
            ids[i] = Long.parseLong(tempArray[i]);
        }
        roomTypeService.delRoomType(ids);
    }

    @RequestMapping(value= "addRoomType")
    @ResponseBody
    public void addRoomType(RoomType roomType, @RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {
        String str = roomTypeService.uploadImg(file , request);
        roomType.setIndexImg(str);
        roomTypeService.addRoomType(roomType);
    }

}
