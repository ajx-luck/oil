package com.breast.oil.web;


import com.breast.oil.consts.AppConsts;
import com.breast.oil.domain.*;
import com.breast.oil.repository.WXInfoRepository;
import com.breast.oil.services.UrlMappingService;
import com.breast.oil.utils.CommonUtils;
import com.breast.oil.utils.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Created by B04e on 2017/11/28.
 */
@RestController
public class StatisticsController {

    @Autowired
    WXInfoRepository mWXInfoRepository;
    @Autowired
    UrlMappingService mUrlMappingService;

    /**
     * 记录微信点击
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/remember", method = RequestMethod.GET)
    public String remember(HttpServletRequest request) {
        String type = request.getParameter("position");
        WebInfo webInfo = mUrlMappingService.getWebInfoByIP(CommonUtils.getIpAddr(request));
        if(webInfo != null) {
            WXInfo wxInfo = new WXInfo(webInfo.getWechatId(), webInfo.getIp(), webInfo.getUrlPath(), new Date().getTime(),
                    type, webInfo.getKeyWord(), webInfo.geteKeywordid(), webInfo.getRefer(),
                    webInfo.geteMatchtype(), webInfo.geteCreative(), webInfo.geteAdposition(), webInfo.getePagenum());
            mUrlMappingService.savaWXInfo(wxInfo, webInfo.getUrlPath(), CommonUtils.getIpAddr(request));
            return "{code:0}";
        }else{
            return "{code:1}";
        }

    }



    /**
     * 记录静态网页点击
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/rememberweb", method = RequestMethod.GET)
    public String rememberWeb(HttpServletRequest request) {
        String ip = CommonUtils.getIpAddr(request);
        String urlPath = request.getParameter("urlPath");
        WebInfo webInfo = mUrlMappingService.getWebInfoByIP(ip);
        String wechatId = mUrlMappingService.getRandomWechatIdByUrl(urlPath);
        if(webInfo != null) {
            wechatId = webInfo.getWechatId();
            String keyWord = webInfo.getKeyWord() == null?"def":webInfo.getKeyWord();
            String e_keywordid = webInfo.geteKeywordid() == null ? "def":webInfo.geteKeywordid();
            HtmlInfo htmlInfo = new HtmlInfo(urlPath, new Date().getTime(), ip,
                    wechatId, keyWord, e_keywordid);
            if(!"fxc".equals(urlPath)) {
                mUrlMappingService.savaHtmlWebInfo(htmlInfo);
            }
            String str = String.format("{\"wechatId\":\"%s\",\"keyWord\":\"%s\",\"e_keywordid\":\"%s\",\"JS_ADD_HISTORY\":\"%s\",\"JS_ADD_BACK_LISTENER\":\"%s\",\"JS_ADD_COPY_LISTENER\":\"%s\"}"
                    ,wechatId,keyWord,e_keywordid, AppConsts.JS_ADD_HISTORY,AppConsts.JS_ADD_BACK_LISTENER,AppConsts.JS_ADD_COPY_LISTENER);
            return str;
        }
        return String.format("{\"wechatId\":\"%s\",\"keyWord\":\"def\",\"e_keywordid\":\"def\",\"JS_ADD_HISTORY\":\"%s\",\"JS_ADD_BACK_LISTENER\":\"%s\",\"JS_ADD_COPY_LISTENER\":\"%s\"}"
                ,wechatId, AppConsts.JS_ADD_HISTORY,AppConsts.JS_ADD_BACK_LISTENER,AppConsts.JS_ADD_COPY_LISTENER);
    }

    /**
     * 提供推广微信
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/wechatid", method = RequestMethod.GET)
    public String wechatid(HttpServletRequest request) {
        return mUrlMappingService.getWechatIdByIP(CommonUtils.getIpAddr(request));
    }

    /**
     * 根据关键词统计
     *
     * @param secondClick
     * @param map
     * @return
     */
    @RequestMapping(value = "/keyword", method = RequestMethod.POST)
    public String keyword(SecondClick secondClick, ModelMap map) {
        long start = TimeUtils.DateTimeParse(secondClick.getStart() + " " + secondClick.getStartTime());
        long end = TimeUtils.DateTimeParse(secondClick.getEnd() + " " + secondClick.getEndTime());
        String result = "";
        if (secondClick.getWechatId() == null || secondClick.getWechatId() == "") {
            result = "{count:" + mWXInfoRepository.countByKeyWordAndCreateTimeGreaterThanEqualAndCreateTimeLessThan(
                    secondClick.getKeyWord(), start, end) + "}";
        } else {
            result = "{count:" + mWXInfoRepository.countByKeyWordAndWechatIdAndCreateTimeGreaterThanEqualAndCreateTimeLessThan(
                    secondClick.getKeyWord(), secondClick.wechatId, start, end) + "}";
        }
        return result;
    }

    /**
     * 增加关键词
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/addkw", method = RequestMethod.GET)
    public String addKeyWord(HttpServletRequest request) {
        String keyWord = request.getParameter("kw");
        String keyWordDesc = request.getParameter("kwd");
        KeyWord kw = new KeyWord(keyWord, keyWordDesc, new Date().getTime());
        mUrlMappingService.addKeyWord(kw);
        return "{code:0}";
    }
}
