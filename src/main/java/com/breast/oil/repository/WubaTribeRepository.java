package com.breast.oil.repository;


import com.breast.oil.domain.WubaTribe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WubaTribeRepository extends JpaRepository<WubaTribe,Long> {
        List<WubaTribe> findByAid(@Param("aid") String aid);
}
