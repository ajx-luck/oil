package com.breast.oil.repository;

import com.breast.oil.domain.WebInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by B04e on 2017/11/27.
 */
public interface WebInfoRepository extends JpaRepository<WebInfo,Long>{

    @Query("select count(*) from  WebInfo where keyWord=:keyWord and createTime>=:startTime and createTime <:endTime")
    Long countByKeyWordAndCreateTimeGreaterThanEqualAndCreateTimeLessThan(@Param("keyWord") String keyWord,
                                                                          @Param("startTime") Long startTime,
                                                                          @Param("endTime") Long endTime);

    @Query("select count(*) from  WebInfo where keyWord=:keyWord and wechatId=:wechatId and createTime>=:startTime and createTime <:endTime")
    Long countByKeyWordAndWechatIdAndCreateTimeGreaterThanEqualAndCreateTimeLessThan(@Param("keyWord") String keyWord,
                                                                                     @Param("wechatId") String wechatId,
                                                                                     @Param("startTime") Long startTime,
                                                                                     @Param("endTime") Long endTime);

    @Query("select count(*) from  WebInfo where keyWord=:keyWord and urlPath=:urlPath and createTime>=:startTime and createTime <:endTime")
    Long countByKeyWordAndUrlPathAndCreateTimeGreaterThanEqualAndCreateTimeLessThan(@Param("keyWord") String keyWord,
                                                                                     @Param("urlPath") String urlPath,
                                                                                     @Param("startTime") Long startTime,
                                                                                     @Param("endTime") Long endTime);

    @Query("select count(*) from  WebInfo where urlPath=:urlPath and createTime>=:startTime and createTime <:endTime")
    Long countByUrlPathAndCreateTimeGreaterThanEqualAndCreateTimeLessThan(
                                                                                    @Param("urlPath") String urlPath,
                                                                                    @Param("startTime") Long startTime,
                                                                                    @Param("endTime") Long endTime);

}
