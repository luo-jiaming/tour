package cn.edu.hlju.tour.dao;

import cn.edu.hlju.tour.entity.TravelComment;

import java.util.List;

public interface TravelCommentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TravelComment record);

    int insertSelective(TravelComment record);

    TravelComment selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TravelComment record);

    int updateByPrimaryKey(TravelComment record);

    List<TravelComment> selectCommentByTravelId(Long id);
}