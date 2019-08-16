package com.breast.oil.repository;



import com.breast.oil.domain.WubaUid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WubaUidRepository extends JpaRepository<WubaUid,Long> {

        List<WubaUid> findByUid(@Param("uid") String uid);

        List<WubaUid> findByUsetime(@Param("usetime") Integer usetime);

        List<WubaUid> findByUpdatetimesLessThan(@Param("updatetimes") Long updatetimes);

        @Query(nativeQuery=true, value = "select * from wuba_uid where usetime = ?1 order by id desc limit 290")
        List<WubaUid> findByUsetimeLimite(@Param("usetime") Integer usetime);
}
