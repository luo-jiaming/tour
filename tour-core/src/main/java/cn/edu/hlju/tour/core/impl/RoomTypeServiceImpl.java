package cn.edu.hlju.tour.core.impl;

import cn.edu.hlju.tour.common.utils.UploadUtils;
import cn.edu.hlju.tour.core.RoomTypeService;
import cn.edu.hlju.tour.dao.HotelMapper;
import cn.edu.hlju.tour.dao.RoomTypeMapper;
import cn.edu.hlju.tour.entity.Hotel;
import cn.edu.hlju.tour.entity.RoomType;
import cn.edu.hlju.tour.entity.User;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by Sole on 2017/4/10.
 */
@Service
public class RoomTypeServiceImpl implements RoomTypeService {

    @Autowired
    private RoomTypeMapper roomTypeMapper;

    @Autowired
    private HotelMapper hotelMapper;

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
        roomTypeMapper.updateByPrimaryKeySelective(roomType);
        Long hotelId = roomType.getHotelId();
        //更改酒店最低价格
        int low = Integer.MAX_VALUE;
        List<RoomType> list = roomTypeMapper.selectByHotelId(hotelId);
        for (RoomType type : list) {
            int price = Integer.parseInt(type.getPrice());
            if (price < low) {
                low = price;
            }
        }
        Hotel hotel = new Hotel();
        hotel.setId(hotelId);
        hotel.setPrice(Integer.toString(low));
        hotelMapper.updateByPrimaryKeySelective(hotel);
    }

    @Override
    public String uploadImg(MultipartFile file, HttpServletRequest request) throws IOException {
        String contextPath = UploadUtils.uploadFile(file, request, "hotel/");
        return contextPath;
    }

    @Override
    public void delRoomType(Long[] ids) {
        roomTypeMapper.delByRoomTypeId(ids);
    }

    @Override
    public void addRoomType(RoomType roomType) {
        roomTypeMapper.insertSelective(roomType);
    }

}
