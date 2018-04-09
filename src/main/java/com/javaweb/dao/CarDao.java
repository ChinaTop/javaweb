package com.javaweb.dao;

import com.javaweb.dao.DBUtil;
import com.javaweb.pojo.Car;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarDao {
    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    public List<Car> find(){
        List<Car> cars=new ArrayList<Car>();
        try {
            conn = DBUtil.openConnection();
            String sql = "SELECT id,name,price,createdate FROM car WHERE 1=1 ";
            ps = conn.prepareStatement(sql);
            rs=ps.executeQuery();
            while (rs.next()) {
                Car car = new Car();
                car.setId(rs.getInt("id")); // 根据字段索引获取值
                car.setName(rs.getString("name")); // 根据字段名获取值
                car.setPrice(rs.getDouble("price"));
                car.setCreateDate(rs.getDate("createdate"));
                cars.add(car);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, rs);
        }
        return cars;
    }


    public Car login(String username,String password) {
        Car car = new Car();
        try {
            conn = DBUtil.openConnection();
            String sql = "SELECT id,name,price,createdate FROM car WHERE name=? and id=? ";
            ps = conn.prepareStatement(sql);
            ps.setString(1,username);
            ps.setString(2,password);
            rs=ps.executeQuery();
            while (rs.next()) {
                car.setId(rs.getInt("id")); // 根据字段索引获取值
                car.setName(rs.getString("name")); // 根据字段名获取值
                car.setPrice(rs.getDouble("price"));
                car.setCreateDate(rs.getDate("createdate"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, rs);
        }
        return car;
    }


    public List<Car> find(Integer page, Integer pagesize, String sort, String order, String name, Double price) {
        List<Car> cars = new ArrayList<Car>();

        try {
            conn = DBUtil.openConnection();
            String sql = "SELECT id,name,price,createdate FROM car ";
            sql += "inner join (select id from car where 1=1 ";
            if (name != null && name.trim().length() > 0) {
                sql += " and name like ? ";
            }
            if (price != null && price > 0) {
                sql += " and price = ? ";
            }
            sql += " order by " + sort + " " + order + " limit " + page + ", " + pagesize + ") as lim using(id)";
            // select * from car where 1=1
            // select * from car where 1=1 and name like ?
            // select * from car where 1=1 and price = ?
            // select * from car where 1=1 and name like ? and price = ?

            ps = conn.prepareStatement(sql);
            int index = 1;
            if (name != null && name.trim().length() > 0) {
                ps.setString(index, "%" + name + "%");
                index++;
            }
            if (price != null && price > 0) {
                ps.setDouble(index, price);
            }

            rs = ps.executeQuery();
            while (rs.next()) {
                Car car = new Car();
                car.setId(rs.getInt("id")); // 根据字段索引获取值
                car.setName(rs.getString("name")); // 根据字段名获取值
                car.setPrice(rs.getDouble("price"));
                car.setCreateDate(rs.getDate("createdate"));

                cars.add(car);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, rs);
        }
        return cars;
    }


    //增
    public int add(Car car) {
        int count = 0;
        conn = DBUtil.openConnection();
        String sql = "insert into car(name,price,createdate) values (?,?,?)";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, car.getName());
            ps.setDouble(2, car.getPrice());
            ps.setDate(3, new java.sql.Date(car.getCreateDate().getTime()));
            count = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, rs);
        }
        return count;
    }


    public int modify(Car car) {
        int count = 0;
        conn = DBUtil.openConnection();
        String sql = "update car set name=?,price=?,createdate=? where id=?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, car.getName());
            ps.setDouble(2, car.getPrice());
            ps.setDate(3, new Date(car.getCreateDate().getTime()));
            ps.setInt(4, car.getId());
            count = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, rs);
        }
        return count;
    }


    public int remove(Integer id) {
        int count = 0;
        conn = DBUtil.openConnection();
        String sql = "delete from car where id=?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            count = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, rs);
        }
        return count;
    }


    public Car find(Integer id) {
        Car car = null;
        try {
            conn = DBUtil.openConnection();
            String sql = "select id,name,price,createdate from car where id=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                car = new Car();
                car.setId(rs.getInt(1)); // 根据字段索引获取值
                car.setName(rs.getString("name")); // 根据字段名获取值
                car.setPrice(rs.getDouble("price"));
                car.setCreateDate(rs.getDate("createdate"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, rs);
        }
        return car;
    }


    public Integer getMaxPage(String name, Double price,Integer pagesize) {
        Integer count = 0;
        try {
            conn = DBUtil.openConnection();
            String sql = "select count(id) from car where 1=1 ";
            if (name != null && name.trim().length() > 0) {
                sql+=" and name like ? ";
            }
            if (price != null && price > 0) {
                sql+=" and price = ? ";
            }
            ps=conn.prepareStatement(sql);
            rs = ps.executeQuery();
            rs.next();
            count=rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(conn,ps,rs);
        }
        return (count%pagesize==0)?
                count/pagesize:(count/pagesize)+1;

    }

}
