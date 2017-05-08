package cn.edu.hlju.tour.core.impl;

import cn.edu.hlju.tour.core.RoomTypeService;
import cn.edu.hlju.tour.dao.RoomTypeMapper;
import cn.edu.hlju.tour.entity.RoomType;
import cn.edu.hlju.tour.entity.User;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by Sole on 2017/4/10.
 */
@Service
public class RoomTypeServiceImpl implements RoomTypeService {

    @Autowired
    private RoomTypeMapper roomTypeMapper;

    @Override
    public List<RoomType> getByHotelId(Long id) {
        return roomTypeMapper.selectByHotelId(id);
    }

    @Override
    public JSONObject selectRoomTypeByPage(int pageNum, int size, Map<String, String> map) {
        PageHelper.startPage(pageNum, size);                          //分页
        List<Map> list = roomTypeMapper.selectByMap(map);             //得到分页之后的map
        PageInfo<User> pageInfo = new PageInfo(list);                 //分页参数
        JSONObject json = new JSONObject();
        json.put("pageinfo", pageInfo);
        json.put("list", list);
        return json;
    }

    @Override
    public void update(RoomType roomType) {

    }

    @Override
    public String uploadImg(MultipartFile file, HttpServletRequest request) {
        return null;
    }

    @Override
    public void delRoomType(Long[] ids) {

    }

    @Override
    public void addRoomType(RoomType roomType) {

    }

}
