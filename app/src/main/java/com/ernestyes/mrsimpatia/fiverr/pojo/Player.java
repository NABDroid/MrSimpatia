package com.ernestyes.mrsimpatia.fiverr.pojo;

public class Player {
    private String name, email, associatedStore;
    int point, completedTask;


    public Player() {
    }

    public Player(String name, String email, String associatedStore, int point, int completedTask) {
        this.name = name;
        this.email = email;
        this.associatedStore = associatedStore;
        this.point = point;
        this.completedTask = completedTask;
    }


    public String getAssociatedStore() {
        return associatedStore;
    }

    public void setAssociatedStore(String associatedStore) {
        this.associatedStore = associatedStore;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public int getCompletedTask() {
        return completedTask;
    }

    public void setCompletedTask(int completedTask) {
        this.completedTask = completedTask;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
