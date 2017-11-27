package com.breast.oil.web;

import com.breast.oil.consts.AppConsts;
import com.breast.oil.domain.WebInfo;
import com.breast.oil.repository.WebInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
public class WebController {

    @Autowired
    WebInfoRepository mWebInfoRepository;

    @RequestMapping("/show")
    public String show(ModelMap map){
        return "show";
    }

    @RequestMapping("/fx2")
    public String fx2(ModelMap map){
        map.addAttribute("wechat_id","fx96756");
        return "fx2";
    }
    @RequestMapping("/fx3")
    public String fx3(ModelMap map){
        map.addAttribute("wechat_id","cph131466");
        return "fx2";
    }
    @RequestMapping("/fx1")
    public String fx1(ModelMap map, HttpServletRequest request){
        map.addAttribute("wechat_id","fx96756");
        WebInfo info = new WebInfo();
        info.setUrlPath("fx1");
        info.setIp(request.getRemoteAddr());
        info.setCreateTime(new Date().getTime());
        info.setPrice(AppConsts.price);
        mWebInfoRepository.save(info);
        return "show";
    }
}
