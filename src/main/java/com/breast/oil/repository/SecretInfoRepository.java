package com.breast.oil.repository;


import com.breast.oil.domain.SecretInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SecretInfoRepository extends JpaRepository<SecretInfo,Long> {
    List<SecretInfo> findBySecret(@Param("secret") String secret);
}
