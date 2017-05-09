package cn.edu.hlju.tour.dao;

import cn.edu.hlju.tour.entity.RoomType;

import java.util.List;
import java.util.Map;

public interface RoomTypeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(RoomType record);

    int insertSelective(RoomType record);

    RoomType selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RoomType record);

    int updateByPrimaryKey(RoomType record);

    List<RoomType> selectByHotelId(Long id);

    List<Map> selectByMap(Map<String, String> map);

    void delByRoomTypeId(Long[] ids);
}