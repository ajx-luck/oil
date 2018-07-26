package com.breast.oil.repository;

/**
 * Created by B04e on 2017/11/28.
 */
public interface StatisticsRepository {
    Long totalMoney(String wechatI, long start, long end);
}
