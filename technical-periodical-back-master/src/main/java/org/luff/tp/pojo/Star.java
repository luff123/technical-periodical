package org.luff.tp.pojo;

public class Star {
    private String userId;
    private int paperId;

    public Star() {
    }

    public Star(String userId, int paperId) {
        this.userId = userId;
        this.paperId = paperId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getPaperId() {
        return paperId;
    }

    public void setPaperId(int paperId) {
        this.paperId = paperId;
    }

    @Override
    public String toString() {
        return "Star{" +
                "userId='" + userId + '\'' +
                ", paperId=" + paperId +
                '}';
    }
}
