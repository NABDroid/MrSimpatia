package com.ernestyes.mrsimpatia.fiverr;

public class Store {
    String storeName, authorName, email, location;

    public Store(String storeName, String authorName, String email, String location) {
        this.storeName = storeName;
        this.authorName = authorName;
        this.email = email;
        this.location = location;
    }

    public Store() {
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
