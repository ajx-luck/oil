package com.breast.oil.utils;

import java.util.*;

/**
 * Created by B04e on 2018/3/8.
 */
public class CommonUtils {

    public static void sortMapByValue(Map<String, Integer> map){
        //获取entrySet
        Set<Map.Entry<String,Integer>> mapEntries = map.entrySet();

        for(Map.Entry<String, Integer> entry : mapEntries){
            System.out.println("key:" +entry.getKey()+"   value:"+entry.getValue() );
        }

        //使用链表来对集合进行排序，使用LinkedList，利于插入元素
        List<Map.Entry<String, Integer>> result = new LinkedList<>(mapEntries);
        //自定义比较器来比较链表中的元素
        Collections.sort(result, new Comparator<Map.Entry<String, Integer>>() {
            //基于entry的值（Entry.getValue()），来排序链表
            @Override
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2) {

                return o1.getValue().compareTo(o2.getValue()) ;
            }

        });

        //将排好序的存入到LinkedHashMap(可保持顺序)中，需要存储键和值信息对到新的映射中。
        Map<String,Integer> linkMap = new LinkedHashMap<>();
        for(Map.Entry<String,Integer> newEntry :result){
            linkMap.put(newEntry.getKey(), newEntry.getValue());
        }
        //根据entrySet()方法遍历linkMap
        for(Map.Entry<String, Integer> mapEntry : linkMap.entrySet()){
            System.out.println("key:"+mapEntry.getKey()+"  value:"+mapEntry.getValue());
        }
    }
}
