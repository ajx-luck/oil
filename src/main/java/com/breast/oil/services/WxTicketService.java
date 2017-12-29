package com.breast.oil.services;

import com.breast.oil.utils.HttpClientHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;


/**
 * Created by B04e on 2017/12/22.
 */
@Service
public class WxTicketService {
    public static String ticket = "weixin://";
    public final static long ONE_Minute =  300 * 1000;

   /* @Scheduled(fixedDelay=ONE_Minute)
    public String getTicket(){
        try {
            String str = HttpClientHelper.sendGet("http://jump.hupeh.cn/sxm1223.php",null,"utf-8");
            int index = str.indexOf("ticket=");
            int end = str.indexOf("#wechat_redirect");
            ticket = str.substring(index,end);
        }catch (Exception e){
            e.printStackTrace();
        }
        return ticket;
    }*/


    /**
     * 获取access_token，然后jsapi_ticket
     */
    /*private String getAccessToken_ticket(String path) {
        String access_token = null; // access_token
        String atime = null;// 获取时间
        String a_expires_in = null;// 有效时间(s)
        String ticket = null;// jsapi_ticket
        String ttime = null;// 得到时间
        String t_expires_in = null;// 有效时间(s)
        String access_tokenStr = TUtils.getAccessToken(APPID,
                API_KEY);
        if (access_tokenStr != null
                && access_tokenStr.indexOf("access_token") != -1) {
            try {
                JSONObject jsonObject = new JSONObject(access_tokenStr);
                access_token = jsonObject.getString("access_token");
                a_expires_in = jsonObject.getString("expires_in");
                atime = getCurrentDateStr();
            } catch (JSONException e) {
                // e.printStackTrace();
            }
        }
        if (access_token != null && !access_token.equals("")) {
            String ticketStr = TicketUtils.getJSAPITicket(access_token);
            // System.out.println("ticketStr:" + ticketStr);
            if (ticketStr != null && ticketStr.indexOf("ticket") != -1) {
                try {
                    JSONObject jsonObject = new JSONObject(ticketStr);
                    ticket = jsonObject.getString("ticket");
                    t_expires_in = jsonObject.getString("expires_in");
                    ttime = getCurrentDateStr();
                } catch (JSONException e) {
                    // e.printStackTrace();
                }
            }
        }
        String result = null;
        if (ticket != null && !ticket.equals("")) {
            result = "{\"access_token\":\"" + access_token
                    + "\",\"a_expires_in\":\"" + a_expires_in
                    + "\",\"atime\":\"" + atime + "\",\"ticket\":\"" + ticket
                    + "\",\"t_expires_in\":\"" + t_expires_in
                    + "\",\"ttime\":\"" + ttime + "\"}";
            if (MyFileUtils.writeIntoText(path, result)) {
                // System.out.println("写入文件成功");
                // System.out.println(result);
            } else {
                System.out.println("写入微信签名文件失败");
            }
        }
        return result;
    }
    public static String getAccessToken(String APPID, String APPSECRET) {

        String url = "https://api.weixin.qq.com/cgi-bin/token";
        String params = "grant_type=client_credential&appid=" + APPID
                + "&secret=" + APPSECRET;
        String resultStr = HttpRequest.sendGet(url, params);
// sendGet：用get方法获取数据 ,具体请参考之间的关于微信的文章 http://www.cnblogs.com/jiduoduo/p/5749363.html

        return resultStr;

    }


    *//**
     * 根据access_token获取ticket { "errcode":0, "errmsg":"ok", "ticket":
     * "bxLdikRXVbTPdHSM05e5u5sUoXNKd8-41ZO3MhKoyN5OfkWITDGgnr2fwJ0m9E8NYzWKVZvdVtaUgWvsdshFKA"
     * , "expires_in":7200 }
     *
     * @param access_token
     * @return
     *//*
    public static String getJSAPITicket(String access_token) {
        String url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket";
        String params = "type=jsapi&access_token=" + access_token;
        String resultStr = HttpRequest.sendGet(url, params);

        return resultStr;
    }

//2.2具体生成签名signature

    public String Wx_Signature() {
        String path = ServletActionContext.getServletContext().getRealPath(
                "/wx/");
        // System.out.println(path);
        try {
            String tokenJSON = MyFileUtils.readText(path);
            // String access_token = null; // access_token
            String atime = null;// 获取时间
            String a_expires_in = null;// 有效时间(s)
            String ticket = null;// jsapi_ticket
            // String ttime = null;// 得到时间
            // String t_expires_in = null;// 有效时间(s)
            String result = tokenJSON;
            if (result == null || result.equals("")) {
                tokenJSON = getAccessToken_ticket(path);
            }
            // System.out.println(result);
            if (tokenJSON != null && !tokenJSON.equals("")
                    && tokenJSON.indexOf("access_token") != -1) {
                try {
                    JSONObject jsonObject = new JSONObject(tokenJSON);
                    // access_token = jsonObject.getString("access_token");//
                    // access_token
                    atime = jsonObject.getString("atime");// 开始时间
                    a_expires_in = jsonObject.getString("a_expires_in");// 有效时间
                    ticket = jsonObject.getString("ticket");// jsapi_ticket
                    // System.out.println(ticket);
                    // ttime = jsonObject.getString("ttime");// 开始时间
                    // t_expires_in = jsonObject.getString("t_expires_in");//
                    // 有效时间
                    String t1 = getCurrentDateStr();
                    String t2 = atime;
                    // System.out.println(atime);
                    // System.out.println(a_expires_in);
                    // System.out.println(TimeInterval.getInterval(t2, t1));
                    long end_time = Long.parseLong(a_expires_in) - 60;
                    if (TimeInterval.getInterval(t2, t1) > end_time) {
                        ticket = getAccessToken_ticket(path);
                    }
                } catch (JSONException e) {
                    msg = e.getMessage();
                }
            } else {

            }
            // System.out.println(ticket);
            String url = getParameter("url");
            String noncestr = TUtils.getRandomString(16);
            String timestamp = System.currentTimeMillis() + "";
            timestamp = timestamp.substring(0, 10);
            String data = "jsapi_ticket=" + ticket + "&noncestr=" + noncestr
                    + "×tamp=" + timestamp + "&url=" + url;
            String digest = new SHA1().getDigestOfString(data.getBytes());
            String signature = digest.toLowerCase();// signature
            result = "{\"noncestr\":\"" + noncestr + "\",\"timestamp\":\""
                    + timestamp + "\",\"url\":\"" + url + "\",\"signature\":\""
                    + signature + "\" ,\"ticket\":\"" + ticket + "\"}";
            msg = result;
        } catch (IOException e) {
            msg = e.getMessage();
        }
        return msg
    }*/

}
