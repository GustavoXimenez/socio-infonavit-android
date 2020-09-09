package com.grjt.socioinfonavit.model;

import java.util.ArrayList;

public class User {

    public int id;
    public String email;
    public String role;
    public Member member;
    public int sign_in_count;

    public User(int id, String email, String role, Member member, int sign_in_count) {
        this.id = id;
        this.email = email;
        this.role = role;
        this.member = member;
        this.sign_in_count = sign_in_count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public int getSign_in_count() {
        return sign_in_count;
    }

    public void setSign_in_count(int sign_in_count) {
        this.sign_in_count = sign_in_count;
    }
}
