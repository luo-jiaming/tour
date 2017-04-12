package cn.edu.hlju.tour.dao;

import cn.edu.hlju.tour.entity.RoomType;

import java.util.List;

public interface RoomTypeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(RoomType record);

    int insertSelective(RoomType record);

    RoomType selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RoomType record);

    int updateByPrimaryKey(RoomType record);

    List<RoomType> selectByHotelId(Long id);
}