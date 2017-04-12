package cn.edu.hlju.tour.core.impl;

import cn.edu.hlju.tour.core.SpotService;
import cn.edu.hlju.tour.dao.SpotCommentMapper;
import cn.edu.hlju.tour.dao.SpotImgMapper;
import cn.edu.hlju.tour.dao.SpotMapper;
import cn.edu.hlju.tour.dao.UserMapper;
import cn.edu.hlju.tour.entity.Spot;
import cn.edu.hlju.tour.entity.SpotComment;
import cn.edu.hlju.tour.entity.User;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Sole on 2017/3/31.
 */
@Service
public class SpotServiceImpl implements SpotService {

    @Autowired
    private SpotMapper spotMapper;

    @Autowired
    private SpotCommentMapper spotCommentMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private SpotImgMapper spotImgMapper;

    private JSONObject jsonTemp;    //暂存ajax查询是否有该景点时缓存得到

    @Override
    public String haveSpot(String spotname) {
        JSONObject json = new JSONObject();
        Spot spot = spotMapper.selectBySpotName(spotname);
        if (spot == null) {
            return "spotNotExist";
        }
        Long id = spot.getId();
        json.put("spot", spot);
        json.put("spotImg", spotImgMapper.selectBySpotId(id));
        jsonTemp = json;
        return "success";
    }

    @Override
    public JSONObject spotByName(String spotname) {
        if (jsonTemp == null) {
            this.haveSpot(spotname);
        }
        return jsonTemp;
    }

    @Override
    public JSONObject spotById(Long id) {
        JSONObject json = new JSONObject();
        Spot spot = spotMapper.selectByPrimaryKey(id);
        json.put("spot", spot);
        json.put("spotImg", spotImgMapper.selectBySpotId(id));
        return json;
    }

    /**
     *
     * @param id 景点ID
     * @param pageNum 第几页
     * @param size  每页的大小
     * @return
     */
    @Override
    public JSONObject getSpotComment(Long id, int pageNum, int size) {
        PageHelper.startPage(pageNum, size);                                        //分页
        List<SpotComment> list =  spotCommentMapper.selectCommentBySpotId(id);      //得到分页之后的评论
        PageInfo<SpotComment> pageInfo = new PageInfo(list);                        //分页参数
        List<Map> mapList = new ArrayList<>();
        for (SpotComment comment : list) {                                          //评论用户
            Map map = new HashMap();
            Long userId = comment.getUserId();
            User user = userMapper.selectByPrimaryKey(userId);
            map.put("comment", comment);
            map.put("user", user);
            mapList.add(map);
        }
        JSONObject json = new JSONObject();
        json.put("pageinfor", pageInfo);
        json.put("maplist", mapList);
        return json;
    }

    @Override
    public List<Spot> getAllSpot() {
        return spotMapper.selectAll();
    }

}
