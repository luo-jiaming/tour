package cn.edu.hlju.tour.core;

import cn.edu.hlju.tour.entity.RoomType;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by Sole on 2017/4/10.
 */
public interface RoomTypeService {

    List<RoomType> getByHotelId(Long id);

    JSONObject selectRoomTypeByPage(int pageNum, int size, Map<String, String> map);

    void update(RoomType roomType);

    String uploadImg(MultipartFile file, HttpServletRequest request);

    void delRoomType(Long[] ids);

    void addRoomType(RoomType roomType);
}
