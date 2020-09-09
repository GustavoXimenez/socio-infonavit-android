package com.grjt.socioinfonavit.model;

public class Wallet {

    private int id;
    private int displayIndex;
    private String displayText;
    private String icon;
    private String path;
    private int maxLevel;
    private String avatar;
    private String name;
    private int benevitCount;
    private Object mobileCoverUrl;
    private Object desktopCoverUrl;
    private int memberLevel;
    private String primaryColor;

    public Wallet() {
    }

    public Wallet(int id, int displayIndex, String displayText, String icon, String path, int maxLevel, String avatar, String name, int benevitCount, Object mobileCoverUrl, Object desktopCoverUrl, int memberLevel, String primaryColor) {
        super();
        this.id = id;
        this.displayIndex = displayIndex;
        this.displayText = displayText;
        this.icon = icon;
        this.path = path;
        this.maxLevel = maxLevel;
        this.avatar = avatar;
        this.name = name;
        this.benevitCount = benevitCount;
        this.mobileCoverUrl = mobileCoverUrl;
        this.desktopCoverUrl = desktopCoverUrl;
        this.memberLevel = memberLevel;
        this.primaryColor = primaryColor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDisplayIndex() {
        return displayIndex;
    }

    public void setDisplayIndex(int displayIndex) {
        this.displayIndex = displayIndex;
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

    public int getMaxLevel() {
        return maxLevel;
    }

    public void setMaxLevel(int maxLevel) {
        this.maxLevel = maxLevel;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBenevitCount() {
        return benevitCount;
    }

    public void setBenevitCount(int benevitCount) {
        this.benevitCount = benevitCount;
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

    public int getMemberLevel() {
        return memberLevel;
    }

    public void setMemberLevel(int memberLevel) {
        this.memberLevel = memberLevel;
    }

    public String getPrimaryColor() {
        return primaryColor;
    }

    public void setPrimaryColor(String primaryColor) {
        this.primaryColor = primaryColor;
    }

}
