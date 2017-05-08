package cn.edu.hlju.tour.dao;

import cn.edu.hlju.tour.entity.HotelComment;

import java.util.List;
import java.util.Map;

public interface HotelCommentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(HotelComment record);

    int insertSelective(HotelComment record);

    HotelComment selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(HotelComment record);

    int updateByPrimaryKey(HotelComment record);

    List<HotelComment> selectCommentByHotelId(Long id);

    void delByUserId(Long[] ids);

    List<Map> selectByMap(Map map);

    void delByHotelCommentId(Long[] ids);

    void delByHotelId(Long[] ids);
}