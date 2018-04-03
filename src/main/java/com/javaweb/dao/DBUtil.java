package com.javaweb.dao;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import sun.net.ConnectionResetException;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class DBUtil {
    private static String driverName="com.mysql.jdbc.Driver";
    private static String url="jdbc:mysql://127.0.0.1:3306/employee";
    private static String user="root";
    private static String password="root";

    private static ComboPooledDataSource dataSource;

    static {
        try {
            dataSource = new ComboPooledDataSource();
            dataSource.setDriverClass(driverName);
            dataSource.setJdbcUrl(url);
            dataSource.setUser(user);
            dataSource.setPassword(password);
            //连接池参数
            dataSource.setMinPoolSize(4);
            dataSource.setMaxPoolSize(20);
            dataSource.setInitialPoolSize(8);
            dataSource.setCheckoutTimeout(1800);
            dataSource.setAcquireIncrement(4);
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
    }

    public static Connection openConnection(){
        Connection conn=null;

            //Class.forName(driverName);
            //conn= DriverManager.getConnection(url,user,password);
        try {
            conn=dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }


    public static void close(Connection conn, PreparedStatement ps, ResultSet rs){
        if(rs!=null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(ps!=null){
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(conn!=null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }



}
