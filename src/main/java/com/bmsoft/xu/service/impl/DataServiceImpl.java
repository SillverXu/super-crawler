package com.bmsoft.xu.service.impl;


import com.bmsoft.xu.dao.HBaseDao;
import com.bmsoft.xu.service.DataService;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataServiceImpl implements DataService {
    private static Logger logger = Logger.getLogger(DataServiceImpl.class);

    private HBaseDao hbasedao = new HBaseDao();
    private static final String TABLENAME = "audit1_data_test";

    public List<Map<String, String>> getdata(String tablename) {
        return hbasedao.getDatasByTablename(tablename);
    }

    public boolean saveDatasByList(List<Map<String, String>> listmap) {
        List<Put> puts = new ArrayList<Put>();
        logger.info("开始存储list格式的数据，共有"+listmap.size()+"条数据！");
        for (int i = 0; i < listmap.size(); i++) {
            Map<String,String> map = listmap.get(i);
            String rowkey = map.get("rowkey");
            if(rowkey != null || !"".equals(rowkey)){
                Put put = new Put(Bytes.toBytes(rowkey));
                map.remove(rowkey);
                for (String s:map.keySet()) {
                    put.addColumn(Bytes.toBytes("audit1"),Bytes.toBytes(s),Bytes.toBytes(map.get(s)));
                }
                puts.add(put);
            }
            }
        return hbasedao.saveDatasByList(puts,TABLENAME);
        }
    public boolean saveDataByMap(Map<String,String> map) {
        String rowkey = map.get("rowkey");
        Put put = null;
        logger.info("开始存储数据，行号为："+rowkey);
        if (rowkey != null || !"".equals(rowkey)) {
            put = new Put(Bytes.toBytes(rowkey));
            map.remove(rowkey);
            for (String s : map.keySet()) {
                put.addColumn(Bytes.toBytes("audit1"), Bytes.toBytes(s), Bytes.toBytes(map.get(s)));
            }
        }
        return hbasedao.saveDataByList(put,TABLENAME);
    }
}
