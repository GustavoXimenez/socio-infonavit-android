
package com.grjt.socioinfonavit.model;


public class WalletL {

    private int id;
    private String name;
    private String description;
    private String displayText;
    private String icon;
    private String path;
    private String primaryColor;
    private String secondaryColor;
    private String createdAt;
    private String updatedAt;
    private int displayIndex;
    private String avatar;
    private Object mobileCoverUrl;
    private Object desktopCoverUrl;
    private int maxLevel;

    /**
     * No args constructor for use in serialization
     * 
     */
    public WalletL() {
    }

    /**
     * 
     * @param maxLevel
     * @param displayText
     * @param icon
     * @param primaryColor
     * @param description
     * @param desktopCoverUrl
     * @param avatar
     * @param path
     * @param createdAt
     * @param mobileCoverUrl
     * @param name
     * @param id
     * @param displayIndex
     * @param secondaryColor
     * @param updatedAt
     */
    public WalletL(int id, String name, String description, String displayText, String icon, String path, String primaryColor, String secondaryColor, String createdAt, String updatedAt, int displayIndex, String avatar, Object mobileCoverUrl, Object desktopCoverUrl, int maxLevel) {
        super();
        this.id = id;
        this.name = name;
        this.description = description;
        this.displayText = displayText;
        this.icon = icon;
        this.path = path;
        this.primaryColor = primaryColor;
        this.secondaryColor = secondaryColor;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.displayIndex = displayIndex;
        this.avatar = avatar;
        this.mobileCoverUrl = mobileCoverUrl;
        this.desktopCoverUrl = desktopCoverUrl;
        this.maxLevel = maxLevel;
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

    public String getDisplayText() {
        return displayText;
    }

    public void setDisplayText(String displayText) {
        this.displayText = displayText;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPrimaryColor() {
        return primaryColor;
    }

    public void setPrimaryColor(String primaryColor) {
        this.primaryColor = primaryColor;
    }

    public String getSecondaryColor() {
        return secondaryColor;
    }

    public void setSecondaryColor(String secondaryColor) {
        this.secondaryColor = secondaryColor;
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

    public int getDisplayIndex() {
        return displayIndex;
    }

    public void setDisplayIndex(int displayIndex) {
        this.displayIndex = displayIndex;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Object getMobileCoverUrl() {
        return mobileCoverUrl;
    }

    public void setMobileCoverUrl(Object mobileCoverUrl) {
        this.mobileCoverUrl = mobileCoverUrl;
    }

    public Object getDesktopCoverUrl() {
        return desktopCoverUrl;
    }

    public void setDesktopCoverUrl(Object desktopCoverUrl) {
        this.desktopCoverUrl = desktopCoverUrl;
    }

    public int getMaxLevel() {
        return maxLevel;
    }

    public void setMaxLevel(int maxLevel) {
        this.maxLevel = maxLevel;
    }

}
