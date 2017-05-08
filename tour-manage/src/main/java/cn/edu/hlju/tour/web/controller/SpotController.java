package cn.edu.hlju.tour.web.controller;

import cn.edu.hlju.tour.core.SpotService;
import cn.edu.hlju.tour.entity.Spot;
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
public class SpotController {

    @Autowired
    private SpotService spotService;

    @RequestMapping(value= "getSpotList")
    @ResponseBody
    public String getSpotList(int page, int rows, Spot spot) {

        //{"total":10, "row":[{},{}]}
        JSONObject json = spotService.selectSpotByPage(page, rows, spot);
        PageInfo pageInfo = (PageInfo)json.get("pageinfo");
        List<Spot> list = (List)json.get("list");
        long total = pageInfo.getTotal();
        String str = JSON.toJSONString(list);
        String jsonStr = "{\"total\":" + total + ", \"rows\":" + str + "}";
        return jsonStr;

    }

    @RequestMapping(value= "editSpot")
    @ResponseBody
    public void editSpot(Spot spot, @RequestParam(value="file", required=false) MultipartFile file, HttpServletRequest request) throws IOException {
        if (file != null) {
            String str = spotService.uploadIndexImg(file , request);
            spot.setIndexImg(str);
        }
        spotService.update(spot);
    }

    @RequestMapping(value= "delSpot")
    @ResponseBody
    public void delSpot(@RequestParam("ids") String idsTemp) {
        String[] tempArray = idsTemp.split(",");
        Long[] ids = new Long[tempArray.length];
        for (int i = 0; i<tempArray.length; i++) {
            ids[i] = Long.parseLong(tempArray[i]);
        }
        spotService.delSpot(ids);
    }

    @RequestMapping(value= "addSpot")
    @ResponseBody
    public void addSpot(Spot spot,@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {
        String str = spotService.uploadIndexImg(file , request);
        spot.setIndexImg(str);
        spotService.addSpot(spot);
    }

    @RequestMapping(value= "getAllSpot")
    @ResponseBody
    public List getAllSpot() throws IOException {
        List<Spot> list = spotService.getAllSpot();
        return list;
    }

//    @RequestMapping(value= "test")
//    @ResponseBody
//    public String test(HttpServletRequest request){
//        return AddrUtils.getUrl(request);
//    }

}
