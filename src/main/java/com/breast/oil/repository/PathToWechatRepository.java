package com.breast.oil.repository;

import com.breast.oil.domain.PathToWechat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by B04e on 2017/11/28.
 */
public interface PathToWechatRepository extends JpaRepository<PathToWechat, Long> {
    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("update PathToWechat set wechatId=:wechatId where urlPath=:urlPath")
    void updateWechatByUrlPath(@Param("urlPath") String urlPath, @Param("wechatId") String wechatId);

    @Query("select '*' from PathToWechat where urlPath=:urlPath")
    List<PathToWechat> findByUrl(@Param("urlPath") String urlPath);

    @Query("select '*' from PathToWechat where wechatId=:wechatId")
    List<PathToWechat> findByWechatId(@Param("wechatId") String wechatId);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("delete from PathToWechat where wechatId=:wechatId")
    void deleteByWechatId(@Param("wechatId") String wechatId);
}
