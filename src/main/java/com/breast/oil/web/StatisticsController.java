package com.breast.oil.web;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.breast.oil.consts.AppConsts;
import com.breast.oil.domain.*;
import com.breast.oil.po.ListTemplete;
import com.breast.oil.po.Location;
import com.breast.oil.po.LocationTaobao;
import com.breast.oil.po.RespInfo;
import com.breast.oil.repository.*;
import com.breast.oil.services.UrlMappingService;
import com.breast.oil.services.UserService;
import com.breast.oil.utils.CommonUtils;
import com.breast.oil.utils.HttpClientHelper;
import com.breast.oil.utils.SymmetricEncoder;
import com.breast.oil.utils.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.util.*;

import static com.breast.oil.consts.AppConsts.URL_2;

/**
 * Created by B04e on 2017/11/28.
 */
@RestController
public class StatisticsController {

    @Autowired
    WXInfoRepository mWXInfoRepository;
    @Autowired
    UrlMappingService mUrlMappingService;
    @Autowired
    RealWebInfoRepository mRealWebInfoRepository;
    @Autowired
    WebInfoRepository mWebInfoRepository;
    @Autowired
    UserService mUserService;
    @Autowired
    private SecretInfoRepository mSecretInfoRepository;
    @Autowired
    MessageInfoRepository mMessageInfoRepository;
    @Autowired
    VersionInfoRepository mVersionInfoRepository;

    /**
     * 记录静态网页点击
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/rememberall", method = RequestMethod.GET)
    public String rememberAll(HttpServletRequest request) {
        String ip = CommonUtils.getIpAddr(request);
        String urlPath = request.getParameter("urlPath");
        String keyWord = request.getParameter("keyword");
        String eMatchtype = request.getParameter("e_matchtype");
        String eCreative = request.getParameter("e_creative");
        String eAdposition = request.getParameter("e_adposition");
        String ePagenum = request.getParameter("e_pagenum");
        String eKeywordid = request.getParameter("e_keywordid");
        String dy = request.getParameter("dy");
        String jh = request.getParameter("jh");
        String wechatId = request.getParameter("wechatid");
        String referer = request.getParameter("referer");
        String price = request.getParameter("price");
        String audience = request.getParameter("audience");
        String city = "北京";
        String provice = "";
        Map<String, Object> params = new HashMap<>();
        params.put("ip", ip);
        String result = HttpClientHelper.sendGet("http://ip.taobao.com/service/getIpInfo.php", params, "UTF-8");
        if (result != null) {
            LocationTaobao locationTaobao = JSONObject.parseObject(result, new TypeReference<LocationTaobao>() {
            });
            Location location = locationTaobao.data;
            city = location.city;
            provice = location.getProvince();
            WebInfo info = new WebInfo(urlPath, new Date().getTime(), CommonUtils.getIpAddr(request),
                    wechatId, keyWord, eKeywordid, referer, eMatchtype, eCreative, eAdposition, ePagenum, price, audience, dy, jh, provice, null);
            info.setCity(city);
            mUrlMappingService.savaWebInfo(info, urlPath, ip);
            String str = String.format("{\"wechatId\":\"%s\",\"city\":\"%s\",\"keyWord\":\"%s\",\"e_keywordid\":\"%s\",\"JS_ADD_HISTORY\":\"%s\",\"JS_ADD_BACK_LISTENER\":\"%s\",\"JS_ADD_COPY_LISTENER\":\"%s\"}"
                    , wechatId, city, keyWord, eKeywordid, AppConsts.JS_ADD_HISTORY, AppConsts.JS_ADD_BACK_LISTENER_NEW, AppConsts.JS_ADD_COPY_LISTENER);
            if (StringUtils.isEmptyOrWhitespace(eCreative) || StringUtils.isEmptyOrWhitespace(audience) || StringUtils.isEmptyOrWhitespace(referer) || location == null || StringUtils.isEmptyOrWhitespace(location.city) || location.toString().contains("北京") || location.toString().contains("上海")
                    || location.toString().contains("广州") || location.toString().contains("深圳") || location.toString().contains("东莞") || "广州".equals(city) || "深圳".equals(city) || "北京".equals(city) || "上海".equals(city) || "东莞".equals(city)) {
                if (location != null && (!location.toString().contains("广东")) && (!StringUtils.isEmptyOrWhitespace(eCreative))) {
                    if ("北京".equals(city) || "北京".equals(location.getProvince()) || "北京".equals(location.country) || location.toString().contains("北京") || location.toString().contains("上海") || StringUtils.isEmptyOrWhitespace(eCreative) || "{creative}".equals(eCreative)
                            || location.toString().contains("广州") || location.toString().contains("深圳") || location.toString().contains("东莞") || "广州".equals(city) || "深圳".equals(city) || "北京".equals(city) || "上海".equals(city) || "东莞".equals(city)) {
                        return "{code:1}";
                    } else {
                        return str;
                    }

                } else {
                    if (StringUtils.isEmptyOrWhitespace(eCreative) || "{creative}".equals(eCreative)) {
                        return "{code:1}";
                    }
                    return "{code:1}";
                }
            } else {
                return str;
            }

        }
        return "{code:1}";

    }


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
        if (webInfo != null) {
            WXInfo wxInfo = new WXInfo(webInfo.getWechatId(), webInfo.getIp(), webInfo.getUrlPath(), new Date().getTime(),
                    type, webInfo.getKeyWord(), webInfo.geteKeywordid(), null,
                    webInfo.geteMatchtype(), webInfo.geteCreative(), webInfo.geteAdposition(), webInfo.getePagenum(), webInfo.getPrice(), webInfo.getAudience(), webInfo.getDy(), webInfo.getJh(), webInfo.getProvice(), webInfo.getStrartUrl());
            wxInfo.setCity(webInfo.getCity());
            mUrlMappingService.savaWXInfo(wxInfo, webInfo.getUrlPath(), CommonUtils.getIpAddr(request));
            return String.format("{\"wechatId\":\"%s\",\"city\":\"%s\",\"keyWord\":\"%s\",\"e_keywordid\":\"%s\",\"JS_ADD_HISTORY\":\"%s\",\"JS_ADD_BACK_LISTENER\":\"%s\",\"JS_ADD_COPY_LISTENER\":\"%s\"}"
                    , webInfo.getWechatId(), webInfo.getCity(), webInfo.getKeyWord(), webInfo.geteKeywordid(), AppConsts.JS_ADD_HISTORY, AppConsts.JS_ADD_BACK_LISTENER_BAIDU, AppConsts.JS_ADD_COPY_LISTENER);
        } else {
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
        String keyWord = request.getParameter("keyword");
        WebInfo webInfo = mUrlMappingService.getWebInfoByIP(ip);
        String wechatId;
        String city = "北京";
        String provice = "";
        if (webInfo != null) {
            wechatId = mUrlMappingService.getRandomWechatId();
            //如果为空，就取推广的记录页面，否则随便取
            if (StringUtils.isEmptyOrWhitespace(wechatId)) {
                wechatId = webInfo.getWechatId();
            }
            city = webInfo.getCity();
            provice = webInfo.getProvice();
            if (StringUtils.isEmptyOrWhitespace(keyWord)) {
                keyWord = webInfo.getKeyWord() == null ? "丰胸" : webInfo.getKeyWord();
            }
            String e_keywordid = webInfo.geteKeywordid() == null ? "丰胸" : webInfo.geteKeywordid();
            HtmlInfo htmlInfo = new HtmlInfo(urlPath, new Date().getTime(), ip,
                    wechatId, keyWord, e_keywordid, city, provice, webInfo.getUrlPath(), webInfo.getStrartUrl());
            webInfo.setKeyWord(keyWord);
            if (!"fxc".equals(urlPath)) {
                mUrlMappingService.savaHtmlWebInfo(htmlInfo);
                mWebInfoRepository.save(webInfo);
            }
            String str = String.format("{\"wechatId\":\"%s\",\"city\":\"%s\",\"keyWord\":\"%s\",\"e_keywordid\":\"%s\",\"JS_ADD_HISTORY\":\"%s\",\"JS_ADD_BACK_LISTENER\":\"%s\",\"JS_ADD_COPY_LISTENER\":\"%s\"}"
                    , wechatId, city, keyWord, e_keywordid, AppConsts.JS_ADD_HISTORY, AppConsts.JS_ADD_BACK_LISTENER_SELF, AppConsts.JS_ADD_COPY_LISTENER);
            return str;
        }
        return "{code:1}";
    }


    /**
     * 记录静态网页点击
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/setweb", method = RequestMethod.GET)
    public String setRedirectWeb(HttpServletRequest request) {
        String ip = CommonUtils.getIpAddr(request);
        String urlPath = request.getParameter("urlPath");
        String keyWord = request.getParameter("keyword");
        WebInfo webInfo = mUrlMappingService.getWebInfoByIP(ip);
        String wechatId = mUrlMappingService.getRandomWechatIdByUrl(urlPath);
        String city = "北京";
        String provice = "";
        if (webInfo != null) {
            //如果为空，就取推广的记录页面，否则随便取
            if (StringUtils.isEmptyOrWhitespace(wechatId)) {
                wechatId = webInfo.getWechatId();
            }
            city = webInfo.getCity();
            provice = webInfo.getProvice();
            if (StringUtils.isEmptyOrWhitespace(keyWord)) {
                keyWord = webInfo.getKeyWord() == null ? "丰胸" : webInfo.getKeyWord();
            }
            String e_keywordid = webInfo.geteKeywordid() == null ? "丰胸" : webInfo.geteKeywordid();
            HtmlInfo htmlInfo = new HtmlInfo(urlPath, new Date().getTime(), ip,
                    wechatId, keyWord, e_keywordid, city, provice, webInfo.getUrlPath(), webInfo.getStrartUrl());
            if (!"fxc".equals(urlPath)) {
                mUrlMappingService.savaHtmlWebInfo(htmlInfo);
            }
            String backparams = String.format(AppConsts.JS_ADD_BACK_LISTENER, webInfo.toString());
            String str = String.format("{\"wechatId\":\"%s\",\"city\":\"%s\",\"keyWord\":\"%s\",\"e_keywordid\":\"%s\",\"JS_ADD_HISTORY\":\"%s\",\"JS_ADD_BACK_LISTENER\":\"%s\",\"JS_ADD_COPY_LISTENER\":\"%s\"}"
                    , wechatId, city, keyWord, e_keywordid, AppConsts.JS_ADD_HISTORY, backparams, AppConsts.JS_ADD_COPY_LISTENER);
            return str;
        }
        return String.format("{\"wechatId\":\"%s\",\"city\":\"%s\",\"keyWord\":\"丰胸\",\"e_keywordid\":\"丰胸\",\"JS_ADD_HISTORY\":\"%s\",\"JS_ADD_BACK_LISTENER\":\"%s\",\"JS_ADD_COPY_LISTENER\":\"%s\"}"
                , wechatId, city, "fengxiong", "fengxiong", "\"fengxiong\"");
    }


    /**
     * 提供推广微信
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/wechatid", method = RequestMethod.GET)
    public String wechatid(HttpServletRequest request) {
        return mUrlMappingService.getRandomWechatId();
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
            result = "{asum:" + mWXInfoRepository.countByKeyWordAndCreateTimeGreaterThanEqualAndCreateTimeLessThan(
                    secondClick.getKeyWord(), start, end) + "}";
        } else {
            result = "{asum:" + mWXInfoRepository.countByKeyWordAndWechatIdAndCreateTimeGreaterThanEqualAndCreateTimeLessThan(
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

    @RequestMapping(value = "/access", method = RequestMethod.GET)
    public String access(HttpServletRequest request) {
        RespInfo info = new RespInfo();
        String pushkey = request.getParameter("pushkey");
        String secret = request.getParameter("secret");
        String deviceid = request.getParameter("deviceid");
        String name = request.getParameter("name");
        if(mUserService.addOrUpdateUser(pushkey,secret,deviceid,name)){
            info.status = 200;
            info.message = "ok";
        }else{
            info.status = 500;
            info.message = "fail";
        }
        return JSON.toJSONString(info);
    }

    @RequestMapping(value = "/check", method = RequestMethod.GET)
    public String check(HttpServletRequest request) {
        RespInfo info = new RespInfo();
        String apppackage = request.getParameter("apppackage");
        List<VersionInfo> versionInfos = mVersionInfoRepository.findByAppPackage(apppackage);
        if(versionInfos != null && versionInfos.size() > 0) {
            info.data = versionInfos.get(versionInfos.size() - 1);
        }

        return JSON.toJSONString(info);
    }

    @RequestMapping(value = "/setcdk", method = RequestMethod.GET)
    public String setCDK(HttpServletRequest request) {
        int number = Integer.valueOf(request.getParameter("number"));
        long time = Long.valueOf(request.getParameter("time"));
        for(int i=0;i<number;i++) {
            String secret = SymmetricEncoder.AESEncode(AppConsts.ENCODER_KEY, new Date().getTime() + "53" + new Random().nextInt(5000));
            SecretInfo secretInfo = new SecretInfo();
            secretInfo.secret = secret;
            secretInfo.availTime = time;
            secretInfo.isUse = false;
            mSecretInfoRepository.save(secretInfo);
        }
        return "0";
    }


    @RequestMapping(value = "/getphone", method = RequestMethod.GET)
    public String getphone(HttpServletRequest request) {
        RespInfo info = new RespInfo();
        String username = request.getParameter("username");
        String deviceid = request.getParameter("deviceid");
        String data = mUserService.checkUserByDevice(deviceid);
        info.data = data;
        if(!"fail".equals(data)) {
            info.status = 200;
            info.message = "ok";
            info.data = mUserService.getContacts(username);
        }else{
            info.status = 500;
            info.message = "fail";
        }
        return JSON.toJSONString(info);
    }


    /**
     * 获取聊天记录
     * @param request
     * @return
     */
    @RequestMapping(value = "/addmessage", method = RequestMethod.POST)
    public String addmessage(HttpServletRequest request) {
        RespInfo info = new RespInfo();
        String username = request.getParameter("username");
        String deviceid = request.getParameter("deviceid");
        String sendpushkey = request.getParameter("sendpushkey");
        String receivepushkey = request.getParameter("receivepushkey");
        String sendnickname = request.getParameter("sendnickname");
        String messagecontent = request.getParameter("messagecontent");
        MessageInfo messageInfo = new MessageInfo();
        messageInfo.device = deviceid;
        messageInfo.username = username;
        messageInfo.isread = 0L;
        messageInfo.sendnickname = sendnickname;
        messageInfo.messagecontent = messagecontent;
        messageInfo.sendpushkey = sendpushkey;
        messageInfo.receivepushkey = receivepushkey;
        messageInfo.sendTime = new Date().getTime();
        mMessageInfoRepository.save(messageInfo);
        String data = mUserService.checkUserByDevice(deviceid);
        info.data = data;
        if(!"fail".equals(data)) {
            info.status = 200;
            info.message = "ok";
        }else{
            info.status = 500;
            info.message = "fail";
        }
        return JSON.toJSONString(info);
    }

}
