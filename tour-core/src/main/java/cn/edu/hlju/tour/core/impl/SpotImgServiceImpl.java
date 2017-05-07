package cn.edu.hlju.tour.core.impl;

import cn.edu.hlju.tour.common.utils.UploadUtils;
import cn.edu.hlju.tour.core.SpotImgService;
import cn.edu.hlju.tour.dao.SpotImgMapper;
import cn.edu.hlju.tour.entity.SpotImg;
import cn.edu.hlju.tour.entity.User;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by lft on 2017/5/7.
 */
@Service
public class SpotImgServiceImpl implements SpotImgService {

    @Autowired
    private SpotImgMapper spotImgMapper;

    @Override
    public JSONObject selectSpotImgByPage(int pageNum, int size, String spotName) {
        PageHelper.startPage(pageNum, size);                                //分页
        List<Map> list = spotImgMapper.selectBySpotName(spotName);          //得到分页之后的map
        PageInfo<User> pageInfo = new PageInfo(list);                       //分页参数
        JSONObject json = new JSONObject();
        json.put("pageinfo", pageInfo);
        json.put("list", list);
        return json;
    }

    @Override
    public void update(SpotImg spotImg) {
        spotImgMapper.updateByPrimaryKeySelective(spotImg);
    }

    @Override
    public String uploadImg(MultipartFile file, HttpServletRequest request) throws IOException {
        String contextPath = UploadUtils.uploadFile(file, request, "spot/");
        return contextPath;
    }

    @Override
    public void delSpotImg(Long[] ids) {
        spotImgMapper.delBySpotImgId(ids);
    }

    @Override
    public void addSpotImg(SpotImg spotImg) {
        spotImgMapper.insertSelective(spotImg);
    }
}
