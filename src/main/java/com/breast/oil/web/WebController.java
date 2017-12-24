package com.breast.oil.web;

import com.breast.oil.domain.SecondClick;
import com.breast.oil.domain.StatisticsInfo;
import com.breast.oil.domain.WebInfo;
import com.breast.oil.repository.StatisticsInfoRepository;
import com.breast.oil.repository.StatisticsRepository;
import com.breast.oil.repository.WXInfoRepository;
import com.breast.oil.repository.WebInfoRepository;
import com.breast.oil.result.Response;
import com.breast.oil.services.UrlMappingService;
import com.breast.oil.utils.FormatUtils;
import com.breast.oil.utils.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    @Autowired
    WXInfoRepository mWXInfoRepository;



    @RequestMapping(value = "/"+URL_1,method = RequestMethod.GET)
    public String fx1(ModelMap map, HttpServletRequest request){
        setInfo(map, request, URL_1, mUrlMappingService.getPriceByUrl(URL_1));
        return "fx5";
    }

    private void setInfo(ModelMap map, HttpServletRequest request, String url1, Long priceByUrl) {
        String ip = request.getRemoteAddr();
        String wechatId = mUrlMappingService.getRandomWechatIdByUrl(url1,ip);
        if(wechatId == null){
            wechatId = mUrlMappingService.getRandomWechatIdByUrl(url1);
        }
        String kw = request.getParameter("kw");
        map.addAttribute("wechat_id", wechatId);
        map.addAttribute("home", kw == null ?url1:url1+"?kw="+kw);
        WebInfo info = new WebInfo();
        info.setUrlPath(url1);
        info.setIp(request.getRemoteAddr());
        info.setCreateTime(new Date().getTime());
        info.setPrice(priceByUrl);
        info.setWechatId(wechatId);
        info.setKeyWord(kw);
        mUrlMappingService.savaWebInfo(info,url1,ip);
    }

    @RequestMapping("/show")
    public String show(ModelMap map){
        return "show";
    }

    @RequestMapping(value = "/"+URL_2,method = RequestMethod.GET)
    public String fx2(ModelMap map, HttpServletRequest request){
        setInfo(map, request, URL_2, mUrlMappingService.getPriceByUrl(URL_2));
        return "fx5";
    }
    @RequestMapping(value = "/"+URL_3,method = RequestMethod.GET)
    public String fx3(ModelMap map, HttpServletRequest request){
        setInfo(map, request, URL_3, mUrlMappingService.getPriceByUrl(URL_3));
        return "fx5";
    }

    @RequestMapping(value = "/"+URL_4,method = RequestMethod.GET)
    public String fx4(ModelMap map, HttpServletRequest request){
        setInfo(map, request, URL_4, mUrlMappingService.getPriceByUrl(URL_4));
        return "fxqrcode";
    }

    @RequestMapping(value = "/"+URL_5,method = RequestMethod.GET)
    public String fx5(ModelMap map, HttpServletRequest request){
        setInfo(map, request, URL_5, mUrlMappingService.getPriceByUrl(URL_5));
        return "fx2";
    }

    @RequestMapping(value = "/"+URL_6,method = RequestMethod.GET)
    public String fx6(ModelMap map, HttpServletRequest request){
        setInfo(map, request, URL_6, mUrlMappingService.getPriceByUrl(URL_6));
        return "fx2";
    }

    @RequestMapping(value = "/"+URL_7,method = RequestMethod.GET)
    public String fx7(ModelMap map, HttpServletRequest request){
        setInfo(map, request, URL_7, mUrlMappingService.getPriceByUrl(URL_7));
        return "fx2";
    }

    @RequestMapping(value = "/"+URL_8,method = RequestMethod.GET)
    public String fx8(ModelMap map, HttpServletRequest request){
        setInfo(map, request, URL_8, mUrlMappingService.getPriceByUrl(URL_8));
        return "fx1";
    }

    @RequestMapping(value = "/"+URL_9,method = RequestMethod.GET)
    public String fx9(ModelMap map, HttpServletRequest request){
        setInfo(map, request, URL_9, mUrlMappingService.getPriceByUrl(URL_9));
        return "fx1";
    }

    @RequestMapping(value = "/"+URL_10,method = RequestMethod.GET)
    public String fx10(ModelMap map, HttpServletRequest request){
        setInfo(map, request, URL_10, mUrlMappingService.getPriceByUrl(URL_10));
        return "fx1";
    }

    /**
     * 手动统计数据
     * @param map
     * @return
     */
    @RequestMapping("/sum")
    public String sum(ModelMap map){
        StatisticsInfo statisticsInfo = new StatisticsInfo();
        StatisticsInfo statisticsInfo1 = new StatisticsInfo();
        map.addAttribute("statisticsInfo",statisticsInfo);
        map.addAttribute("statisticsInfo1",statisticsInfo1);
        return "sum";
    }

    /**
     * 统计数据结果
     * @param statisticsInfo
     * @param map
     * @return
     */
    @RequestMapping(value = "/result",method = RequestMethod.POST)
    public String result(StatisticsInfo statisticsInfo, ModelMap map){
        long start = TimeUtils.DateTimeParse(statisticsInfo.getStart() + " "+statisticsInfo.getStartTime());
        long end = TimeUtils.DateTimeParse(statisticsInfo.getEnd() + " "+statisticsInfo.getEndTime());
        long total = mWebInfoRepository.countByUrlPathAndCreateTimeGreaterThanEqualAndCreateTimeLessThan(statisticsInfo.getWechatId(),start,end);
        long wechatRAdd = mWXInfoRepository.countByUrlPathAndCreateTimeGreaterThanEqualAndCreateTimeLessThan(statisticsInfo.getWechatId(),start,end);
        long wechatAdd = statisticsInfo.getWechatAdd();
        long addWX = wechatAdd == 0?wechatRAdd:wechatAdd;
        statisticsInfo.setStart(TimeUtils.timesToDate(start));
        statisticsInfo.setEnd(TimeUtils.timesToDate(end));
        statisticsInfo.setResult(total+"次");
        statisticsInfo.setWechatRAdd(wechatRAdd);
        statisticsInfo.setAverage(FormatUtils.percentage(addWX,total));
        statisticsInfo.setCreateTime(new Date().getTime());
        statisticsInfo.setUrl(statisticsInfo.wechatId);
        map.addAttribute("statisticsInfo", statisticsInfo);
        if(wechatAdd>0) {
            statisticsInfo.setAverageMoney(statisticsInfo.getSaleMoney()/statisticsInfo.getWechatAdd());
            mStatisticsInfoRepository.save(statisticsInfo);
        }
        statisticsInfo.setWechatAdd((int)addWX);
        return "result";
    }

    @RequestMapping(value = "/updatewx",method = RequestMethod.GET)
    public String updatewx(ModelMap map, HttpServletRequest request){
        if("aa12345678".equals(request.getParameter("pd"))) {
            map.addAttribute("pathToWechat",
                    mUrlMappingService.updatePriceAndWechatIdByUrl(request.getParameter("url"),
                    request.getParameter("wx"), Long.valueOf(request.getParameter("price"))));
        }
        return "update";
    }

    @RequestMapping(value = "/product",method = RequestMethod.GET)
    public String product(ModelMap map,HttpServletRequest request){
        map.addAttribute("home",request.getParameter("home"));
        return "product";
    }

    @RequestMapping(value = "/about",method = RequestMethod.GET)
    public String about(ModelMap map,HttpServletRequest request){
        map.addAttribute("home",request.getParameter("home"));
        return "about";
    }

    @RequestMapping(value = "/contact",method = RequestMethod.GET)
    public String contact(ModelMap map,HttpServletRequest request){
        map.addAttribute("home",request.getParameter("home"));
        return "contact";
    }

    @RequestMapping(value = "/kw",method = RequestMethod.GET)
    public String kw(ModelMap map){
        SecondClick secondClick = new SecondClick();
        SecondClick secondClick1 = new SecondClick();
        map.addAttribute("secondClick",secondClick);
        map.addAttribute("secondClick1",secondClick1);
        return "kw";
    }

    @RequestMapping(value = "/addwx",method = RequestMethod.GET)
    public String addwx(ModelMap map, HttpServletRequest request){
        if("aa12345678".equals(request.getParameter("pd"))) {
            map.addAttribute("pathToWechat",
                    mUrlMappingService.addUrlAndWechat(request.getParameter("url"),
                            request.getParameter("wx"), Long.valueOf(request.getParameter("price"))));
        }
        return "update";
    }

    @ResponseBody
    @RequestMapping(value = "/deletewx",method = RequestMethod.GET)
    public ResponseEntity deletewx(ModelMap map, HttpServletRequest request){
        if("aa12345678".equals(request.getParameter("pd"))) {
            mUrlMappingService.deleteByWechatId(request.getParameter("wx"));
        }
        return new ResponseEntity("操作成功", HttpStatus.OK);
    }


    @RequestMapping(value = "/countall",method = RequestMethod.POST)
    public String countAll(StatisticsInfo statisticsInfo1,ModelMap map){
        long start = TimeUtils.DateTimeParse(statisticsInfo1.getStart() + " "+statisticsInfo1.getStartTime());
        long end = TimeUtils.DateTimeParse(statisticsInfo1.getEnd() + " "+statisticsInfo1.getEndTime());
        if(StringUtils.isEmptyOrWhitespace(statisticsInfo1.getWechatId())){
            if(StringUtils.isEmptyOrWhitespace(statisticsInfo1.getUrl())){
                map.addAttribute("list", mUrlMappingService.countAll(start, end));
            }else {
                map.addAttribute("list", mUrlMappingService.countAllByUrl(statisticsInfo1.getUrl(),start, end));
            }
        }else {
            map.addAttribute("list", mUrlMappingService.countAllByWechatId(statisticsInfo1.getWechatId(),start, end));
        }
        return "count";
    }

    @RequestMapping(value = "/countCost",method = RequestMethod.POST)
    public String countCost(StatisticsInfo statisticsInfo1,ModelMap map){
        long start = TimeUtils.DateTimeParse(statisticsInfo1.getStart() + " "+statisticsInfo1.getStartTime());
        long end = TimeUtils.DateTimeParse(statisticsInfo1.getEnd() + " "+statisticsInfo1.getEndTime());
        String url = statisticsInfo1.getUrl();
        map.addAttribute("list",  mUrlMappingService.countCost(url,start,end));
        if(url == null){
            statisticsInfo1.setUrl("all");
        }
        return "countcost";
    }


    @RequestMapping(value = "/weixin",method = RequestMethod.GET)
    public String wxinfo(ModelMap map, HttpServletRequest request){
        String ip = request.getRemoteAddr();
        String wechatId = mUrlMappingService.getLastWechatIdByIp(ip);
        map.addAttribute("wechat_id", wechatId);
        return "zixun";
    }
}
