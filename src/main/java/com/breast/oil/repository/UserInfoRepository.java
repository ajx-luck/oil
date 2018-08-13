package com.breast.oil.repository;

import com.breast.oil.domain.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserInfoRepository extends JpaRepository<UserInfo,Long> {
    List<UserInfo> findByDevice(@Param("device") String device);

    List<UserInfo> findByTonken(@Param("tonken") String tonken);

    List<UserInfo> findByUsername(@Param("username") String username);
}
