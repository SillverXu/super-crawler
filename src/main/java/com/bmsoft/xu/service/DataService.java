package com.bmsoft.xu.service;

import java.util.List;
import java.util.Map;

public interface DataService {
    /**
     * 拿出表中的所有数据
     *
     * @param tablename 表名
     */
    List<Map<String, String>> getdata(String tablename);

    /**
     * 批量存储数据
     *
     * @param listmap
     */
    boolean saveDatasByList(List<Map<String, String>> listmap);
    /**
     * 存一条数据
     *
     * @param map
     */
    boolean saveDataByMap(Map<String, String> map);
}
