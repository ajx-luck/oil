package com.breast.oil.repository;

import com.breast.oil.domain.DouyinAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DouyinAccountRepository extends JpaRepository<DouyinAccount,Long> {

    List<DouyinAccount> findByPhone(@Param("phone") String phone);

    List<DouyinAccount> findByUpdatetimesLessThan(@Param("updatetimes") Long updatetimes);

    List<DouyinAccount> findByFollowtimesLessThan(@Param("followtimes") Long followtimes);

    List<DouyinAccount> findByIos(@Param("ios") String ios);

    List<DouyinAccount> findByFilenameAndUpdatetimesLessThan(@Param("filename") String filename,@Param("updatetimes") Long updatetimes);

    List<DouyinAccount> findByFilenameAndFollowtimesLessThan(@Param("filename") String filename,@Param("followtimes") Long followtimes);

}
