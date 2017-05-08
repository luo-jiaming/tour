package cn.edu.hlju.tour.core.impl;

import cn.edu.hlju.tour.core.HotelCommentService;
import cn.edu.hlju.tour.dao.HotelCommentMapper;
import cn.edu.hlju.tour.entity.User;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by lft on 2017/5/7.
 */
@Service
public class HotelCommentServiceImpl implements HotelCommentService {

    @Autowired
    private HotelCommentMapper hotelCommentMapper;

    @Override
    public JSONObject selectHotelCommentByPage(int pageNum, int size, Map map) {
        PageHelper.startPage(pageNum, size);                          //分页
        List<Map> list = hotelCommentMapper.selectByMap(map);          //得到分页之后的map
        PageInfo<User> pageInfo = new PageInfo(list);                 //分页参数
        JSONObject json = new JSONObject();
        json.put("pageinfo", pageInfo);
        json.put("list", list);
        return json;
    }

    @Override
    public void delHotelComment(Long[] ids) {
        hotelCommentMapper.delByHotelCommentId(ids);
    }
}
