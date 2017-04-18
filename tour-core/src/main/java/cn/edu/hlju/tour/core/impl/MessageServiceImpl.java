package cn.edu.hlju.tour.core.impl;

import cn.edu.hlju.tour.core.MessageService;
import cn.edu.hlju.tour.dao.MessageMapper;
import cn.edu.hlju.tour.dao.UserMapper;
import cn.edu.hlju.tour.entity.Message;
import cn.edu.hlju.tour.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageMapper messageMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public List queryByUserId(Long id) {
        List listMap = new ArrayList();
        List<Message> list = messageMapper.selectByUserId(id);
        for (int i = 0; i<list.size(); i++) {
            Map map = new HashMap();
            Long userId = list.get(i).getFromUid();
            User user = userMapper.selectByPrimaryKey(userId);
            map.put("user", user);
            map.put("message", list.get(i));
            listMap.add(map);
        }
        return listMap;
    }

    @Override
    public void update(Message message) {
        message.setStatus("1");
        messageMapper.updateByPrimaryKeySelective(message);
    }

}
