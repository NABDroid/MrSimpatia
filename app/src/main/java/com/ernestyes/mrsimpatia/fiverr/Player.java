package com.ernestyes.mrsimpatia.fiverr;

class Player {
    String name, email;

    public Player(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public Player() {
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
