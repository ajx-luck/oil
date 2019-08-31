package com.breast.oil.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.breast.oil.domain.MusicUid;
import com.breast.oil.domain.WubaUid;
import com.breast.oil.po.RespInfo;
import com.breast.oil.repository.MusicUidRepository;
import org.apache.http.util.TextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class MusicController {
    @Autowired
    MusicUidRepository musicUidRepository;

    @RequestMapping(value = "/musicuid", method = RequestMethod.POST)
    public String setmusicuid(HttpServletRequest request) {
        String uids = request.getParameter("uids");
        RespInfo respInfo = new RespInfo();
        respInfo.status = 200;
        respInfo.message = "ok";
        List<MusicUid> musicUids = JSONObject.parseArray(uids, MusicUid.class);
        respInfo.data = "上传成功";
        if(musicUids != null) {
            int length = musicUids.size();
            int avalength = 0;

            for (MusicUid musicUid : musicUids) {
                String uid = musicUid.getUid();
                List<MusicUid> list = musicUidRepository.findByUid(uid);
                if ((list == null || list.size() == 0)) {
                    avalength = avalength + 1;
                    musicUid.setUid(uid);
                    musicUid.setUsetime(0);
                    musicUid.setCreatetimes(System.currentTimeMillis());
                    musicUid.setUpdatetimes(System.currentTimeMillis());
                    musicUidRepository.save(musicUid);
                }
            }
            respInfo.data = "上传UID数量为"+length+"有效UID数量为"+avalength;
        }

//        respInfo.data = String.format("上传了%d个Uid,有效Uid%d个",length,enablelength);
        return JSON.toJSONString(respInfo);
    }

    @RequestMapping(value = "/musicuid", method = RequestMethod.GET)
    public String getmusicuid(HttpServletRequest request) {
        int usetime = Integer.parseInt(request.getParameter("usetime"));
//        int usetime = 100;
        int gender = Integer.parseInt(request.getParameter("gender"));
        int maxlength = Integer.parseInt(request.getParameter("maxlength"));
        List<MusicUid> list = musicUidRepository.findByUsetimeLimite(usetime,gender,maxlength);
        RespInfo respInfo = new RespInfo();
        respInfo.status = 200;
        respInfo.message = "ok";
        respInfo.data = "";
        if(list!=null && list.size()>0){
            StringBuilder sb = new StringBuilder();
            for(MusicUid wubaUid:list){
                wubaUid.setUsetime(wubaUid.getUsetime() + 1);
                wubaUid.setUpdatetimes(System.currentTimeMillis() + 1);
                musicUidRepository.save(wubaUid);
                if (TextUtils.isEmpty(sb)) {
                    sb.append(wubaUid.getUid());
                } else {
                    sb.append("----");
                    sb.append(wubaUid.getUid());
                }
            }
//            sb.append("----");
//            sb.append("1966035142");
            respInfo.data = sb.toString();
        }

        return JSON.toJSONString(respInfo);
    }

}
