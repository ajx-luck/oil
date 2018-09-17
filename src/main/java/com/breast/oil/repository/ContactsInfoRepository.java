package com.breast.oil.repository;

import com.breast.oil.domain.ContactsInfo;
import com.breast.oil.domain.HtmlInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by B04e on 2018/3/22.
 */
public interface ContactsInfoRepository extends JpaRepository<ContactsInfo, Long> {

    Page<ContactsInfo> findByUsernameAndIsread(@Param("username") String username,@Param("isread") Long isread,Pageable pageable);
}
