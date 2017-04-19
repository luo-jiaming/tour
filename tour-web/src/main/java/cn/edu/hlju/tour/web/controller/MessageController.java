package cn.edu.hlju.tour.web.controller;

import cn.edu.hlju.tour.core.MessageService;
import cn.edu.hlju.tour.entity.Message;
import cn.edu.hlju.tour.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Controller
public class MessageController {

    @Autowired
    private MessageService messageService;

    @RequestMapping(value = "messages")
    @ResponseBody
    public List<Map> messages(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        return messageService.queryByUserId(user.getId());
    }

    /**
     * 更新消息状态
     * @return
     */
    @RequestMapping(value = "updateMsg")
    @ResponseBody
    public void updateMsg(Message message) {
        messageService.update(message);
    }


//    /**
//     *
//     * @param request
//     * @return
//     */
//    @RequestMapping(value = "test")
//    public String test(HttpServletRequest request) {
//        request.setAttribute("name", "wen");
//        return "forward:test.jsp";
//    }


    /**
     * 回复消息
     */
    @RequestMapping(value = "sendMessage")
    @ResponseBody
    public void sendMessage(Message message, HttpServletRequest request) {
        User user = (User)request.getSession().getAttribute("user");
        message.setFromUid(user.getId());
        message.setType(0L);
        message.setTime(new Date());
        messageService.addMessage(message);
    }

}
