package com.breast.oil.services;

import com.breast.oil.consts.AppConsts;
import com.breast.oil.domain.SecretInfo;
import com.breast.oil.domain.UserInfo;
import com.breast.oil.repository.SecretInfoRepository;
import com.breast.oil.repository.UserInfoRepository;
import com.breast.oil.utils.SymmetricEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class UserService {
    @Autowired
    UserInfoRepository mUserInfoRepository;
    @Autowired
    SecretInfoRepository mSecretInfoRepository;

    public String checkUserByDevice(String device){
        List<UserInfo> list = mUserInfoRepository.findByDevice(device);
        if(checkUser(list)) {
            return list.get(0).username;
        }else{
            return "fail";
        }
    }

    private boolean checkUser(List<UserInfo> list) {
        if(list == null || list.size() == 0){
            return false;
        }else{
            if(list.get(0).lastTime > new Date().getTime()){
                return true;
            }else{
                return false;
            }
        }
    }

    public String checkUserByTonken(String tonken){
        List<UserInfo> list = mUserInfoRepository.findByTonken(tonken);
        if(checkUser(list)) {
            return list.get(0).username;
        }else{
            return "fail";
        }
    }

    public boolean addOrUpdateUser(String pushkey,String secret,String device,String name){
        synchronized(UserService.class) {
            List<SecretInfo> infos = mSecretInfoRepository.findBySecret(secret);
            if (infos == null || infos.size() == 0 || infos.get(0).isUse) {
                return false;
            }
            SecretInfo info = infos.get(0);
            info.isUse = true;
            long availTime = info.availTime * 60 * 60 * 1000;
            long currentTime = new Date().getTime();
            List<UserInfo> list = mUserInfoRepository.findByDevice(device);
            if (list == null || list.size() == 0) {
                UserInfo userInfo = new UserInfo();
                userInfo.pushkey = pushkey;
                userInfo.device = device;
                userInfo.username = name;
                userInfo.secret = secret;
                userInfo.lastTime = currentTime + availTime;
                userInfo.tonken = SymmetricEncoder.AESEncode(AppConsts.ENCODER_KEY, device + currentTime + new Random().nextInt(5000));
                userInfo.startTime = currentTime;
                mSecretInfoRepository.save(info);
                mUserInfoRepository.save(userInfo);
                return true;
            } else {
                UserInfo userInfo = list.get(0);
                userInfo.tonken = SymmetricEncoder.AESEncode(AppConsts.ENCODER_KEY, device + currentTime + new Random().nextInt(5000));
                if (userInfo.lastTime < currentTime) {
                    userInfo.lastTime = currentTime + availTime;
                    userInfo.startTime = currentTime;
                } else {
                    userInfo.lastTime = userInfo.lastTime + availTime;
                }
                mSecretInfoRepository.save(info);
                mUserInfoRepository.save(userInfo);
                return true;
            }
        }
    }
}
