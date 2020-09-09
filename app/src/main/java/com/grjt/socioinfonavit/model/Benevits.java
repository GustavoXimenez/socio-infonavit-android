package com.grjt.socioinfonavit.model;

import java.util.ArrayList;
import java.util.List;

public class Benevits {

    public ArrayList<Benevit> benevit;
    public int id;
    public String description;

    public Benevits() { }

    public Benevits(ArrayList<Benevit> benevit, int id, String description) {
        this.benevit = benevit;
        this.id = id;
        this.description = description;
    }

    public ArrayList<Benevit> getBenevit() {
        return benevit;
    }

    public void setBenevit(ArrayList<Benevit> benevit) {
        this.benevit = benevit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
