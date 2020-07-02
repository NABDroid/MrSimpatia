package com.ernestyes.mrsimpatia.fiverr;

public class OnlineStatus {
    private String uId, userName;
    boolean onlineStatus;

    public OnlineStatus() {
    }

    public OnlineStatus(String uId, String userName, boolean onlineStatus) {
        this.uId = uId;
        this.userName = userName;
        this.onlineStatus = onlineStatus;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean isOnlineStatus() {
        return onlineStatus;
    }

    public void setOnlineStatus(boolean onlineStatus) {
        this.onlineStatus = onlineStatus;
    }
}
