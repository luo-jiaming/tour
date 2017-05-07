package cn.edu.hlju.tour.dao;

import cn.edu.hlju.tour.entity.Flight;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FlightMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Flight record);

    int insertSelective(Flight record);

    Flight selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Flight record);

    int updateByPrimaryKey(Flight record);

    List<Flight> selectFlightByFromTo(@Param("from") String from, @Param("to") String to);

    List<Flight> selectByFlight(Flight flight);

    void delByFlightId(Long[] ids);
}