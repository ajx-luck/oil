package com.breast.oil.web;

import com.breast.oil.domain.StatisticsInfo;
import com.breast.oil.domain.WebInfo;
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

import static com.breast.oil.consts.AppConsts.*;

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



    @RequestMapping("/"+URL_1)
    public String fx1(ModelMap map, HttpServletRequest request){
        String wechatId = mUrlMappingService.getWechatIdByUrl(URL_1);
        map.addAttribute("wechat_id",wechatId);
        map.addAttribute("home","/"+URL_1);
        WebInfo info = new WebInfo();
        info.setUrlPath(URL_1);
        info.setIp(request.getRemoteAddr());
        info.setCreateTime(new Date().getTime());
        info.setPrice(mUrlMappingService.getPriceByUrl(URL_1));
        info.setWechatId(wechatId);
        mWebInfoRepository.save(info);
        return "fx1";
    }

    @RequestMapping("/show")
    public String show(ModelMap map){
        return "show";
    }

    @RequestMapping("/"+URL_2)
    public String fx2(ModelMap map, HttpServletRequest request){
        String wechatId = mUrlMappingService.getWechatIdByUrl(URL_2);
        map.addAttribute("wechat_id",wechatId);
        WebInfo info = new WebInfo();
        info.setUrlPath(URL_2);
        info.setIp(request.getRemoteAddr());
        info.setCreateTime(new Date().getTime());
        info.setPrice(mUrlMappingService.getPriceByUrl(URL_2));
        info.setWechatId(wechatId);
        mWebInfoRepository.save(info);
        return "fx2";
    }
    @RequestMapping("/"+URL_3)
    public String fx3(ModelMap map, HttpServletRequest request){
        String wechatId = mUrlMappingService.getWechatIdByUrl(URL_3);
        map.addAttribute("wechat_id",wechatId);
        WebInfo info = new WebInfo();
        info.setUrlPath(URL_3);
        info.setIp(request.getRemoteAddr());
        info.setCreateTime(new Date().getTime());
        info.setPrice(mUrlMappingService.getPriceByUrl(URL_3));
        info.setWechatId(wechatId);
        mWebInfoRepository.save(info);
        return "fx1";
    }

    @RequestMapping("/"+URL_4)
    public String fx4(ModelMap map, HttpServletRequest request){
        String wechatId = mUrlMappingService.getWechatIdByUrl(URL_4);
        map.addAttribute("wechat_id",wechatId);
        WebInfo info = new WebInfo();
        info.setUrlPath(URL_4);
        info.setIp(request.getRemoteAddr());
        info.setCreateTime(new Date().getTime());
        info.setPrice(mUrlMappingService.getPriceByUrl(URL_4));
        info.setWechatId(wechatId);
        mWebInfoRepository.save(info);
        return "fx1";
    }

    @RequestMapping("/"+URL_5)
    public String fx5(ModelMap map, HttpServletRequest request){
        String wechatId = mUrlMappingService.getWechatIdByUrl(URL_5);
        map.addAttribute("wechat_id",wechatId);
        WebInfo info = new WebInfo();
        info.setUrlPath(URL_5);
        info.setIp(request.getRemoteAddr());
        info.setCreateTime(new Date().getTime());
        info.setPrice(mUrlMappingService.getPriceByUrl(URL_5));
        info.setWechatId(wechatId);
        mWebInfoRepository.save(info);
        return "fx1";
    }

    @RequestMapping("/"+URL_6)
    public String fx6(ModelMap map, HttpServletRequest request){
        String wechatId = mUrlMappingService.getWechatIdByUrl(URL_6);
        map.addAttribute("wechat_id",wechatId);
        WebInfo info = new WebInfo();
        info.setUrlPath(URL_6);
        info.setIp(request.getRemoteAddr());
        info.setCreateTime(new Date().getTime());
        info.setPrice(mUrlMappingService.getPriceByUrl(URL_6));
        info.setWechatId(wechatId);
        mWebInfoRepository.save(info);
        return "fx1";
    }

    @RequestMapping("/"+URL_7)
    public String fx7(ModelMap map, HttpServletRequest request){
        String wechatId = mUrlMappingService.getWechatIdByUrl(URL_7);
        map.addAttribute("wechat_id",wechatId);
        WebInfo info = new WebInfo();
        info.setUrlPath(URL_7);
        info.setIp(request.getRemoteAddr());
        info.setCreateTime(new Date().getTime());
        info.setPrice(mUrlMappingService.getPriceByUrl(URL_7));
        info.setWechatId(wechatId);
        mWebInfoRepository.save(info);
        return "fx1";
    }

    @RequestMapping("/"+URL_8)
    public String fx8(ModelMap map, HttpServletRequest request){
        String wechatId = mUrlMappingService.getWechatIdByUrl(URL_8);
        map.addAttribute("wechat_id",wechatId);
        WebInfo info = new WebInfo();
        info.setUrlPath(URL_8);
        info.setIp(request.getRemoteAddr());
        info.setCreateTime(new Date().getTime());
        info.setPrice(mUrlMappingService.getPriceByUrl(URL_8));
        info.setWechatId(wechatId);
        mWebInfoRepository.save(info);
        return "fx1";
    }

    @RequestMapping("/"+URL_9)
    public String fx9(ModelMap map, HttpServletRequest request){
        String wechatId = mUrlMappingService.getWechatIdByUrl(URL_9);
        map.addAttribute("wechat_id",wechatId);
        WebInfo info = new WebInfo();
        info.setUrlPath(URL_9);
        info.setIp(request.getRemoteAddr());
        info.setCreateTime(new Date().getTime());
        info.setPrice(mUrlMappingService.getPriceByUrl(URL_9));
        info.setWechatId(wechatId);
        mWebInfoRepository.save(info);
        return "fx1";
    }

    @RequestMapping("/"+URL_10)
    public String fx10(ModelMap map, HttpServletRequest request){
        String wechatId = mUrlMappingService.getWechatIdByUrl(URL_10);
        map.addAttribute("wechat_id",wechatId);
        WebInfo info = new WebInfo();
        info.setUrlPath(URL_10);
        info.setIp(request.getRemoteAddr());
        info.setCreateTime(new Date().getTime());
        info.setPrice(mUrlMappingService.getPriceByUrl(URL_10));
        info.setWechatId(wechatId);
        mWebInfoRepository.save(info);
        return "fx1";
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

    @RequestMapping(value = "/updatewx",method = RequestMethod.GET)
    public String updatewx(ModelMap map, HttpServletRequest request){
        if("aa12345678".equals(request.getParameter("passwd"))) {
            map.addAttribute("pathToWechat",
                    mUrlMappingService.updatePriceAndWechatIdByUrl(request.getParameter("url"),
                    request.getParameter("wx"), Long.valueOf(request.getParameter("price"))));
        }
        return "update";
    }
}
