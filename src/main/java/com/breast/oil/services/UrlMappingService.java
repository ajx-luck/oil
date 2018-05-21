package com.breast.oil.services;

import com.breast.oil.domain.*;
import com.breast.oil.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by B04e on 2017/11/28.
 */
@Service
public class UrlMappingService {
    @Autowired
    PathToWechatRepository mPathToWechatRepository;
    @Autowired
    WebInfoRepository mWebInfoRepository;
    @Autowired
    WXInfoRepository mWXInfoRepository;
    @Autowired
    KeyWordRepository mKeyWordRepository;
    @Autowired
    StatisticsInfoRepository mStatisticsInfoRepository;
    @Autowired
    HtmlInfoRepository mHtmlInfoRepository;
    @Autowired
    RealWebInfoRepository mRealWebInfoRepository;
    public List<PathToWechat> mPathToWechats;


    public List<PathToWechat> getAllPathToWechat() {
        if (mPathToWechats == null) {
            mPathToWechats = mPathToWechatRepository.findAll();
        }
        return mPathToWechats;
    }

    /**
     * 随机获取一个微信号码
     * @return
     */
    public String getRandomWechatId(){
        List<PathToWechat> list = getAllPathToWechat();
        int i = new Random().nextInt(list.size());
        return list.get(i).getWechatId();
    }

    public String getWechatIdByIP(String ip){
        List<WebInfo> list = mWebInfoRepository.findByIpOrderByIdDesc(ip);
        if(list == null || list.size() == 0 || StringUtils.isEmptyOrWhitespace(list.get(0).getWechatId())){
            return getRandomWechatId();
        }else{
            return list.get(0).getWechatId();
        }
    }

    public List<PathToWechat> updateAllPathToWechat() {
        mPathToWechats = mPathToWechatRepository.findAll();
        return mPathToWechats;
    }

    public String getWechatIdByUrl(String url) {
        if (getAllPathToWechat() == null) {
            return null;
        }
        for (PathToWechat pathToWechat : getAllPathToWechat()) {
            if (pathToWechat.getUrlPath().equals(url)) {
                return pathToWechat.getWechatId();
            }
        }
        return null;
    }

    /**
     * 根据url获取随机微信号
     *
     * @param url
     * @return
     */

    public String getRandomWechatIdByUrl(String url) {
        if (getAllPathToWechat() == null) {
            return getRandomWechatId();
        }
        List<PathToWechat> list = new ArrayList();
        for (PathToWechat pathToWechat : getAllPathToWechat()) {
            if (pathToWechat.getUrlPath().equals(url)) {
                list.add(pathToWechat);
            }
        }
        if (list.size() == 0) {
            return getRandomWechatId();
        } else {
            return list.get(new Random().nextInt(list.size())).getWechatId();
        }
    }

    public WebInfo getWebInfoByIP(String ip){
        List<WebInfo> list = mWebInfoRepository.findByIpOrderByIdDesc(ip);
        if(list == null || list.size() == 0){
            return null;
        }else{
            return list.get(0);
        }
    }

    @Cacheable(value = "getwechat", key = "T(String).valueOf(#url).concat('-').concat(#ip)")
    public String getRandomWechatIdByUrl(String url, String ip) {
        cacheIp(url, ip);
        return getRandomWechatIdByUrl(url);
    }

    @CachePut(value = "getwechat", key = "T(String).valueOf(#url).concat('-').concat(#ip)")
    public void cacheIp(String url, String ip) {

    }



    /**
     * 根据url获取第一个微信号
     *
     * @param wechatId
     * @return
     */
    public String getUrlByWechatId(String wechatId) {
        if (getAllPathToWechat() == null) {
            return null;
        }
        for (PathToWechat pathToWechat : getAllPathToWechat()) {
            if (pathToWechat.getWechatId().equals(wechatId)) {
                return pathToWechat.getUrlPath();
            }
        }
        return null;
    }

    /**
     * 根据url获取价格
     *
     * @param url
     * @return
     */
    public Long getPriceByUrl(String url) {
        if (getAllPathToWechat() == null) {
            return null;
        }
        for (PathToWechat pathToWechat : getAllPathToWechat()) {
            if (pathToWechat.getUrlPath().equals(url)) {
                return pathToWechat.getPrice();
            }
        }
        return null;
    }

    /**
     * 更新价格根据url和微信
     *
     * @param url
     * @param wechatId
     * @param price
     * @return
     */
    public PathToWechat updatePriceAndWechatIdByUrl(String url, String wechatId, Long price) {
        if (getAllPathToWechat() == null) {
            return null;
        }
        for (PathToWechat pathToWechat : getAllPathToWechat()) {
            if (pathToWechat.getUrlPath().equals(url)) {
                pathToWechat.setPrice(price);
                pathToWechat.setWechatId(wechatId);
                mPathToWechatRepository.save(pathToWechat);
                updateAllPathToWechat();
                return pathToWechat;
            }
        }
        return null;
    }

    /**
     * 根据id查询url
     *
     * @param id
     * @return
     */
    public String getUrlById(Long id) {
        if (getAllPathToWechat() == null) {
            return null;
        }
        for (PathToWechat pathToWechat : getAllPathToWechat()) {
            if (pathToWechat.getId().equals(id)) {
                return pathToWechat.getUrlPath();
            }
        }
        return null;
    }

    /**
     * 新增匹配的url
     *
     * @param url
     * @param wechatId
     * @param price
     * @return
     */
    public PathToWechat addUrlAndWechat(String url, String wechatId, Long price) {
        PathToWechat pathToWechat = new PathToWechat();
        pathToWechat.setWechatId(wechatId);
        pathToWechat.setUrlPath(url);
        pathToWechat.setPrice(price);
        pathToWechat.setCreateTime(new Date().getTime());
        mPathToWechatRepository.save(pathToWechat);
        return pathToWechat;
    }

    /**
     * 保存页面访问（根据ip过滤）
     *
     * @param info
     * @param ip
     */
    public void savaWebInfo(WebInfo info,String url, String ip) {
        cacheWeb(url,ip);
        //真实有效点击
        if(info.getePagenum() != null && (!info.getePagenum().equals("{pagenum}")) && info.getPrice() != null && (!StringUtils.isEmptyOrWhitespace(info.getKeyWord()))){
            addKeyWordAndWebClick(info.getKeyWord(),info.geteKeywordid());
            RealWebInfo realWebInfo = new RealWebInfo(info.getUrlPath(),info.getCreateTime(),info.getIp(),info.getWechatId(),info.getKeyWord(),info.geteKeywordid(),null,info.geteMatchtype(),info.geteCreative(),info.geteAdposition(),
                    info.getePagenum(),info.getPrice(),info.getAudience(),info.getDy(),info.getJh());
            realWebInfo.setCity(info.getCity());
            mRealWebInfoRepository.save(realWebInfo);
        }
        mWebInfoRepository.save(info);
    }

//    @CachePut(value = "saveweb#5#2", key = "T(String).valueOf(#url).concat('-').concat(#ip)")
    public void cacheWeb(String url,String ip) {

    }

    /**
     * 保存点击微信次数（根据ip过滤）
     *
     * @param info
     * @param ip
     */
    @Cacheable(value = "savewx", key = "T(String).valueOf(#url).concat('-').concat(#ip)")
    public void savaWXInfo(WXInfo info, String url,String ip) {
        cacheWx(url,ip);
        if(!StringUtils.isEmptyOrWhitespace(info.getKeyWord())){
            addKeyWordAndWxClick(info.getKeyWord(),info.geteKeywordid());
        }
        mWXInfoRepository.save(info);
    }

    @CachePut(value = "savewx", key = "T(String).valueOf(#url).concat('-').concat(#ip)")
    public void cacheWx(String url,String ip) {

    }

    public void savaHtmlWebInfo(HtmlInfo info) {
        mHtmlInfoRepository.save(info);
    }

    /**
     * 删除微信号
     *
     * @param wechatId
     */
    public void deleteByWechatId(String wechatId) {
        mPathToWechatRepository.deleteByWechatId(wechatId);
    }

    /**
     * 新增关键字
     *
     * @param keyWord
     */
    public void addKeyWord(KeyWord keyWord) {
        mKeyWordRepository.save(keyWord);
    }

    /**
     * 关键词统计+1
     * @param keyword
     * @param keyWordDesc
     */
    public void addKeyWordAndWebClick(String keyword,String keyWordDesc){
        if(keyword == null || keyWordDesc == null){
            return;
        }
        List<KeyWord> list = mKeyWordRepository.findByKeyWord(keyword);
        KeyWord kw;
        if(list!=null && list.size() > 0){
            kw = list.get(0);
            kw.setWeb(kw.getWeb()+1);
        }else{
            kw = new KeyWord(keyword,keyWordDesc,new Date().getTime());
        }
        if(!keyWordDesc.equals("{keywordid}")) {
            mKeyWordRepository.save(kw);
        }
    }

    /**
     * 关键词统计+1
     * @param keyword
     * @param keyWordDesc
     */
    public void addKeyWordAndWxClick(String keyword,String keyWordDesc){
        if(keyword == null || keyWordDesc == null){
            return;
        }
        List<KeyWord> list = mKeyWordRepository.findByKeyWord(keyword);
        KeyWord kw;
        if(list!=null && list.size() > 0){
            kw = list.get(0);
            kw.setWxClick(kw.getWxClick()+1);
        }else{
            kw = new KeyWord(keyword,keyWordDesc,new Date().getTime());
        }
        if(!keyWordDesc.equals("{keywordid}")) {
            mKeyWordRepository.save(kw);
        }
    }

    /**
     * 获取统计数据
     *
     * @param start
     * @param end
     * @return
     */
    public List<WebAndWXCount> countAll(long start, long end) {
        List<WebAndWXCount> list = new ArrayList<>();
        for (KeyWord keyWord : mKeyWordRepository.findAll()) {
            String kw = keyWord.getKeyWord();
            String kwd = keyWord.getE_keywordid();
            long webCount = mWebInfoRepository.countByKeyWordAndCreateTimeGreaterThanEqualAndCreateTimeLessThan(kw, start, end);
            long wxCount = mWXInfoRepository.countByKeyWordAndCreateTimeGreaterThanEqualAndCreateTimeLessThan(kw, start, end);
            WebAndWXCount webAndWXCount = new WebAndWXCount(kw, kwd, wxCount, webCount);
            list.add(webAndWXCount);
        }
        return list;
    }

    /**
     * 获取统计数据
     *
     * @param start
     * @param end
     * @return
     */
    public List<WebAndWXCount> countAllByWechatId(String wechatId, long start, long end) {
        List<WebAndWXCount> list = new ArrayList<>();
        for (KeyWord keyWord : mKeyWordRepository.findAll()) {
            String kw = keyWord.getKeyWord();
            String kwd = keyWord.getE_keywordid();
            long webCount = mWebInfoRepository.countByKeyWordAndWechatIdAndCreateTimeGreaterThanEqualAndCreateTimeLessThan(kw, wechatId, start, end);
            long wxCount = mWXInfoRepository.countByKeyWordAndWechatIdAndCreateTimeGreaterThanEqualAndCreateTimeLessThan(kw, wechatId, start, end);
            WebAndWXCount webAndWXCount = new WebAndWXCount(kw, kwd, wxCount, webCount);
            webAndWXCount.setWechatId(wechatId);
            list.add(webAndWXCount);
        }
        return list;
    }

    /**
     * 获取统计数据
     *
     * @param start
     * @param end
     * @return
     */
    public List<WebAndWXCount> countAllByUrl(String urlPath, long start, long end) {
        List<WebAndWXCount> list = new ArrayList<>();
        for (KeyWord keyWord : mKeyWordRepository.findAll()) {
            String kw = keyWord.getKeyWord();
            String kwd = keyWord.getE_keywordid();
            long webCount = mWebInfoRepository.countByKeyWordAndUrlPathAndCreateTimeGreaterThanEqualAndCreateTimeLessThan(kw, urlPath, start, end);
            long wxCount = mWXInfoRepository.countByKeyWordAndUrlPathAndCreateTimeGreaterThanEqualAndCreateTimeLessThan(kw, urlPath, start, end);
            WebAndWXCount webAndWXCount = new WebAndWXCount(kw, kwd, wxCount, webCount);
            webAndWXCount.setWechatId(urlPath);
            list.add(webAndWXCount);
        }
        return list;
    }

    /**
     * 统计手动添加的数据
     *
     * @param url
     * @param start
     * @param end
     * @return
     */
    public List<StatisticsInfo> countCost(String url, long start, long end) {
        List<StatisticsInfo> list;
        if (StringUtils.isEmptyOrWhitespace(url)) {
            list = mStatisticsInfoRepository.findByCreateTimeGreaterThanEqualAndCreateTimeLessThan(start, end);
        } else {
            list = mStatisticsInfoRepository.findByUrlAndCreateTimeGreaterThanEqualAndCreateTimeLessThan(url, start, end);
        }
        return list;
    }

    public String getLastWechatIdByIp(String ip) {
        if (StringUtils.isEmptyOrWhitespace(ip)) {
            return getRandomWechatIdByUrl("fxc");
        } else {
            List<WebInfo> list = mWebInfoRepository.findByIpOrderByIdDesc(ip);
            String wechatId = "";
            if (list != null && list.size() > 0) {
                wechatId = list.get(list.size() - 1).getWechatId();
            }
            return StringUtils.isEmptyOrWhitespace(wechatId)?getRandomWechatIdByUrl("fxc"):wechatId;

        }
    }
}
