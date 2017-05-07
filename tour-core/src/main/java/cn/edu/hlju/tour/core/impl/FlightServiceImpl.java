package cn.edu.hlju.tour.core.impl;

import cn.edu.hlju.tour.core.FlightService;
import cn.edu.hlju.tour.dao.FlightMapper;
import cn.edu.hlju.tour.entity.Flight;
import cn.edu.hlju.tour.entity.TravelComment;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Sole on 2017/4/1.
 */
@Service
public class FlightServiceImpl implements FlightService {

    @Autowired
    private FlightMapper flightMapper;

    @Override
    public List<Flight> getFlightList(String from, String to) {
        return flightMapper.selectFlightByFromTo(from, to);
    }

    @Override
    public JSONObject selectFlightByPage(int pageNum, int size, Flight flight) {
        PageHelper.startPage(pageNum, size);                              //分页
        List<Flight> list = flightMapper.selectByFlight(flight);          //得到分页之后的用户
        PageInfo<TravelComment> pageInfo = new PageInfo(list);            //分页参数
        JSONObject json = new JSONObject();
        json.put("pageinfo", pageInfo);
        json.put("list", list);
        return json;
    }

    @Override
    public void update(Flight flight) {
        flightMapper.updateByPrimaryKeySelective(flight);
    }

    @Override
    public void delFlight(Long[] ids) {
        flightMapper.delByFlightId(ids);
    }

    @Override
    public void addFlight(Flight flight) {
        flightMapper.insertSelective(flight);
    }

}
