package com.breast.oil.repository.impl;

import com.breast.oil.repository.BaseNativeSqlRepository;
import com.breast.oil.repository.StatisticsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by B04e on 2017/11/28.
 */
@Service
public class StatisticsRepositoryImpl extends BaseNativeSqlRepository implements StatisticsRepository {
    @Override
    public Long totalMoney(String wechatId, long start, long end) {
        String querySql = String.format("SELECT SUM(price) FROM web_info WHERE create_time >= %d AND create_time < %d AND wechat_id = \"%s\"", start, end, wechatId);
        List<Object[]> objecArraytList = sqlArrayList(querySql);
        String obj = String.valueOf(objecArraytList.get(0));
        return Long.valueOf(obj);
    }
}
