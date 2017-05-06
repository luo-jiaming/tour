package cn.edu.hlju.tour.dao;

import cn.edu.hlju.tour.entity.SpotComment;

import java.util.List;

public interface SpotCommentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SpotComment record);

    int insertSelective(SpotComment record);

    SpotComment selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SpotComment record);

    int updateByPrimaryKey(SpotComment record);

    List selectAll();

    List selectCommentBySpotId(Long id);

    void delByUserId(Long[] ids);

}