package com.breast.oil.web;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.breast.oil.consts.AppConsts;
import com.breast.oil.domain.SecondClick;
import com.breast.oil.domain.StatisticsInfo;
import com.breast.oil.domain.WebInfo;
import com.breast.oil.po.Location;
import com.breast.oil.repository.StatisticsInfoRepository;
import com.breast.oil.repository.StatisticsRepository;
import com.breast.oil.repository.WXInfoRepository;
import com.breast.oil.repository.WebInfoRepository;
import com.breast.oil.services.UrlMappingService;
import com.breast.oil.services.WxTicketService;
import com.breast.oil.utils.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
    @Autowired
    WxTicketService mWxTicketService;


    private static Log log = LogFactory.getLog(WebController.class);

    @RequestMapping(value = "/"+URL_1,method = RequestMethod.GET)
    public String fx1(ModelMap map, HttpServletRequest request){
        setInfo(map, request, URL_1, mUrlMappingService.getPriceByUrl(URL_1));
        return "fx5";
    }

    private void setInfo(ModelMap map, HttpServletRequest request, String url1, Long priceByUrl){
        setInfo(map, request, url1,"",null);
    }

    private void setInfo(ModelMap map, HttpServletRequest request, String url1,String city,HttpServletResponse response) {
        String ip = CommonUtils.getIpAddr(request);
        String wechatId = mUrlMappingService.getRandomWechatIdByUrl(url1,ip);
        if(wechatId == null){
            wechatId = mUrlMappingService.getRandomWechatIdByUrl(url1);
        }
        if(response != null){
            CookieUtils.set(response, AppConsts.WECHAT_ID_COOKIE_NAME, wechatId,60*60*24*15);
            CookieUtils.set(response, AppConsts.CITY_NAME,city,60*60*24*15);
        }
        String keyword = request.getParameter("keyword");
        String referer = request.getHeader("referer");
        String e_keywordid = request.getParameter("e_keywordid");
        String e_creative = request.getParameter("e_creative");
        String e_matchtype = request.getParameter("e_matchtype");
        String e_adposition = request.getParameter("e_adposition");
        String e_pagenum = request.getParameter("e_pagenum");
        String price = request.getParameter("price");
        String audience = request.getParameter("audience");
        String dy = request.getParameter("dy");
        String jh = request.getParameter("jh");
        map.addAttribute("wechat_id", wechatId);
        map.addAttribute("home", keyword == null ?url1:url1+"?kw="+keyword);
       /* if(StringUtils.isEmptyOrWhitespace(mWxTicketService.getTicket())){
            mWxTicketService.getTicket();
        }*/
      /*  if(!StringUtils.isEmptyOrWhitespace(e_keywordid)){
            mUrlMappingService.addKeyWordAndWebClick(e_keywordid,keyword);
        }*/
        map.addAttribute("ticket", "weixin://");
        WebInfo info = new WebInfo(url1,new Date().getTime(),CommonUtils.getIpAddr(request),
                wechatId,keyword,e_keywordid,referer,e_matchtype,e_creative,e_adposition,e_pagenum,price,audience,dy,jh);
        info.setCity(city);
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
    public String fx3(ModelMap map, HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {

        Map<String,Object> params = new HashMap<>();
        String ip = CommonUtils.getIpAddr(request);
        params.put("format","json");
        params.put("ip",ip);
        String e_creative = request.getParameter("e_creative");
        String audience = request.getParameter("audience");
        String referer = request.getHeader("referer");
        boolean isMobile = ip.equals(request.getRemoteAddr()) && DeviceUtils.isMobileDevice(request);
        String city = "";
        try {
            String result = HttpClientHelper.sendGet("http://int.dpool.sina.com.cn/iplookup/iplookup.php", params, "UTF-8");
            System.out.println(result);
            if (result != null) {
                Location location = JSONObject.parseObject(result, new TypeReference<Location>() {
                });
                city = location.city;
                if ((!isMobile) || StringUtils.isEmptyOrWhitespace(e_creative) || StringUtils.isEmptyOrWhitespace(audience) || StringUtils.isEmptyOrWhitespace(referer) || location == null || StringUtils.isEmptyOrWhitespace(location.city) || location.toString().contains("北京") || location.toString().contains("上海")
                        || location.toString().contains("广州") || location.toString().contains("深圳") || location.toString().contains("东莞") || "广州".equals(city) || "深圳".equals(city) || "北京".equals(city) || "上海".equals(city) || "东莞".equals(city)) {
                    if (isMobile && location != null && (!location.toString().contains("广东")) && (!StringUtils.isEmptyOrWhitespace(audience)) && (!StringUtils.isEmptyOrWhitespace(referer)) && (!StringUtils.isEmptyOrWhitespace(e_creative))) {
                        if("北京".equals(city) || "北京".equals(location.province) || "北京".equals(location.country) || location.toString().contains("北京") || location.toString().contains("上海")
                                || location.toString().contains("广州") || location.toString().contains("深圳") || location.toString().contains("东莞") || "广州".equals(city) || "深圳".equals(city) || "北京".equals(city) || "上海".equals(city) || "东莞".equals(city)){
                            setInfo(map, request, "fxn", city, response);
                            return "forward:/fxpy.html";
                        }else{
                            setInfo(map, request, "fxh", city, response);
                            return "forward:/fxcg.html";
                        }

                    }else {
                        setInfo(map, request, "fxn", city, response);
                        return "forward:/fxpy.html";
                    }
                }else{
                    setInfo(map, request, "fxh", city, response);
                    return "forward:/fxcg.html";
                }

//                }else if(TimeUtils.isAdTimes()){
//                    setInfo(map, request, "fxn",city, response);
//                    return "forward:/fxn.html";
//                }

            }
        }catch (Exception e){
            log.error(e);
            setInfo(map, request, URL_3,city, response);
            return "forward:/fxn.html";
        }
        setInfo(map, request, URL_3,city, response);
        return "forward:/fxn.html";
    }



    @RequestMapping(value = "/"+URL_4,method = RequestMethod.GET)
    public String fx4(ModelMap map, HttpServletRequest request){
        setInfo(map, request, URL_4, mUrlMappingService.getPriceByUrl(URL_4));
        return "fxqrcode";
    }

    @RequestMapping(value = "/"+URL_5,method = RequestMethod.GET)
    public String fx5(ModelMap map, HttpServletRequest request){
        setInfo(map, request, URL_5, mUrlMappingService.getPriceByUrl(URL_5));
        return "fx5";
    }

    @RequestMapping(value = "/"+URL_6,method = RequestMethod.GET)
    public String fx6(ModelMap map, HttpServletRequest request){
        setInfo(map, request, URL_6, mUrlMappingService.getPriceByUrl(URL_6));
        return "fx5";
    }

    @RequestMapping(value = "/"+URL_7,method = RequestMethod.GET)
    public String fx7(ModelMap map, HttpServletRequest request){
        setInfo(map, request, URL_7, mUrlMappingService.getPriceByUrl(URL_7));
        return "fx5";
    }

    @RequestMapping(value = "/"+URL_8,method = RequestMethod.GET)
    public String fx8(ModelMap map, HttpServletRequest request){
        setInfo(map, request, URL_8, mUrlMappingService.getPriceByUrl(URL_8));
        return "fx5";
    }

    @RequestMapping(value = "/"+URL_9,method = RequestMethod.GET)
    public String fx9(ModelMap map, HttpServletRequest request){
        setInfo(map, request, URL_9, mUrlMappingService.getPriceByUrl(URL_9));
        return "fx5";
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
        return "wxinfo";
    }

    @RequestMapping(value = "/zixun",method = RequestMethod.GET)
    public String zixun(ModelMap map, HttpServletRequest request){
        String ip = request.getRemoteAddr();
        String wechatId = mUrlMappingService.getLastWechatIdByIp(ip);
        map.addAttribute("wechat_id", wechatId);
        return "zixun";
    }

    /**
     * 获取请求参数保存，并重定向
     * @param modelMap
     * @param request
     * @return
     */
    @RequestMapping(value = "/fxhold", method = RequestMethod.GET)
    public String getRedirectWeb(ModelMap modelMap,HttpServletRequest request) {
        Map<String,String[]> map = request.getParameterMap();
        WebInfo webInfo = new WebInfo();
        for (Map.Entry<String, String[]> entry : map.entrySet()) {
            Field f = null;
            try {
                if(!"createTime".equals(map.entrySet())) {
                    f = webInfo.getClass().getDeclaredField(entry.getKey());
                    f.setAccessible(true);
                    f.set(webInfo, entry.getValue()[0]);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        webInfo.setCreateTime(new Date().getTime());
        mUrlMappingService.savaWebInfo(webInfo,webInfo.getUrlPath(),webInfo.getIp());
        String url = "redirect:/baidu/form.html?";
        try {
            url = String.format("redirect:/baidu/form.html?word=%s",URLEncoder.encode(webInfo.getKeyWord(),"utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return url;
    }




}
