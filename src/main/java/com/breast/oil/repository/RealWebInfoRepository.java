package com.breast.oil.repository;

import com.breast.oil.domain.RealWebInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by B04e on 2017/11/27.
 */
public interface RealWebInfoRepository extends JpaRepository<RealWebInfo, Long> {

    @Query("select count(*) from  RealWebInfo where keyWord=:keyWord and createTime>=:startTime and createTime <:endTime")
    Long countByKeyWordAndCreateTimeGreaterThanEqualAndCreateTimeLessThan(@Param("keyWord") String keyWord,
                                                                          @Param("startTime") Long startTime,
                                                                          @Param("endTime") Long endTime);

    @Query("select count(*) from  RealWebInfo where keyWord=:keyWord and wechatId=:wechatId and createTime>=:startTime and createTime <:endTime")
    Long countByKeyWordAndWechatIdAndCreateTimeGreaterThanEqualAndCreateTimeLessThan(@Param("keyWord") String keyWord,
                                                                                     @Param("wechatId") String wechatId,
                                                                                     @Param("startTime") Long startTime,
                                                                                     @Param("endTime") Long endTime);

    @Query("select count(*) from  RealWebInfo where keyWord=:keyWord and urlPath=:urlPath and createTime>=:startTime and createTime <:endTime")
    Long countByKeyWordAndUrlPathAndCreateTimeGreaterThanEqualAndCreateTimeLessThan(@Param("keyWord") String keyWord,
                                                                                    @Param("urlPath") String urlPath,
                                                                                    @Param("startTime") Long startTime,
                                                                                    @Param("endTime") Long endTime);

    @Query("select count(*) from  RealWebInfo where urlPath=:urlPath and createTime>=:startTime and createTime <:endTime")
    Long countByUrlPathAndCreateTimeGreaterThanEqualAndCreateTimeLessThan(
            @Param("urlPath") String urlPath,
            @Param("startTime") Long startTime,
            @Param("endTime") Long endTime);

/*    @Query("select '*' from WebInfo where ip=:ip order by id desc")
    List<WebInfo> findByIpOrderById(@Param("ip") String ip);*/

    List<RealWebInfo> findByIpOrderByIdDesc(@Param("ip") String ip);

    Long countByEKeywordidAndCreateTimeGreaterThanEqualAndCreateTimeLessThan(@Param("eKeywordid") String eKeywordid,
                                                                             @Param("startTime") Long startTime,
                                                                             @Param("endTime") Long endTime);
}
