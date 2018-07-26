package com.breast.oil.repository;

import com.breast.oil.domain.KeyWord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by B04e on 2017/12/19.
 */
public interface KeyWordRepository extends JpaRepository<KeyWord, Long> {
    List<KeyWord> findByKeyWord(@Param("keyWord") String keyWord);
}
