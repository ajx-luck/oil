package com.breast.oil.repository;

import com.breast.oil.domain.StatisticsInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by B04e on 2017/11/28.
 */
public interface StatisticsInfoRepository extends JpaRepository<StatisticsInfo, Long> {
    //    @Query("select '*' from StatisticsInfo where url=:url and createTime >=:startTime and createTime <:endTime")
    List<StatisticsInfo> findByUrlAndCreateTimeGreaterThanEqualAndCreateTimeLessThan(@Param("url") String url,
                                                                                     @Param("startTime") Long startTime,
                                                                                     @Param("endTime") Long endTime);

    //    @Query("select '*' from StatisticsInfo where createTime >=:startTime and createTime <:endTime")
    List<StatisticsInfo> findByCreateTimeGreaterThanEqualAndCreateTimeLessThan(@Param("startTime") Long startTime,
                                                                               @Param("endTime") Long endTime);
}
