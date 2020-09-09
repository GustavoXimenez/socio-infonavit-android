package com.grjt.socioinfonavit.model;

import java.util.List;

public class Benevit {

    private int id;
    private String name;
    private String description;
    private String title;
    private String instructions;
    private String expirationDate;
    private boolean active;
    private String primaryColor;
    private boolean hasCoupons;
    private String vectorFileName;
    private String vectorFullPath;
    private String slug;
    private Wallet wallet;
    private List<Territory> territories = null;
    private Ally ally;
    private Boolean locked;

    /**
     * No args constructor for use in serialization
     *
     */
    public Benevit() {
    }

    public Benevit(int id, String name, String description, String title, String instructions, String expirationDate, boolean active, String primaryColor, boolean hasCoupons, String vectorFileName, String vectorFullPath, String slug, Wallet wallet, List<Territory> territories, Ally ally, Boolean locked) {
        super();
        this.id = id;
        this.name = name;
        this.description = description;
        this.title = title;
        this.instructions = instructions;
        this.expirationDate = expirationDate;
        this.active = active;
        this.primaryColor = primaryColor;
        this.hasCoupons = hasCoupons;
        this.vectorFileName = vectorFileName;
        this.vectorFullPath = vectorFullPath;
        this.slug = slug;
        this.wallet = wallet;
        this.territories = territories;
        this.ally = ally;
        this.locked = locked;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getPrimaryColor() {
        return primaryColor;
    }

    public void setPrimaryColor(String primaryColor) {
        this.primaryColor = primaryColor;
    }

    public boolean isHasCoupons() {
        return hasCoupons;
    }

    public void setHasCoupons(boolean hasCoupons) {
        this.hasCoupons = hasCoupons;
    }

    public String getVectorFileName() {
        return vectorFileName;
    }

    public void setVectorFileName(String vectorFileName) {
        this.vectorFileName = vectorFileName;
    }

    public String getVectorFullPath() {
        return vectorFullPath;
    }

    public void setVectorFullPath(String vectorFullPath) {
        this.vectorFullPath = vectorFullPath;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    public List<Territory> getTerritories() {
        return territories;
    }

    public void setTerritories(List<Territory> territories) {
        this.territories = territories;
    }

    public Ally getAlly() {
        return ally;
    }

    public void setAlly(Ally ally) {
        this.ally = ally;
    }


    public Boolean getType() {
        return locked;
    }

    public void setType(Boolean type) {
        this.locked = type;
    }


}
