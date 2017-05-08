package cn.edu.hlju.tour.dao;

import cn.edu.hlju.tour.entity.Hotel;

import java.util.List;

public interface HotelMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Hotel record);

    int insertSelective(Hotel record);

    Hotel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Hotel record);

    int updateByPrimaryKey(Hotel record);

    List<Hotel> query(Hotel hotel);

    List<Hotel> selectByHotel(Hotel hotel);

    void delByHotelId(Long[] ids);

    List<Hotel> selectAll();
}