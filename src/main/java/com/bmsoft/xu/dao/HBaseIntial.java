package com.bmsoft.xu.dao;

import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Table;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutorService;

public class HBaseIntial {
    private static final Logger LOG = LoggerFactory.getLogger(HBaseIntial.class);
    private static final String[] CONF_FILES = {"core-site.xml", "hdfs-site.xml", "hbase-site.xml"};
    private static String CONF_PATH = null;

    public static void setConfPath(String confPath) {
        CONF_PATH = confPath;
    }


    /**
     * Get the hbase connection.多线程
     *
     * @return
     */
    public static Connection getConnection(ExecutorService pool) {
        Connection connection = null;
        try {
            connection = ConnectionFactory.createConnection(getConfInstance(), pool);
        } catch (IOException e) {
            LOG.error("Create hbase connection failed:" + e.getMessage(), e);
        }
        return connection;
    }

    /**
     * Get the hbase connection.单线程
     *
     * @return
     */
    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = ConnectionFactory.createConnection(getConfInstance());
        } catch (IOException e) {
            LOG.error("Create hbase connection faild:" + e.getMessage(), e);
        }
        return connection;
    }

    /**
     * Get the HBase Table instance.
     *
     * @param tableName the table name
     * @return Table
     */
    public static Table getTable(String tableName) {
        Table table = null;
        try {
            table = getConnection().getTable(TableName.valueOf(tableName));
        } catch (IOException e) {
            LOG.error("Get table error:" + e.getMessage(), e);
        }
        return table;
    }

    public static Admin getAdmin() {
        Admin admin = null;
        try {
            admin = getConnection().getAdmin();
        } catch (IOException e) {
            LOG.error("Get hbase admin instance error:" + e.getMessage(), e);
        }
        return admin;
    }

    /**
     * get configuration
     */
    public static Configuration getConfInstance() {
        Configuration configuration = null;
        if (StringUtils.isEmpty(CONF_PATH)) throw new IllegalArgumentException("'" + CONF_PATH + "'");
        configuration = HBaseConfiguration.create();
        configuration.addResource(new Path(CONF_PATH + File.separator + CONF_FILES[0]));
        configuration.addResource(new Path(CONF_PATH + File.separator + CONF_FILES[1]));
        configuration.addResource(new Path(CONF_PATH + File.separator + CONF_FILES[2]));
        return configuration;
    }
}
