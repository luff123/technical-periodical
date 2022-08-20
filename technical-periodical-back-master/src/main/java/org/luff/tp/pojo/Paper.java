package org.luff.tp.pojo;

public class Paper {
    private int id;
    private String title;
    private String authorId;
    private String date;
    private int periodicalId;
    private String summary;
    private String coverPath;
    private String content;
    private String classify;

    public Paper() {
    }

    public Paper(
            int id,
            String title,
            String authorId,
            String date,
            int periodicalId,
            String summary,
            String coverPath,
            String content,
            String classify
    ) {
        this.id = id;
        this.title = title;
        this.authorId = authorId;
        this.date = date;
        this.periodicalId = periodicalId;
        this.summary = summary;
        this.coverPath = coverPath;
        this.content = content;
        this.classify = classify;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getPeriodicalId() {
        return periodicalId;
    }

    public void setPeriodicalId(int periodicalId) {
        this.periodicalId = periodicalId;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getCoverPath() {
        return coverPath;
    }

    public void setCoverPath(String coverPath) {
        this.coverPath = coverPath;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }

    @Override
    public String toString() {
        return "Paper{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", authorId='" + authorId + '\'' +
                ", date='" + date + '\'' +
                ", periodicalId=" + periodicalId +
                ", summary='" + summary + '\'' +
                ", coverPath='" + coverPath + '\'' +
                ", content='" + content + '\'' +
                ", classify='" + classify + '\'' +
                '}';
    }
}
