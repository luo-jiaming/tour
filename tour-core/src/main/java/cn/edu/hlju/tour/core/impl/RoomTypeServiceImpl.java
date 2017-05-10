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
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
        //更新酒店最低价格
        Long hotelId = roomType.getHotelId();
        setLowerPrice(hotelId);
    }

    @Override
    public String uploadImg(MultipartFile file, HttpServletRequest request) throws IOException {
        String contextPath = UploadUtils.uploadFile(file, request, "hotel/");
        return contextPath;
    }

    @Override
    public void delRoomType(Long[] ids) {
        Set<Long> hotelIds = new HashSet();
        for (Long id : ids) {
            RoomType roomType = roomTypeMapper.selectByPrimaryKey(id);
            hotelIds.add(roomType.getHotelId());
        }
        //删除酒店房间类型
        roomTypeMapper.delByRoomTypeId(ids);
        //更新hotelIds的酒店最低价格
        for (Long id : hotelIds) {
            setLowerPrice(id);
        }
    }

    @Override
    public void addRoomType(RoomType roomType) {
        roomTypeMapper.insertSelective(roomType);
        //更新酒店最低价格
        Long hotelId = roomType.getHotelId();
        setLowerPrice(hotelId);
    }

    private void setLowerPrice(Long  hotelId) {
        //更改酒店最低价格
        int low = Integer.MAX_VALUE;
        List<RoomType> list = roomTypeMapper.selectByHotelId(hotelId);
        for (RoomType type : list) {
            int price = Integer.parseInt(type.getPrice());
            if (price < low) {
                low = price;
            }
        }
        if (low == Integer.MAX_VALUE) {
            low = 0;
        }
        Hotel hotel = new Hotel();
        hotel.setId(hotelId);
        hotel.setPrice(Integer.toString(low));
        hotelMapper.updateByPrimaryKeySelective(hotel);
    }

}
