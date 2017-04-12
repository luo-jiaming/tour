package cn.edu.hlju.tour.core.impl;

import cn.edu.hlju.tour.core.HotelService;
import cn.edu.hlju.tour.dao.*;
import cn.edu.hlju.tour.entity.*;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by Sole on 2017/4/10.
 */
@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelMapper hotelMapper;

    @Autowired
    private SpotMapper spotMapper;

    @Autowired
    private HotelCommentMapper hotelCommentMapper;

    @Autowired
    private RoomTypeMapper roomTypeMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public JSONObject query(String spotName) {
        JSONObject json = new JSONObject();
        Hotel hotel = new Hotel();
        if (spotName == null || "".equals(spotName)) {              //无条件查询
        } else {
            Spot spot = spotMapper.selectBySpotName(spotName);      //条件查询
            if (spot == null) {
                json.put("status", "spotNotExist");
                return json;
            }
            hotel.setSpotId(spot.getId());
        }
        List<Hotel> list = hotelMapper.query(hotel);
        if (list == null || list.size() == 0) {
            json.put("status", "hotelNotExist");
            return json;
        }
        List<Map> mapList = new ArrayList<>();
        for (Hotel temp : list) {
            Long spotid = temp.getSpotId();
            Spot spot = spotMapper.selectByPrimaryKey(spotid);         //酒店附近的景点
            Map<String, Object> map = new HashMap<>();
            map.put("hotel", temp);
            map.put("spot", spot);
            mapList.add(map);
        }
        json.put("status", "success");
        json.put("maplist", mapList);
        return json;
    }

    @Override
    public JSONObject hotelById(Long id) {
        JSONObject json = new JSONObject();
        Hotel hotel = hotelMapper.selectByPrimaryKey(id);
        List<RoomType> roomList = roomTypeMapper.selectByHotelId(hotel.getId());
        json.put("hotel", hotel);
        json.put("roomlist", roomList);
        return json;
    }

    /**
     *
     * @param id 景点ID
     * @param pageNum 第几页
     * @param size  每页的大小
     * @return
     */
    @Override
    public JSONObject getHotelComment(Long id, int pageNum, int size) {
        PageHelper.startPage(pageNum, size);                                            //分页
        List<HotelComment> list =  hotelCommentMapper.selectCommentByHotelId(id);       //得到分页之后的评论
        PageInfo<HotelComment> pageInfo = new PageInfo(list);                           //分页参数
        List<Map> mapList = new ArrayList<>();
        for (HotelComment comment : list) {                                             //评论用户
            Map map = new HashMap();
            Long userId = comment.getUserId();
            User user = userMapper.selectByPrimaryKey(userId);
            map.put("comment", comment);
            map.put("user", user);
            mapList.add(map);
        }
        JSONObject json = new JSONObject();
        json.put("pageinfor", pageInfo);
        json.put("maplist", mapList);
        return json;
    }

    @Override
    public void saveComment(HotelComment comment, HttpServletRequest request) {
        User user =  (User)request.getSession().getAttribute("user");
        comment.setUserId(user.getId());
        comment.setTime(new Date());
        hotelCommentMapper.insertSelective(comment);
    }


}
