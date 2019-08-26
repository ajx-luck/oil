package com.breast.oil.web;

import com.alibaba.fastjson.JSON;
import com.breast.oil.domain.DouyinAccount;
import com.breast.oil.domain.WubaTribe;
import com.breast.oil.domain.WubaUid;
import com.breast.oil.domain.WubaUser;
import com.breast.oil.po.RespInfo;
import com.breast.oil.repository.DouyinAccountRepository;
import com.breast.oil.repository.WubaTribeRepository;
import com.breast.oil.repository.WubaUidRepository;
import com.breast.oil.repository.WubaUserRepository;
import org.apache.http.util.TextUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.util.StringUtils;

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
    @Autowired
    WubaUidRepository wubaUidRepository;
    String wubatouxiang = "";
    static final long DAY = 24*60*60*1000;
    @RequestMapping(value = "/addaccount", method = RequestMethod.POST)
    public String addAccount(HttpServletRequest request) {
        String ios = request.getParameter("ios");
        String phone = request.getParameter("phone");
        String android = request.getParameter("android");
        String filename = request.getParameter("filename");
        List<DouyinAccount> list = douyinAccountRepository.findByIos(ios);
        RespInfo respInfo = new RespInfo();
        respInfo.status = 400;
        respInfo.message = "fail";
        respInfo.data = "账号已经存在";
        if(list != null && list.size() > 0) {
            return JSON.toJSONString(respInfo);
        }
        DouyinAccount douyinAccount = new DouyinAccount();
        douyinAccount.setPhone(phone);
        douyinAccount.setAndroid(android);
        douyinAccount.setIos(ios);
        douyinAccount.setFilename(filename);
        douyinAccount.setAndroidusetimes(0L);
        douyinAccount.setIosusetimes(0L);
        long currentTime = System.currentTimeMillis();
        douyinAccount.setCreatetimes(currentTime);
        douyinAccount.setUpdatetimes(currentTime-DAY);
        douyinAccount.setFollowtimes(currentTime-DAY);
        douyinAccountRepository.save(douyinAccount);
        respInfo.status = 200;
        respInfo.message = "ok";
        respInfo.data = "上传成功";
        return JSON.toJSONString(respInfo);
    }

    @RequestMapping(value = "/addaccountlist", method = RequestMethod.POST)
    public String addAccountList(HttpServletRequest request) {
        String ios = request.getParameter("ios");
        String filename = request.getParameter("filename");
        String[] ioss = ios.split("\r\n");
        RespInfo respInfo = new RespInfo();
        respInfo.status = 200;
        respInfo.message = "ok";
        respInfo.data = "上传成功";
        if(ioss != null && ioss.length>0){
            for(String str:ioss){
                String acc = str;
                if(str.contains("----")){
                    acc = str.split("----")[1];
                }
                List<DouyinAccount> list = douyinAccountRepository.findByIos(acc);
                if(list == null || list.size() == 0) {
                    DouyinAccount douyinAccount = new DouyinAccount();
                    douyinAccount.setIos(acc);
                    douyinAccount.setFilename(filename);
                    douyinAccount.setAndroidusetimes(0L);
                    douyinAccount.setIosusetimes(0L);
                    long currentTime = System.currentTimeMillis();
                    douyinAccount.setCreatetimes(currentTime);
                    douyinAccount.setUpdatetimes(currentTime-DAY);
                    douyinAccount.setFollowtimes(currentTime-DAY);
                    douyinAccountRepository.save(douyinAccount);
                }
            }
        }else{
            respInfo.status = 400;
            respInfo.message = "fail";
            respInfo.data = "账号格式不正确";
        }

        return JSON.toJSONString(respInfo);
    }

    @RequestMapping(value = "/deleteaccount", method = RequestMethod.POST)
    public String deleteAccount(HttpServletRequest request) {
        String ios = request.getParameter("ios");
        RespInfo respInfo = new RespInfo();
        respInfo.status = 200;
        respInfo.message = "ok";
        respInfo.data = "删除成功";
        List<DouyinAccount> list = douyinAccountRepository.findByIos(ios);
        if(list != null && list.size() > 0) {
            douyinAccountRepository.delete(list.get(0));
        }else{
            respInfo.status = 404;
            respInfo.message = "fail";
            respInfo.data = "账号不存在";
        }
        return JSON.toJSONString(respInfo);
    }

    @RequestMapping(value = "/douyinacc", method = RequestMethod.GET)
    public String getFollowAcc(HttpServletRequest request) {
        String usetype = request.getParameter("usetype");
        String filename = request.getParameter("filename");
        Long currenttime = System.currentTimeMillis();
        Long yesterday = currenttime - DAY;
        List<DouyinAccount> list;
        if("follow".equals(usetype)) {
            if(StringUtils.isEmpty(filename)) {
                list = douyinAccountRepository.findByFollowtimesLessThan(yesterday);
            }else{
                list = douyinAccountRepository.findByFilenameAndFollowtimesLessThan(filename,yesterday);
            }
        }else{
            if(StringUtils.isEmpty(filename)) {
                list = douyinAccountRepository.findByUpdatetimesLessThan(yesterday);
            }else{
                list = douyinAccountRepository.findByFilenameAndUpdatetimesLessThan(filename,yesterday);
            }
        }
        DouyinAccount douyinAccount = new DouyinAccount();
        RespInfo respInfo = new RespInfo();
        respInfo.status = 404;
        respInfo.message = "fail";
        respInfo.data = "没有可用的账号";
        if(list != null && list.size() > 0) {
            int count = list.size();
            int index = new Random().nextInt(count);
            douyinAccount = list.get(index);
            if("follow".equals(usetype)) {
                douyinAccount.setFollowtimes(currenttime);
            }else{
                douyinAccount.setUpdatetimes(currenttime);
            }
            douyinAccount.setIosusetimes(douyinAccount.getIosusetimes()+1);
            douyinAccountRepository.save(douyinAccount);
        }
        return StringUtils.isEmpty(douyinAccount.getIos()) ? "":douyinAccount.getIos();
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
                wubaUser.setUid(uid);
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


    @RequestMapping(value = "/wubatouxiang", method = RequestMethod.GET)
    public String getWubaTouxiang(HttpServletRequest request) {
        RespInfo respInfo = new RespInfo();
        respInfo.status = 200;
        respInfo.message = "ok";
        respInfo.data = wubatouxiang;
        return JSON.toJSONString(respInfo);
    }

    @RequestMapping(value = "/wubatouxiang", method = RequestMethod.POST)
    public String setWubaTouxiang(HttpServletRequest request) {
        wubatouxiang = request.getParameter("wubatouxiang");
        RespInfo respInfo = new RespInfo();
        respInfo.status = 200;
        respInfo.message = "ok";
        respInfo.data = wubatouxiang;
        return JSON.toJSONString(respInfo);
    }

    @RequestMapping(value = "/wubauid", method = RequestMethod.POST)
    public String setwubauid(HttpServletRequest request) {
        String wubauids = request.getParameter("uids");
        int follow = Integer.valueOf(request.getParameter("follow"));
        String[] uidarr = wubauids.split("----");
        int length = 0;
        int enablelength = 0;
        RespInfo respInfo = new RespInfo();
        respInfo.status = 200;
        respInfo.message = "ok";
        StringBuilder sb = new StringBuilder();
        if(uidarr!= null && uidarr.length>0){
            length = uidarr.length;
            for(String uid : uidarr){
                List<WubaUid> list = wubaUidRepository.findByUid(uid);
                if((list == null || list.size() == 0) && (enablelength < follow)){
                    WubaUid wubaUid = new WubaUid();
                    wubaUid.setUid(uid);
                    wubaUid.setUsetime(1);
                    wubaUid.setCreatetimes(System.currentTimeMillis());
                    wubaUid.setUpdatetimes(System.currentTimeMillis());
                    wubaUidRepository.save(wubaUid);
                    enablelength = enablelength + 1;
                    if (TextUtils.isEmpty(sb)) {
                        sb.append(wubaUid.getUid());
                    } else {
                        sb.append("----");
                        sb.append(wubaUid.getUid());
                    }
                }
            }
        }
        respInfo.data = sb.toString();
//        respInfo.data = String.format("上传了%d个Uid,有效Uid%d个",length,enablelength);
        return JSON.toJSONString(respInfo);
    }

    @RequestMapping(value = "/wubauid", method = RequestMethod.GET)
    public String getwubauid(HttpServletRequest request) {
        List<WubaUid> list = wubaUidRepository.findByUsetimeLimite(0);
        RespInfo respInfo = new RespInfo();
        respInfo.status = 200;
        respInfo.message = "ok";
        respInfo.data = "";
        StringBuilder sb = new StringBuilder();
        if(list!=null && list.size()>0){
            for(WubaUid wubaUid:list){
                wubaUid.setUsetime(wubaUid.getUsetime() + 1);
                wubaUid.setUpdatetimes(System.currentTimeMillis() + 1);
                wubaUidRepository.save(wubaUid);
                if (TextUtils.isEmpty(sb)) {
                    sb.append(wubaUid.getUid());
                } else {
                    sb.append("----");
                    sb.append(wubaUid.getUid());
                }

            }
        }
        respInfo.data = sb.toString();
        return JSON.toJSONString(respInfo);
    }


    @RequestMapping(value = "/wubauserfollow", method = RequestMethod.POST)
    public String setwubauserfollow(HttpServletRequest request) {
        String followjson = request.getParameter("followjson");
        String uid = request.getParameter("uid");
        String wubacook1 = request.getParameter("wubacook1");
        String wubacook2 = request.getParameter("wubacook2");
        long currenttime = System.currentTimeMillis();
        WubaUser wubaUser;
        RespInfo respInfo = new RespInfo();
        respInfo.status = 404;
        respInfo.message = "fail";
        respInfo.data = "上传账号失败";
        try {
            JSONObject jsonObject = new JSONObject(wubacook1);
            JSONObject data = (JSONObject) jsonObject.get("data");
            String mobile = data.getString("MOBILE");
            List<WubaUser> list = wubaUserRepository.findByUid(uid);
            if(list!=null&&list.size()>0){
                wubaUser = list.get(0);
                wubaUser.setUpdatetimes(currenttime);
                wubaUser.setFollowjson(followjson);
                wubaUser.setWubacook1(wubacook1);
                wubaUser.setWubacook2(wubacook2);
                wubaUser.setUid(uid);
                wubaUserRepository.save(wubaUser);
                respInfo.status = 200;
                respInfo.message = "ok";
                respInfo.data = "账号已存在，更新信息完成";
            }else{
                wubaUser = new WubaUser();
                wubaUser.setCreatetimes(currenttime);
                wubaUser.setUpdatetimes(currenttime- 24*60*60*1000);
                wubaUser.setUid(uid);
                wubaUser.setLogin(0);
                wubaUser.setWubacook1(wubacook1);
                wubaUser.setWubacook2(wubacook2);
                wubaUser.setFollowjson(followjson);
                wubaUserRepository.save(wubaUser);
                respInfo.status = 200;
                respInfo.message = "ok";
                respInfo.data = "上传关注信息成功";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return JSON.toJSONString(respInfo);
    }


    @RequestMapping(value = "/wubauserfollow", method = RequestMethod.GET)
    public String getwubauserfollow(HttpServletRequest request) {
        String uid = request.getParameter("uid");
        List<WubaUser> list = wubaUserRepository.findByUid(uid);
        RespInfo respInfo = new RespInfo();
        respInfo.status = 404;
        respInfo.message = "fail";
        respInfo.data = "";
        if(list!=null && list.size()>0){
            WubaUser wubaUser = list.get(0);
            respInfo.status = 200;
            respInfo.message = "ok";
            respInfo.data = wubaUser.getFollowjson();
        }
        return JSON.toJSONString(respInfo);
    }
}
