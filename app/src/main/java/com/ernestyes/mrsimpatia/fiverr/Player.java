package com.ernestyes.mrsimpatia.fiverr;

public class Player {
    private String name, email, associatedStore;
    int point, completedTask;
    private boolean machine1 = false, machine2 = false, machine3 = false, machine4 = false, machine5 = false, color1 = false, color2 = false, color3 = false, color4 = false, color5= false;

    public boolean isMachine1() {
        return machine1;
    }

    public void setMachine1(boolean machine1) {
        this.machine1 = machine1;
    }

    public boolean isMachine2() {
        return machine2;
    }

    public void setMachine2(boolean machine2) {
        this.machine2 = machine2;
    }

    public boolean isMachine3() {
        return machine3;
    }

    public void setMachine3(boolean machine3) {
        this.machine3 = machine3;
    }

    public boolean isMachine4() {
        return machine4;
    }

    public void setMachine4(boolean machine4) {
        this.machine4 = machine4;
    }

    public boolean isMachine5() {
        return machine5;
    }

    public void setMachine5(boolean machine5) {
        this.machine5 = machine5;
    }

    public boolean isColor1() {
        return color1;
    }

    public void setColor1(boolean color1) {
        this.color1 = color1;
    }

    public boolean isColor2() {
        return color2;
    }

    public void setColor2(boolean color2) {
        this.color2 = color2;
    }

    public boolean isColor3() {
        return color3;
    }

    public void setColor3(boolean color3) {
        this.color3 = color3;
    }

    public boolean isColor4() {
        return color4;
    }

    public void setColor4(boolean color4) {
        this.color4 = color4;
    }

    public boolean isColor5() {
        return color5;
    }

    public void setColor5(boolean color5) {
        this.color5 = color5;
    }

    public Player(String name, String email, String associatedStore, int point, int completedTask, boolean machine1, boolean machine2, boolean machine3, boolean machine4, boolean machine5, boolean color1, boolean color2, boolean color3, boolean color4, boolean color5) {
        this.name = name;
        this.email = email;
        this.associatedStore = associatedStore;
        this.point = point;
        this.completedTask = completedTask;
        this.machine1 = machine1;
        this.machine2 = machine2;
        this.machine3 = machine3;
        this.machine4 = machine4;
        this.machine5 = machine5;
        this.color1 = color1;
        this.color2 = color2;
        this.color3 = color3;
        this.color4 = color4;
        this.color5 = color5;
    }

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
