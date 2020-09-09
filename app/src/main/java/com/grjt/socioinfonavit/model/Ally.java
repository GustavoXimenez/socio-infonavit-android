
package com.grjt.socioinfonavit.model;


public class Ally {

    private int id;
    private String name;
    private String slug;
    private String mini_logo_file_name;
    private String mini_logo_full_path;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Ally() {
    }

    public Ally(int id, String name, String slug, String mini_logo_file_name, String mini_logo_full_path) {
        super();
        this.id = id;
        this.name = name;
        this.slug = slug;
        this.mini_logo_file_name = mini_logo_file_name;
        this.mini_logo_full_path = mini_logo_full_path;
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

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getMiniLogoFileName() {
        return mini_logo_file_name;
    }

    public void setMiniLogoFileName(String miniLogoFileName) {
        this.mini_logo_file_name = miniLogoFileName;
    }

    public String getMiniLogoFullPath() {
        return mini_logo_full_path;
    }

    public void setMiniLogoFullPath(String miniLogoFullPath) {
        this.mini_logo_full_path = miniLogoFullPath;
    }

}
