package org.luff.tp.pojo;

public class Comment {
    private int id;
    private String userId;
    private int paperId;
    private String date;
    private String content;

    public Comment() {
    }

    public Comment(int id, String userId, int paperId, String date, String content) {
        this.id = id;
        this.userId = userId;
        this.paperId = paperId;
        this.date = date;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", paperId=" + paperId +
                ", date='" + date + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
