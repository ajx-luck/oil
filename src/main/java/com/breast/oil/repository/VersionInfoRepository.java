package com.breast.oil.repository;

import com.breast.oil.domain.VersionInfo;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by B04e on 2018/3/22.
 */
public interface VersionInfoRepository extends JpaRepository<VersionInfo, Long> {
    List<VersionInfo> findByAppPackage(@Param("appPackage") String appPackage);

}
