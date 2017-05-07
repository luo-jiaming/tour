package cn.edu.hlju.tour.dao;

import cn.edu.hlju.tour.entity.SpotComment;

import java.util.List;
import java.util.Map;

public interface SpotCommentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SpotComment record);

    int insertSelective(SpotComment record);

    SpotComment selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SpotComment record);

    int updateByPrimaryKey(SpotComment record);

    List selectCommentBySpotId(Long id);

    void delByUserId(Long[] ids);

    void delBySpotId(Long[] ids);

    void delBySpotCommentId(Long[] ids);

    List<Map> selectByMap(Map map);
}