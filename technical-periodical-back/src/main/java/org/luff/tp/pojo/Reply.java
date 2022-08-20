package org.luff.tp.pojo;

public class Reply {
    private int id;
    private String userId;
    private int commentId;
    private String date;
    private String content;

    public Reply() {
    }

    public Reply(int id, String userId, int commentId, String date, String content) {
        this.id = id;
        this.userId = userId;
        this.commentId = commentId;
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

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
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
        return "Reply{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", commentId=" + commentId +
                ", date='" + date + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
