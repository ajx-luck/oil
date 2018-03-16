package com.breast.oil.web;


import com.breast.oil.domain.KeyWord;
import com.breast.oil.domain.SecondClick;
import com.breast.oil.domain.WXInfo;
import com.breast.oil.domain.WebInfo;
import com.breast.oil.repository.WXInfoRepository;
import com.breast.oil.services.UrlMappingService;
import com.breast.oil.utils.FormatUtils;
import com.breast.oil.utils.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;
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
     * @param request
     * @return
     */
    @RequestMapping(value = "/remember",method = RequestMethod.GET)
    public String remember(HttpServletRequest request){
        String wechatId = request.getParameter("wechatId");
        String urlPath = request.getParameter("urlPath");
        String[] paths = urlPath.split("\\?kw");
        urlPath = paths[0];
        String keyWord = request.getParameter("keyWord");
        String type = request.getParameter("position");
        String e_keywordid = FormatUtils.decode(request.getParameter("e_keywordid"));
        String e_creative = request.getParameter("e_creative");
        if(!StringUtils.isEmptyOrWhitespace(e_keywordid)){
            mUrlMappingService.addKeyWordAndWxClick(e_keywordid,keyWord);
        }
        WXInfo wxInfo = new WXInfo(wechatId,request.getHeader("X-Real-IP"),urlPath,keyWord == null ? "def":keyWord,e_creative,e_keywordid,type,new Date().getTime());
        mUrlMappingService.savaWXInfo(wxInfo,urlPath,request.getHeader("X-Real-IP"));
        return "{code:0}";
    }

    @RequestMapping(value = "/rememberwx",method = RequestMethod.GET)
    public String rememberwx(HttpServletRequest request){
        String ip = request.getHeader("X-Real-IP");
        WebInfo webInfo = mUrlMappingService.getWebInfoByIP(ip);
        if(webInfo != null){
            String wechatId = webInfo.getWechatId();
            String urlPath = webInfo.getUrlPath();
            String keyWord = webInfo.getKeyWord();
            String type = "";
            String e_keywordid = webInfo.getKeywordid();
            String e_creative = webInfo.getCreative();
            WXInfo wxInfo = new WXInfo(wechatId,request.getHeader("X-Real-IP"),urlPath,keyWord == null ? "def":keyWord,e_creative,e_keywordid,type,new Date().getTime());
            mUrlMappingService.savaWXInfo(wxInfo,urlPath,request.getHeader("X-Real-IP"));
        }

        return "{code:0}";
    }

    /**
     * 提供推广微信
     * @param request
     * @return
     */
    @RequestMapping(value = "/wechatid",method = RequestMethod.GET)
    public String wechatid(HttpServletRequest request){
        return mUrlMappingService.getWechatIdByIP(request.getHeader("X-Real-IP"));
    }

    /**
     * 根据关键词统计
     * @param secondClick
     * @param map
     * @return
     */
    @RequestMapping(value = "/keyword",method = RequestMethod.POST)
    public String keyword(SecondClick secondClick,ModelMap map){
        long start = TimeUtils.DateTimeParse(secondClick.getStart() + " "+secondClick.getStartTime());
        long end = TimeUtils.DateTimeParse(secondClick.getEnd() + " "+secondClick.getEndTime());
        String result = "";
        if(secondClick.getWechatId() == null || secondClick.getWechatId() == "") {
            result = "{count:" + mWXInfoRepository.countByKeyWordAndCreateTimeGreaterThanEqualAndCreateTimeLessThan(
                    secondClick.getKeyWord(), start, end) + "}";
        }else{
            result = "{count:" + mWXInfoRepository.countByKeyWordAndWechatIdAndCreateTimeGreaterThanEqualAndCreateTimeLessThan(
                    secondClick.getKeyWord(),secondClick.wechatId, start, end) + "}";
        }
        return result;
    }

    /**
     * 增加关键词
     * @param request
     * @return
     */
    @RequestMapping(value = "/addkw",method = RequestMethod.GET)
    public String addKeyWord(HttpServletRequest request){
        String keyWord = request.getParameter("kw");
        String keyWordDesc = request.getParameter("kwd");
        KeyWord kw = new KeyWord(keyWord,keyWordDesc,new Date().getTime());
        mUrlMappingService.addKeyWord(kw);
        return "{code:0}";
    }
}
