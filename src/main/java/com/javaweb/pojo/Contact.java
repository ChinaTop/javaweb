package com.javaweb.pojo;

import java.util.Date;

public class Contact {
    private Integer id;
    private String name;
    private String org;
    private String mobil;
    private String phone;
    private String email;
    private String address;

    public Contact() {
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setOrg(String org) {
        this.org = org;
    }

    public String getOrg() {
        return org;
    }

    public void setMobil(String mobil) {
        this.mobil = mobil;
    }

    public String getMobil() {
        return mobil;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }
}