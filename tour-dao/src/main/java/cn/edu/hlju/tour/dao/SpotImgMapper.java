package cn.edu.hlju.tour.dao;

import cn.edu.hlju.tour.entity.SpotImg;

import java.util.List;

public interface SpotImgMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SpotImg record);

    int insertSelective(SpotImg record);

    SpotImg selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SpotImg record);

    int updateByPrimaryKey(SpotImg record);

    List<SpotImg> selectBySpotId(Long id);
}