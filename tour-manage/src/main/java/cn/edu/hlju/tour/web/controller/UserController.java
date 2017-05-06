package cn.edu.hlju.tour.web.controller;

import cn.edu.hlju.tour.core.UserService;
import cn.edu.hlju.tour.entity.User;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Sole on 2017/3/2.
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value= "getUserList")
    @ResponseBody
    public String getList(int page, int rows, User user, HttpServletRequest request) {

        //{"total":10, "row":[{},{}]}
        JSONObject json = userService.selectAllUserByPage(page, rows);
        PageInfo pageInfo = (PageInfo)json.get("pageinfo");
        List<User> list = (List)json.get("list");
        long total = pageInfo.getTotal();
        String str = JSON.toJSONString(list);
        String jsonStr = "{\"total\":" + total + ", \"rows\":" + str + "}";
        return jsonStr;

    }

    @RequestMapping(value= "delUser")
    @ResponseBody
    public void delUser(@RequestParam("ids") String idsTemp) {
        String[] tempArray = idsTemp.split(",");
        Long[] ids = new Long[tempArray.length];
        for (int i = 0; i<tempArray.length; i++) {
            ids[i] = Long.parseLong(tempArray[i]);
        }
        userService.delUser(ids);
    }

}
