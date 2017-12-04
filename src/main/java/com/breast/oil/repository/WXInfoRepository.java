package com.breast.oil.repository;

import com.breast.oil.domain.WXInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by B04e on 2017/12/4.
 */
public interface WXInfoRepository extends JpaRepository<WXInfo,Long>{

    @Query("select count(1) from  WXInfo n where n.keyWord=keyWord and n.createTime>=startTime and n.createTime < endTime")
    Long countByKeyWordAndCreateTimeGreaterThanEqualAndCreateTimeLessThan(@Param("keyWord") String keyWord,
                                                     @Param("startTime")Long startTime,
                                                     @Param("endTime")Long endTime);
}
