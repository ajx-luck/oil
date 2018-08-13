package com.breast.oil.repository;

import com.breast.oil.domain.PYQInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PYQInfoRepository extends JpaRepository<PYQInfo,Long> {
    List<PYQInfo> findByUsername(@Param("username") String username);
}
