package com.grjt.socioinfonavit.model;

public class Member {

    public int id;
    public int user_id;
    public String id_socio_infonavit;
    public String name;
    public String lastname;
    public String mobile;
    public int zipcode;
    public String avatar;

    public Member(int id, int user_id, String id_socio_infonavit,
                  String name, String lastname, String mobile,
                  int zipcode, String avatar) {
        this.id = id;
        this.user_id = user_id;
        this.id_socio_infonavit = id_socio_infonavit;
        this.name = name;
        this.lastname = lastname;
        this.mobile = mobile;
        this.zipcode = zipcode;
        this.avatar = avatar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getId_socio_infonavit() {
        return id_socio_infonavit;
    }

    public void setId_socio_infonavit(String id_socio_infonavit) {
        this.id_socio_infonavit = id_socio_infonavit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getZipcode() {
        return zipcode;
    }

    public void setZipcode(int zipcode) {
        this.zipcode = zipcode;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
