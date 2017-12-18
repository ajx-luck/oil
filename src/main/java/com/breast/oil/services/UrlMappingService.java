package com.breast.oil.services;

import com.breast.oil.domain.PathToWechat;
import com.breast.oil.repository.PathToWechatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.breast.oil.consts.AppConsts.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

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

    public List<PathToWechat> updateAllPathToWechat(){
        mPathToWechats = mPathToWechatRepository.findAll();
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

    /**
     * 根据url获取随机微信号
     * @param url
     * @return
     */
    public String getRandomWechatIdByUrl(String url){
        if(getAllPathToWechat() == null){
            return null;
        }
        List<PathToWechat> list = new ArrayList();
        for(PathToWechat pathToWechat: getAllPathToWechat()){
            if(pathToWechat.getUrlPath().equals(url)){
                list.add(pathToWechat);
            }
        }
        if(list.size() == 0) {
            return null;
        }else{
            return list.get(new Random().nextInt(list.size())).getWechatId();
        }
    }

    /**
     * 根据url获取第一个微信号
     * @param wechatId
     * @return
     */
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

    /**
     * 根据url获取价格
     * @param url
     * @return
     */
    public Long getPriceByUrl(String url){
        if(getAllPathToWechat() == null){
            return null;
        }
        for(PathToWechat pathToWechat: getAllPathToWechat()){
            if(pathToWechat.getUrlPath().equals(url)){
                return pathToWechat.getPrice();
            }
        }
        return null;
    }

    /**
     * 更新价格根据url和微信
     * @param url
     * @param wechatId
     * @param price
     * @return
     */
    public PathToWechat updatePriceAndWechatIdByUrl(String url,String wechatId,Long price){
        if(getAllPathToWechat() == null){
            return null;
        }
        for(PathToWechat pathToWechat: getAllPathToWechat()){
            if(pathToWechat.getUrlPath().equals(url)){
                pathToWechat.setPrice(price);
                pathToWechat.setWechatId(wechatId);
                mPathToWechatRepository.save(pathToWechat);
                updateAllPathToWechat();
                return pathToWechat;
            }
        }
        return null;
    }

    /**
     * 根据id查询url
     * @param id
     * @return
     */
    public String getUrlById(Long id){
        if(getAllPathToWechat() == null){
            return null;
        }
        for(PathToWechat pathToWechat: getAllPathToWechat()){
            if(pathToWechat.getId().equals(id)){
                return pathToWechat.getUrlPath();
            }
        }
        return null;
    }

    /**
     * 新增匹配的url
     * @param url
     * @param wechatId
     * @param price
     * @return
     */
    public PathToWechat addUrlAndWechat(String url,String wechatId,Long price){
        PathToWechat pathToWechat = new PathToWechat();
        pathToWechat.setWechatId(wechatId);
        pathToWechat.setUrlPath(url);
        pathToWechat.setPrice(price);
        pathToWechat.setCreateTime(new Date().getTime());
        mPathToWechatRepository.save(pathToWechat);
        return pathToWechat;
    }

    public void deleteByWechatId(String wechatId){
        mPathToWechatRepository.deleteByWechatId(wechatId);
    }
}
