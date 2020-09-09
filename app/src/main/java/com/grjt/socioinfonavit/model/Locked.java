
package com.grjt.socioinfonavit.model;

import java.util.List;

public class Locked {

    private int id;
    private String name;
    private String description;
    private String title;
    private String instructions;
    private String expiration_date;
    private boolean active;
    private String primary_color;
    private boolean has_coupons;
    private String vector_file_name;
    private String vector_full_path;
    private String slug;
    private Wallet wallet;
    private List<Territory> territories = null;
    private Ally ally;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Locked() {
    }

    public Locked(int id, String name, String description, String title, String instructions, String expiration_date,
                  boolean active, String primary_color, boolean has_coupons, String vector_file_name,
                  String vector_full_path, String slug, Wallet wallet, List<Territory> territories, Ally ally) {
        super();
        this.id = id;
        this.name = name;
        this.description = description;
        this.title = title;
        this.instructions = instructions;
        this.expiration_date = expiration_date;
        this.active = active;
        this.primary_color = primary_color;
        this.has_coupons = has_coupons;
        this.vector_file_name = vector_file_name;
        this.vector_full_path = vector_full_path;
        this.slug = slug;
        this.wallet = wallet;
        this.territories = territories;
        this.ally = ally;
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
        return expiration_date;
    }

    public void setExpirationDate(String expirationDate) {
        this.expiration_date = expirationDate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getPrimaryColor() {
        return primary_color;
    }

    public void setPrimaryColor(String primaryColor) {
        this.primary_color = primaryColor;
    }

    public boolean isHasCoupons() {
        return has_coupons;
    }

    public void setHasCoupons(boolean hasCoupons) {
        this.has_coupons = hasCoupons;
    }

    public String getVectorFileName() {
        return vector_file_name;
    }

    public void setVectorFileName(String vectorFileName) {
        this.vector_file_name = vectorFileName;
    }

    public String getVectorFullPath() {
        return vector_full_path;
    }

    public void setVectorFullPath(String vectorFullPath) {
        this.vector_full_path = vectorFullPath;
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

}
