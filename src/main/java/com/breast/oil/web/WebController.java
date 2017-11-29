package com.breast.oil.web;

import com.breast.oil.consts.AppConsts;
import com.breast.oil.domain.PathToWechat;
import com.breast.oil.domain.StatisticsInfo;
import com.breast.oil.domain.WebInfo;
import com.breast.oil.repository.PathToWechatRepository;
import com.breast.oil.repository.StatisticsInfoRepository;
import com.breast.oil.repository.StatisticsRepository;
import com.breast.oil.repository.WebInfoRepository;
import com.breast.oil.services.UrlMappingService;
import com.breast.oil.utils.FormatUtils;
import com.breast.oil.utils.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Controller
public class WebController {

    @Autowired
    WebInfoRepository mWebInfoRepository;
    @Autowired
    StatisticsRepository mStatisticsRepository;
    @Autowired
    StatisticsInfoRepository mStatisticsInfoRepository;
    @Autowired
    UrlMappingService mUrlMappingService;


    @RequestMapping("/show")
    public String show(ModelMap map){
        return "show";
    }

    @RequestMapping("/fx2")
    public String fx2(ModelMap map, HttpServletRequest request){
        String wechatId = mUrlMappingService.getWechatIdByUrl("fx2");
        map.addAttribute("wechat_id",wechatId);
        WebInfo info = new WebInfo();
        info.setUrlPath("fx2");
        info.setIp(request.getRemoteAddr());
        info.setCreateTime(new Date().getTime());
        info.setPrice(AppConsts.price);
        info.setWechatId(wechatId);
        mWebInfoRepository.save(info);
        return "fx2";
    }
    @RequestMapping("/fx3")
    public String fx3(ModelMap map, HttpServletRequest request){
        String wechatId = mUrlMappingService.getWechatIdByUrl("fx3");
        map.addAttribute("wechat_id",wechatId);
        WebInfo info = new WebInfo();
        info.setUrlPath("fx3");
        info.setIp(request.getRemoteAddr());
        info.setCreateTime(new Date().getTime());
        info.setPrice(AppConsts.price);
        info.setWechatId(wechatId);
        mWebInfoRepository.save(info);
        return "fx2";
    }
    @RequestMapping("/fx1")
    public String fx1(ModelMap map, HttpServletRequest request){
        String wechatId = mUrlMappingService.getWechatIdByUrl("fx1");
        map.addAttribute("wechat_id",wechatId);
        WebInfo info = new WebInfo();
        info.setUrlPath("fx1");
        info.setIp(request.getRemoteAddr());
        info.setCreateTime(new Date().getTime());
        info.setPrice(AppConsts.price);
        info.setWechatId(wechatId);
        mWebInfoRepository.save(info);
        return "show";
    }

    @RequestMapping("/sum")
    public String sum(ModelMap map){
        StatisticsInfo statisticsInfo = new StatisticsInfo();
        map.addAttribute(statisticsInfo);
        return "sum";
    }

    @RequestMapping(value = "/result",method = RequestMethod.POST)
    public String result(StatisticsInfo statisticsInfo, ModelMap map){

        long start = TimeUtils.DateTimeParse(statisticsInfo.getStart() + " "+statisticsInfo.getStartTime());
        long end = TimeUtils.DateTimeParse(statisticsInfo.getEnd() + " "+statisticsInfo.getEndTime());
        long total = mStatisticsRepository.totalMoney(statisticsInfo.getWechatId(),start,end);
        statisticsInfo.setStart(TimeUtils.timesToDate(start));
        statisticsInfo.setEnd(TimeUtils.timesToDate(end));
        statisticsInfo.setResult(FormatUtils.formatMoney(total));
        statisticsInfo.setAverage(FormatUtils.formatMoney(total/statisticsInfo.wechatAdd));
        statisticsInfo.setCreateTime(new Date().getTime());
        statisticsInfo.setUrl(mUrlMappingService.getUrlByWechatId(statisticsInfo.wechatId));
        map.addAttribute("statisticsInfo", statisticsInfo);
        mStatisticsInfoRepository.save(statisticsInfo);
        return "result";
    }
}
