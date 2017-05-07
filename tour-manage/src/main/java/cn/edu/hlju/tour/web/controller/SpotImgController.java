package cn.edu.hlju.tour.web.controller;

import cn.edu.hlju.tour.core.SpotImgService;
import cn.edu.hlju.tour.entity.SpotImg;
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

    @RequestMapping(value= "editSpotImg")
    @ResponseBody
    public void editSpotImg(SpotImg spotImg, @RequestParam(value="file", required=false) MultipartFile file, HttpServletRequest request) throws IOException {
        if (file != null) {
            String str = spotImgService.uploadImg(file , request);
            spotImg.setImg(str);
        }
        spotImgService.update(spotImg);
    }

    @RequestMapping(value= "delSpotImg")
    @ResponseBody
    public void delSpotImg(@RequestParam("ids") String idsTemp) {
        String[] tempArray = idsTemp.split(",");
        Long[] ids = new Long[tempArray.length];
        for (int i = 0; i<tempArray.length; i++) {
            ids[i] = Long.parseLong(tempArray[i]);
        }
        spotImgService.delSpotImg(ids);
    }

    @RequestMapping(value= "addSpotImg")
    @ResponseBody
    public void addSpotImg(SpotImg spotImg,@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {
        String str = spotImgService.uploadImg(file , request);
        spotImg.setImg(str);
        spotImgService.addSpotImg(spotImg);
    }

}
