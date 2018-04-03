package com.javaweb.dao;

import com.javaweb.pojo.Contact;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContactDao {
    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    public List<Contact> find() {
        List<Contact> contacts = new ArrayList<Contact>();

        try {
            conn = DBUtil.openConnection();
            String sql = "SELECT * FROM contact WHERE 1=1";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Contact contact = new Contact();
                contact.setId(rs.getInt("id")); // 根据字段索引获取值
                contact.setName(rs.getString("name")); // 根据字段名获取值
                contact.setOrg(rs.getString("org"));
                contact.setMobil(rs.getString("mobil"));
                contact.setPhone(rs.getString("phone"));
                contact.setEmail(rs.getString("email"));
                contact.setAddress(rs.getString("address"));
                contacts.add(contact);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, rs);
        }
        return contacts;
    }


    public List<Contact> find(String name, String mobil) {
        List<Contact> contacts = new ArrayList<Contact>();
        try {
            conn = DBUtil.openConnection();
            String sql = "SELECT * FROM contact WHERE 1=1";
            if (name != null && name.trim().length() > 0) {
                sql += " and name like ? ";
            }
            if (mobil != null && mobil.trim().length() > 0) {
                sql += " and mobil like ? ";
            }
            ps = conn.prepareStatement(sql);
            int index = 1;
            if (name != null && name.trim().length() > 0) {
                ps.setString(index, "%" + name + "%");
                index++;
            }
            if (mobil != null && mobil.trim().length() > 0) {
                ps.setString(index, "%" + mobil + "%");
            }

            rs = ps.executeQuery();
            while (rs.next()) {
                Contact contact = new Contact();
                contact.setId(rs.getInt("id")); // 根据字段索引获取值
                contact.setName(rs.getString("name")); // 根据字段名获取值
                contact.setOrg(rs.getString("org"));
                contact.setMobil(rs.getString("mobil"));
                contact.setPhone(rs.getString("phone"));
                contact.setEmail(rs.getString("email"));
                contact.setAddress(rs.getString("address"));
                contacts.add(contact);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, rs);
        }
        return contacts;
    }



    public int add(Contact contact) {
        int count=0;
        conn= DBUtil.openConnection();
        String sql="insert into contact(name,mobil,org,phone,email,address) values (?,?,?,?,?,?)";
        try {
            ps=conn.prepareStatement(sql);
            ps.setString(1,contact.getName());
            ps.setString(2,contact.getMobil());
            ps.setString(3,contact.getOrg());
            ps.setString(4,contact.getPhone());
            ps.setString(5,contact.getEmail());
            ps.setString(6,contact.getAddress());
            count=ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(conn,ps,rs);
        }
        return count;
    }



    public int modify(Contact contact){
        int count=0;
        conn= DBUtil.openConnection();
        String sql="update contact set name=?,mobil=?,org=?,phone=?,email=?,address=? where id=?";
        try {
            ps=conn.prepareStatement(sql);
            ps.setString(1,contact.getName());
            ps.setString(2,contact.getMobil());
            ps.setString(3,contact.getOrg());
            ps.setString(4,contact.getPhone());
            ps.setString(5,contact.getEmail());
            ps.setString(6,contact.getAddress());
            ps.setInt(7,contact.getId());
            count=ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(conn,ps,rs);
        }
        return count;
    }



    public int remove(Integer id){
        int count=0;
        conn= DBUtil.openConnection();
        String sql="delete from contact where id=?";
        try {
            ps=conn.prepareStatement(sql);
            ps.setInt(1,id);
            count=ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(conn,ps,rs);
        }
        return count;
    }


    public Contact find(Integer id){
        Contact contact=null;
        try {
            conn = DBUtil.openConnection();
            String sql = "select id,name,mobil,org,phone,email,address from contact where id=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                contact = new Contact();
                contact.setId(rs.getInt(1)); // 根据字段索引获取值
                contact.setName(rs.getString("name")); // 根据字段名获取值
                contact.setMobil(rs.getString("mobil"));
                contact.setOrg(rs.getString("org"));
                contact.setPhone(rs.getString("phone"));
                contact.setEmail(rs.getString("email"));
                contact.setAddress(rs.getString("address"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, rs);
        }
        return contact;
    }

}