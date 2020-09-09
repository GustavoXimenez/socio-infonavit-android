
package com.grjt.socioinfonavit.model;

import java.util.List;

public class Landing {

    private List<Locked> locked = null;
    private List<Unlocked> unlocked = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Landing() {
    }

    /**
     * 
     * @param locked
     * @param unlocked
     */
    public Landing(List<Locked> locked, List<Unlocked> unlocked) {
        super();
        this.locked = locked;
        this.unlocked = unlocked;
    }

    public List<Locked> getLocked() {
        return locked;
    }

    public void setLocked(List<Locked> locked) {
        this.locked = locked;
    }

    public List<Unlocked> getUnlocked() {
        return unlocked;
    }

    public void setUnlocked(List<Unlocked> unlocked) {
        this.unlocked = unlocked;
    }

}
