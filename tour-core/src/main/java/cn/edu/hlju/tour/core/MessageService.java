package cn.edu.hlju.tour.core;


import cn.edu.hlju.tour.entity.Message;

import java.util.List;

public interface MessageService {

    List queryByUserId(Long id);

    void update(Message message);

}
