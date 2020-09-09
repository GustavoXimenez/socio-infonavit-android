
package com.grjt.socioinfonavit.model;


public class Territory {

    private int id;
    private String name;
    private String clave;
    private String createdAt;
    private String updatedAt;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Territory() {
    }

    /**
     * 
     * @param createdAt
     * @param clave
     * @param name
     * @param id
     * @param updatedAt
     */
    public Territory(int id, String name, String clave, String createdAt, String updatedAt) {
        super();
        this.id = id;
        this.name = name;
        this.clave = clave;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

}
