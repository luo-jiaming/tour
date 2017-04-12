package cn.edu.hlju.tour.core;

import cn.edu.hlju.tour.entity.Flight;

import java.util.List;

/**
 * Created by Sole on 2017/4/1.
 */
public interface FlightService {

    List<Flight> getFlightList(String from, String to);

}
