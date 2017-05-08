package cn.edu.hlju.tour.core;

import cn.edu.hlju.tour.entity.Hotel;
import cn.edu.hlju.tour.entity.HotelComment;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * Created by Sole on 2017/4/10.
 */
public interface HotelService {

    JSONObject query(String spotName);

    JSONObject hotelById(Long id);

    JSONObject getHotelComment(Long id, int pageNum, int size);

    void saveComment(HotelComment comment, HttpServletRequest request);

    JSONObject selectHotelByPage(int pageNum, int size, Hotel hotel);

    String uploadIndexImg(MultipartFile file, HttpServletRequest request) throws IOException;

    void update(Hotel hotel);

    void delHotel(Long[] ids);

    void addHotel(Hotel hotel);

    List<Hotel> getAllHotel();
}
