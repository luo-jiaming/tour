package cn.edu.hlju.tour.core;

import cn.edu.hlju.tour.entity.RoomType;

import java.util.List;

/**
 * Created by Sole on 2017/4/10.
 */
public interface RoomTypeService {

    List<RoomType> getByHotelId(Long id);

}
