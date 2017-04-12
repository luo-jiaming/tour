package cn.edu.hlju.tour.core.impl;

import cn.edu.hlju.tour.core.RoomTypeService;
import cn.edu.hlju.tour.dao.RoomTypeMapper;
import cn.edu.hlju.tour.entity.RoomType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

}
