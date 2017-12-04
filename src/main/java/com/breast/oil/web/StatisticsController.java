package com.breast.oil.web;


import com.breast.oil.domain.SecondClick;
import com.breast.oil.domain.WXInfo;
import com.breast.oil.repository.WXInfoRepository;
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

    @RequestMapping(value = "/remember",method = RequestMethod.GET)
    public String remember(HttpServletRequest request){
        String wechatId = request.getParameter("wechatId");
        String urlPath = request.getParameter("urlPath");
        String keyWord = request.getParameter("keyWord");
        WXInfo wxInfo = new WXInfo(wechatId,request.getRemoteAddr(),urlPath,keyWord == null ? "def":keyWord,new Date().getTime());
        mWXInfoRepository.save(wxInfo);
        return "{code:0}";
    }

    @RequestMapping(value = "/keyword",method = RequestMethod.POST)
    public String keyword(SecondClick secondClick,ModelMap map){
        long start = TimeUtils.DateTimeParse(secondClick.getStart() + " "+secondClick.getStartTime());
        long end = TimeUtils.DateTimeParse(secondClick.getEnd() + " "+secondClick.getEndTime());
        return "{count:"+mWXInfoRepository.countByKeyWordAndCreateTimeGreaterThanEqualAndCreateTimeLessThan(
                secondClick.getKeyWord(),start,end)+"}";
    }

}
