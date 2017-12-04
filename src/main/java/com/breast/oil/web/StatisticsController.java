package com.breast.oil.web;


import com.breast.oil.domain.WXInfo;
import com.breast.oil.repository.WXInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by B04e on 2017/11/28.
 */
@RestController
public class StatisticsController {

    @Autowired
    WXInfoRepository mWXInfoRepository;

    @RequestMapping(value = "/remember",method = RequestMethod.GET)
    public String remember(HttpServletRequest request){
        String wechatId = request.getParameter("wechatId");
        String urlPath = request.getParameter("urlPath");
        String keyWord = request.getParameter("keyWord");
        WXInfo wxInfo = new WXInfo(wechatId,request.getRemoteAddr(),urlPath,keyWord == null ? "def":keyWord);
        mWXInfoRepository.save(wxInfo);
        return "{code:0}";
    }
}
