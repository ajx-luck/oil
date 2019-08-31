package com.breast.oil.repository;



import com.breast.oil.domain.MusicUid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MusicUidRepository extends JpaRepository<MusicUid,Long> {

        List<MusicUid> findByUid(@Param("uid") String uid);

        List<MusicUid> findByUsetime(@Param("usetime") Integer usetime);

        List<MusicUid> findByUpdatetimesLessThan(@Param("updatetimes") Long updatetimes);

        @Query(nativeQuery=true, value = "select * from music_uid where usetime < ?1 and gender = ?2 order by id asc limit ?3")
        List<MusicUid> findByUsetimeLimite(@Param("usetime") Integer usetime,@Param("gender") Integer gender,@Param("maxlength")Integer maxlength);
}
