package cn.edu.hlju.tour.core.impl;

import cn.edu.hlju.tour.core.FlightService;
import cn.edu.hlju.tour.dao.FlightMapper;
import cn.edu.hlju.tour.entity.Flight;
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

}
