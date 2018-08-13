package com.breast.oil.web;

import com.alibaba.fastjson.JSON;
import com.breast.oil.domain.PYQInfo;
import com.breast.oil.po.RespInfo;
import com.breast.oil.po.WechatFriend;
import com.breast.oil.push.Demo;
import com.breast.oil.repository.PYQInfoRepository;
import com.breast.oil.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

@RestController
public class WxToolsController {
    @Autowired
    PYQInfoRepository mPYQInfoRepository;
    @Autowired
    Demo mPush;
    @Autowired
    UserService mUserService;

    @RequestMapping(value = "/names", method = RequestMethod.GET)
    public String remember(HttpServletRequest request) {
        String name = request.getParameter("name");
        List<PYQInfo> list = mPYQInfoRepository.findByUsername(name);
        RespInfo respInfo = new RespInfo();
        if(list == null || list.size() == 0){
            respInfo.status = 500;
            respInfo.message = "fail";
        }else{
            WechatFriend wechatFriend = new WechatFriend();
            wechatFriend.names = stringToList(list.get(0).names);
            respInfo.status = 200;
            respInfo.message = "ok";
            respInfo.data = wechatFriend;
        }
        return JSON.toJSONString(respInfo);
    }

    @RequestMapping(value = "/names", method = RequestMethod.PUT)
    public String updateNames(HttpServletRequest request) {
        String name = request.getParameter("name");
        String names = request.getParameter("names");
        List<PYQInfo> list = mPYQInfoRepository.findByUsername(name);
        if(list == null || list.size() == 0){
            mPYQInfoRepository.save(new PYQInfo(name,names));
        }else{
            PYQInfo info = list.get(0);
            info.username = name;
            info.names = names;
            mPYQInfoRepository.save(info);
        }
        RespInfo respInfo = new RespInfo();
        respInfo.status = 200;
        respInfo.message = "ok";
        try {
            mPush.sendAndroidUnicast("养号","TRUE",name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JSON.toJSONString(respInfo);
    }

    @RequestMapping(value = "/pyqinfo", method = RequestMethod.PUT)
    public String updatepyq(HttpServletRequest request) {
        String content = request.getParameter("content");
        String index = request.getParameter("index");
        String count = request.getParameter("count");
        String name = request.getParameter("name");
        List<PYQInfo> list = mPYQInfoRepository.findByUsername(name);
        if(list == null || list.size() == 0){
            mPYQInfoRepository.save(new PYQInfo(content,index,count,name));
        }else{
            PYQInfo info = list.get(0);
            info.content = content;
            info.sindex = index;
            info.asum = count;
            mPYQInfoRepository.save(info);
        }
        RespInfo respInfo = new RespInfo();
        respInfo.status = 200;
        respInfo.message = "ok";
        try {
            mPush.sendAndroidUnicast("朋友圈","TRUE",name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JSON.toJSONString(respInfo);
    }

    @RequestMapping(value = "/pyqinfo", method = RequestMethod.GET)
    public String sendpyq(HttpServletRequest request) {
        String name = request.getParameter("name");
        List<PYQInfo> list = mPYQInfoRepository.findByUsername(name);
        RespInfo respInfo = new RespInfo();
        if(list == null || list.size() == 0){
            respInfo.status = 500;
            respInfo.message = "fail";
        }else{
            PYQInfo info = list.get(0);
            respInfo.status = 200;
            respInfo.message = "ok";
            respInfo.data = info;
        }
        return JSON.toJSONString(respInfo);
    }

    @RequestMapping(value = "/addfriends", method = RequestMethod.PUT)
    public String addfriends(HttpServletRequest request) {
        String name = request.getParameter("name");
        String time = request.getParameter("time");
        RespInfo respInfo = new RespInfo();
        respInfo.status = 200;
        respInfo.message = "ok";
        try {
            mPush.sendAndroidUnicast("加粉",time,name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JSON.toJSONString(respInfo);

    }

    @RequestMapping(value = "/stopall", method = RequestMethod.GET)
    public String stopAll(HttpServletRequest request) {
        String name = request.getParameter("name");
        RespInfo respInfo = new RespInfo();
        respInfo.status = 200;
        respInfo.message = "ok";
        try {
//            mPush.sendAndroidCustomizedcast("停止","FALSE",name,"WEIXIN");
            mPush.sendAndroidUnicast("停止","FALSE",name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JSON.toJSONString(respInfo);

    }


    public static List<String> stringToList(String mList) {
        return Arrays.asList(mList.split(","));
    }

}
