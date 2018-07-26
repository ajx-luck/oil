package com.breast.oil.services;

import com.breast.oil.domain.WXInfo;
import com.breast.oil.po.Custom;
import com.breast.oil.repository.WXInfoRepository;
import com.breast.oil.repository.WebInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by B04e on 2018/1/3.
 */
@Service
public class KeyWordService {
    @Autowired
    WebInfoRepository mWebInfoRepository;
    @Autowired
    WXInfoRepository mWXInfoRepository;

    public List<Custom> getAllWeiXinData(long start, long end) {
        List<WXInfo> wxInfos = mWXInfoRepository.findByCreateTimeGreaterThanEqualAndCreateTimeLessThan(start, end);
        Map<String, Integer> map = new HashMap<>();
        Map<String, String> keywords = new HashMap<>();
        List<Custom> customs = new ArrayList<>();
        for (WXInfo info : wxInfos) {
            Integer value = map.get(info.geteKeywordid());
            if (value == null) {
                map.put(info.geteKeywordid(), 1);
                keywords.put(info.geteKeywordid(), info.getKeyWord());
            } else {
                map.put(info.geteKeywordid(), ++value);
            }
        }
        for (Map.Entry<String, Integer> entry : map.entrySet()) {

            String keywordid = entry.getKey();
            String keyword = keywords.get(keywordid);
            int weixin = entry.getValue();
            long web = mWebInfoRepository.countByEKeywordidAndCreateTimeGreaterThanEqualAndCreateTimeLessThan(keywordid, start, end);
            Custom custom = new Custom(keywordid, weixin, web, keyword);
            customs.add(custom);
        }
        return customs;

    }
}
