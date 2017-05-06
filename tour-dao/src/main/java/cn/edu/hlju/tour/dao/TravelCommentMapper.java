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

    //删除某个用户所有游记下面的评论
    void delByTravelId(Long[] ids);

    //删除某个用户所有的评论（所有游记）
    void delByUserId(Long id);

}