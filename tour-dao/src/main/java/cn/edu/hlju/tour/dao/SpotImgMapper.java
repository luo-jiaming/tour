package cn.edu.hlju.tour.dao;

import cn.edu.hlju.tour.entity.SpotImg;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SpotImgMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SpotImg record);

    int insertSelective(SpotImg record);

    SpotImg selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SpotImg record);

    int updateByPrimaryKey(SpotImg record);

    List<SpotImg> selectBySpotId(Long id);

    List<Map> selectBySpotName(@Param("spotName") String spotName);

    void delBySpotId(Long[] ids);

    void delBySpotImgId(Long[] ids);
}