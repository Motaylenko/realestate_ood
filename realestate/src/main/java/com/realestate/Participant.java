package com.realestate;
/**
 * Абстрактний клас учасника угоди
 */
public abstract class Participant {
    protected String name;
    protected String contactInfo;
    
    public Participant(String name, String contactInfo) {
        this.name = name;
        this.contactInfo = contactInfo;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }
}