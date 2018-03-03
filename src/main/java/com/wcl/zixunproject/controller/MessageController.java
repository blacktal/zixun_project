package com.wcl.zixunproject.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.HtmlUtils;

import com.wcl.zixunproject.pojo.Comment;
import com.wcl.zixunproject.pojo.Message;
import com.wcl.zixunproject.pojo.User;
import com.wcl.zixunproject.service.MessageService;
import com.wcl.zixunproject.service.UserService;
import com.wcl.zixunproject.util.HostHolder;
import com.wcl.zixunproject.util.ViewObject;
import com.wcl.zixunproject.util.ZixunProjectUtil;

@Controller
public class MessageController {
    Logger logger = LoggerFactory.getLogger(MessageController.class);
    
    @Autowired
    UserService userService;
    
    @Autowired
    MessageService messageService;
    
    @Autowired
    HostHolder hostHolder;
    
    @RequestMapping(path= {"/msg/addMessage"}, method= {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String addMessage(@RequestParam("toName") String toName,
                             @RequestParam("content") String content) {
        try {
            User currentUser = hostHolder.getUser();
            if (currentUser == null) {
                // 未登录用户，返回未登录代码999
                return ZixunProjectUtil.getJSONString(999);
            }
            // 校验toId用户是否存在
            User toUser = userService.getUserByName(toName);
            if ( toUser == null) {
                return ZixunProjectUtil.getJSONString(1, "用户不存在");
            }
            // 当前用户给别人发信息
            Message message = new Message();
            message.setContent(HtmlUtils.htmlEscape(content));
            message.setToId(toUser.getId());
            message.setFromId(currentUser.getId());
            message.setCreatedDate(new Date());
            message.setConversationId((currentUser.getId() > toUser.getId() ?
                    String.format("%d_%d", toUser.getId(), currentUser.getId()): String.format("%d_%d", currentUser.getId(), toUser.getId())));
            message.setHasRead(0);
            messageService.addMessage(message);
            return ZixunProjectUtil.getJSONString(0);
        } catch (Exception e) {
            logger.error("添加站内信失败：" + e.getMessage());
        }
        return ZixunProjectUtil.getJSONString(1, "添加站内信失败");
    }
    
    
    @RequestMapping(path= {"/msg/list"}, method= {RequestMethod.GET})
    public String listMessage(Model model) {
        try {
            User currentUser = hostHolder.getUser();
            if (currentUser == null) {
                // 未登录用户，返回登录页面
                return "login";
            }
            
            // 当前用户与他人的信息往来
            List<Message> msgList = messageService.getMessageListByUserId(currentUser.getId(), 0, 10);
            List<ViewObject> vos = new ArrayList<>();
            for (Message msg : msgList) {
                ViewObject vo = new ViewObject();
                vo.set("conversation", msg);
                // 获取对方的user信息
                int userId = (currentUser.getId() == msg.getFromId() ? msg.getToId() : msg.getFromId());
                vo.set("user", userService.getUserById(userId));
                vos.add(vo);
            }
            model.addAttribute("conversations", vos);
            
            
        } catch (Exception e) {
            logger.error("添加站内信失败：" + e.getMessage());
        }
        return ZixunProjectUtil.getJSONString(1, "添加站内信失败");
    }
    
    
    
}
