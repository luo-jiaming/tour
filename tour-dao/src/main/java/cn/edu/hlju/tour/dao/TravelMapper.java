package cn.edu.hlju.tour.dao;

import cn.edu.hlju.tour.entity.Travel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TravelMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Travel record);

    int insertSelective(Travel record);

    Travel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Travel record);

    int updateByPrimaryKeyWithBLOBs(Travel record);

    int updateByPrimaryKey(Travel record);

    //返回表的第num+1条记录
    Travel selectLimitOne(Long num);

    //返回spotid为id的第num+1条记录
    Travel selectBySpotIdLimitOne(@Param("num") Long num, @Param("id") Long id);

    //返回表的总记录数
    long total();

    //返回spotid为id的总记录数
    long totalBySpotId(Long id);

    List<Travel> query(Travel travel);

}