package org.luff.tp.pojo;

public class Download {
    private String userId;
    private int paperId;
    private String localPath;

    public Download() {
    }

    public Download(String userId, int paperId, String localPath) {
        this.userId = userId;
        this.paperId = paperId;
        this.localPath = localPath;
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

    public String getLocalPath() {
        return localPath;
    }

    public void setLocalPath(String localPath) {
        this.localPath = localPath;
    }

    @Override
    public String toString() {
        return "Download{" +
                "userId='" + userId + '\'' +
                ", paperId=" + paperId +
                ", localPath='" + localPath + '\'' +
                '}';
    }
}
