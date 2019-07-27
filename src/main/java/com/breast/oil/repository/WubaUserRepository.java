package com.breast.oil.repository;



import com.breast.oil.domain.WubaUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WubaUserRepository extends JpaRepository<WubaUser,Long> {
        List<WubaUser> findByUid(@Param("uid") String uid);

        List<WubaUser> findByUpdatetimesLessThan(@Param("updatetimes") Long updatetimes);
}
