package org.luff.tp.pojo;

public class Pub {
    private String userId;
    private int periodicalId;

    public Pub() {
    }

    public Pub(String userId, int periodicalId) {
        this.userId = userId;
        this.periodicalId = periodicalId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getPeriodicalId() {
        return periodicalId;
    }

    public void setPeriodicalId(int periodicalId) {
        this.periodicalId = periodicalId;
    }

    @Override
    public String toString() {
        return "Pub{" +
                "userId='" + userId + '\'' +
                ", periodicalId=" + periodicalId +
                '}';
    }
}
