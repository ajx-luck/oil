package com.breast.oil.services;

import com.breast.oil.domain.PathToWechat;
import com.breast.oil.repository.PathToWechatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by B04e on 2017/11/28.
 */
@Service
public class UrlMappingService {
    @Autowired
    PathToWechatRepository mPathToWechatRepository;
    public  List<PathToWechat> mPathToWechats;

    public List<PathToWechat> getAllPathToWechat(){
        if(mPathToWechats == null) {
            mPathToWechats = mPathToWechatRepository.findAll();
        }
        return mPathToWechats;
    }

    public String getWechatIdByUrl(String url){
        if(getAllPathToWechat() == null){
            return null;
        }
        for(PathToWechat pathToWechat: getAllPathToWechat()){
            if(pathToWechat.getUrlPath().equals(url)){
                return pathToWechat.getWechatId();
            }
        }
        return null;
    }

    public String getUrlByWechatId(String wechatId){
        if(getAllPathToWechat() == null){
            return null;
        }
        for(PathToWechat pathToWechat: getAllPathToWechat()){
            if(pathToWechat.getWechatId().equals(wechatId)){
                return pathToWechat.getUrlPath();
            }
        }
        return null;
    }
}
