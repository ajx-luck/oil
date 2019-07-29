package com.breast.oil.web;

import com.alibaba.fastjson.JSON;
import com.breast.oil.domain.DouyinAccount;
import com.breast.oil.domain.WubaTribe;
import com.breast.oil.domain.WubaUser;
import com.breast.oil.po.RespInfo;
import com.breast.oil.repository.DouyinAccountRepository;
import com.breast.oil.repository.WubaTribeRepository;
import com.breast.oil.repository.WubaUserRepository;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Random;

@RestController
public class DouController {
    @Autowired
    DouyinAccountRepository douyinAccountRepository;
    @Autowired
    WubaTribeRepository wubaTribeRepository;
    @Autowired
    WubaUserRepository wubaUserRepository;
    @RequestMapping(value = "/addaccount", method = RequestMethod.GET)
    public String addAccount(HttpServletRequest request) {
        DouyinAccount douyinAccount = new DouyinAccount();
        douyinAccount.setPhone(request.getParameter("phone"));
        douyinAccount.setAndroid(request.getParameter("android"));
        douyinAccount.setIos(request.getParameter("ios"));
        douyinAccount.setAndroidusetimes(0L);
        douyinAccount.setIosusetimes(0L);
        long currentTime = System.currentTimeMillis();
        douyinAccount.setCreatetimes(currentTime);
        douyinAccount.setUpdatetimes(currentTime);
        douyinAccountRepository.save(douyinAccount);
        return "{code:200}";
    }

    @RequestMapping(value = "/tribeid", method = RequestMethod.GET)
    public String gettribeid(HttpServletRequest request) {
        int count = (int) wubaTribeRepository.count();
        long index = new Random().nextInt(count);
        WubaTribe wubaTribe = wubaTribeRepository.findOne(index);
        RespInfo respInfo = new RespInfo();
        respInfo.status = 200;
        respInfo.message = "ok";
        respInfo.data = wubaTribe.getTribe();
        //respInfo.data = "{\"pagetype\":\"tribeDetail\",\"title\":\"帖子\",\"supportWeb\":true,\"aid\":\"1016778510327537664\",\"cbd\":\"400000002\",\"referrer\":\"1\",\"algorithm\":\"1\"}";
        wubaTribe.setUpdatetimes(System.currentTimeMillis());
        wubaTribeRepository.save(wubaTribe);
        return JSON.toJSONString(respInfo);
    }

    @RequestMapping(value = "/tribeid", method = RequestMethod.POST)
    public String setribeid(HttpServletRequest request) {
        String tribe = request.getParameter("tribe");
        long currenttime = System.currentTimeMillis();
        WubaTribe wubaTribe;
        try {
            JSONObject jsonObject = new JSONObject(tribe);
            String aid = jsonObject.getString("aid");
            List<WubaTribe> list = wubaTribeRepository.findByAid(aid);

            if(list!=null&&list.size()>0){
                wubaTribe = list.get(0);
                wubaTribe.setUpdatetimes(currenttime);
                wubaTribe.setTribe(tribe);
                wubaTribeRepository.save(wubaTribe);
            }else{
                wubaTribe = new WubaTribe();
                wubaTribe.setCreatetimes(currenttime);
                wubaTribe.setUpdatetimes(currenttime);
                wubaTribe.setAid(aid);
                wubaTribe.setTribe(tribe);
                wubaTribeRepository.save(wubaTribe);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RespInfo respInfo = new RespInfo();
        respInfo.status = 200;
        respInfo.message = "ok";
        respInfo.data = tribe;
        return JSON.toJSONString(respInfo);
    }


    @RequestMapping(value = "/wubauser", method = RequestMethod.GET)
    public String getwubauser(HttpServletRequest request) {
        String reg = request.getParameter("reg");
        Long currenttime = System.currentTimeMillis();
        Long yesterday = currenttime - 24*60*60*1000;
//        Long yesterday = currenttime;
        List<WubaUser> list = wubaUserRepository.findByUpdatetimesLessThan(yesterday);
        WubaUser wubaUser;
        RespInfo respInfo = new RespInfo();
        respInfo.status = 404;
        respInfo.message = "fail";
        respInfo.data = "没有可用的账号";
        if(list != null && list.size() > 0){
            int count = list.size();
            int index = new Random().nextInt(count);
            wubaUser = list.get(index);
            wubaUser.setUpdatetimes(currenttime);
            wubaUser.setLogin(wubaUser.getLogin()+1);
            wubaUserRepository.save(wubaUser);
            respInfo.status = 200;
            respInfo.message = "ok";
            respInfo.data = wubaUser.getWubacook1() + reg + wubaUser.getWubacook2();
        }


        return JSON.toJSONString(respInfo);
    }

    @RequestMapping(value = "/wubauser", method = RequestMethod.POST)
    public String setwubauser(HttpServletRequest request) {
        String wubacook1 = request.getParameter("wubacook1");
        String wubacook2 = request.getParameter("wubacook2");
        long currenttime = System.currentTimeMillis();
        WubaUser wubaUser;
        RespInfo respInfo = new RespInfo();
        respInfo.status = 404;
        respInfo.message = "fail";
        respInfo.data = "上传账号失败";
        try {
            JSONObject jsonObject = new JSONObject(wubacook2);
            JSONObject data = (JSONObject) jsonObject.get("data");


            String uid = data.getString("uid");
            List<WubaUser> list = wubaUserRepository.findByUid(uid);

            if(list!=null&&list.size()>0){
                wubaUser = list.get(0);
                wubaUser.setUpdatetimes(currenttime);
                wubaUser.setWubacook1(wubacook1);
                wubaUser.setWubacook2(wubacook2);
                wubaUserRepository.save(wubaUser);
                respInfo.status = 200;
                respInfo.message = "ok";
                respInfo.data = "账号已存在，更新信息完成";
            }else{
                wubaUser = new WubaUser();
                wubaUser.setCreatetimes(currenttime);
                wubaUser.setUpdatetimes(currenttime- 24*60*60*1000);
                wubaUser.setUid(uid);
                wubaUser.setWubacook1(wubacook1);
                wubaUser.setWubacook2(wubacook2);
                wubaUser.setLogin(0);
                wubaUserRepository.save(wubaUser);
                respInfo.status = 200;
                respInfo.message = "ok";
                respInfo.data = "上传账号成功";
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return JSON.toJSONString(respInfo);
    }

    @RequestMapping(value = "/wubauser", method = RequestMethod.DELETE)
    public String deletewubauser(HttpServletRequest request) {
        String wubacook1 = request.getParameter("wubacook1");
        String wubacook2 = request.getParameter("wubacook2");
        long currenttime = System.currentTimeMillis();
        WubaUser wubaUser;
        RespInfo respInfo = new RespInfo();
        respInfo.status = 200;
        respInfo.message = "ok";
        respInfo.data = "账号删除成功";
        try {
            JSONObject jsonObject = new JSONObject(wubacook2);
            JSONObject data = (JSONObject) jsonObject.get("data");

            String uid = data.getString("uid");
            List<WubaUser> list = wubaUserRepository.findByUid(uid);

            if(list!=null&&list.size()>0){
                wubaUser = list.get(0);
                wubaUserRepository.delete(wubaUser);;
            }else{
                respInfo.status = 404;
                respInfo.message = "fail";
                respInfo.data = "账号不存在";
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return JSON.toJSONString(respInfo);
    }


}
