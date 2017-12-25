package com.bmsoft.xu.dao;

import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HBaseDao {
    private static Logger logger = Logger.getLogger("HBaseDao.class");
    private Table table;
    private Admin admin;

    /**
     * 创建表格
     *
     * @param tablename 表名
     * @param seriesStr 列名数组
     */
    private boolean creatTable(String tablename, String[] seriesStr) {
        admin = HBaseIntial.getAdmin();
        try {
            if (!admin.tableExists(TableName.valueOf(tablename)) && admin != null) {
                logger.info("table not exist");
                HTableDescriptor descriptor = new HTableDescriptor(TableName.valueOf(tablename));
                for (String s : seriesStr) {
                    descriptor.addFamily(new HColumnDescriptor(s.getBytes()));
                }
                admin.createTable(descriptor);
                logger.info("创建表成功");
                return true;
            }
        } catch (IOException e) {
            logger.error("tablename error!" + e.getMessage(), e);
        } finally {
            try {
                admin.close();
            } catch (IOException e) {
                logger.error("admin close error!" + e.getMessage(), e);
            }
        }
        return false;
    }

    /**
     * 按表名和行号获取结果
     *
     * @param tableName 表名
     * @param rowkey    行号
     */
    public Result getDataByRowkey(String tableName, String rowkey) {
        Result result = null;
        table = HBaseIntial.getTable(tableName);
        Get get = new Get(Bytes.toBytes(rowkey));
        try {
            result = table.get(get);
        } catch (IOException e) {
            logger.error("Get data error: " + e.getMessage(), e);
        } finally {
            try {
                table.close();
            } catch (IOException e) {
                logger.error(e.getMessage(), e);
            }
        }
        return result;
    }

    /**
     * 按表名和行号获取结果
     *
     * @param tableName 表名
     * @param rowkeys   行号列表
     */
    public List<Result> getDataByRowkey(String tableName, List<String> rowkeys) {
        List<Result> listResult = null;
        rowkeys.forEach((k) -> listResult.add(getDataByRowkey(tableName, k)));
        return listResult;
    }

    /**
     * 按表名获取全部数据
     *
     * @param tableName 表名
     */
    public List<Map<String, String>> getDatasByTablename(String tableName) {
        List<Map<String, String>> listmap = new ArrayList<>();
        ResultScanner resultScanner = null;
        try {
            table = HBaseIntial.getTable(tableName);
            Scan scan = new Scan();
            resultScanner = table.getScanner(scan);
            for (Result result : resultScanner) {
                Map<String, String> map = new HashMap<String, String>();
                for (Cell cell : result.rawCells()) {
                    String qulifier = Bytes.toString(CellUtil.cloneQualifier(cell));
                    String value = Bytes.toString(CellUtil.cloneValue(cell));
                    map.put(qulifier, value);
                }
                listmap.add(map);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            resultScanner.close();
        }
        return listmap;
    }

    /**
     * 批量存数据
     *
     * @param puts      数据条数组
     * @param tableName 表名
     */
    public boolean saveDataByList(List<Put> puts, String tableName) {
        boolean success = false;
        try {
            table = HBaseIntial.getTable(tableName);
            table.put(puts);
            logger.info("save data success!");
            success = true;
        } catch (IOException e) {
            logger.info("获取表格失败");
            e.printStackTrace();
        } finally {
            try {
                table.close();
            } catch (IOException e) {
                logger.error("table close error!" + e.getMessage(), e);
            }
        }
        return success;
    }
    /**
     * 存数据一条数据
     *
     * @param put      数据条
     * @param tableName 表名
     */
    public boolean saveDataByList(Put put, String tableName) {
        boolean success = false;
        try {
            table = HBaseIntial.getTable(tableName);
            table.put(put);
            logger.info("save data success!");
            success = true;
        } catch (IOException e) {
            logger.error("get table error!" + e.getMessage(), e);
        } finally {
            try {
                table.close();
            } catch (IOException e) {
                logger.error("table close error!" + e.getMessage(), e);
            }
        }
        return success;
    }
}