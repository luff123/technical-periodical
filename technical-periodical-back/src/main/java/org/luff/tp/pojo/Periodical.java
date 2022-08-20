package org.luff.tp.pojo;

public class Periodical {
    private int id;
    private String name;
    private String authorId;
    private String date;
    private String summary;
    private String coverPath;
    private String classify;

    public Periodical() {
    }

    public Periodical(
            int id,
            String name,
            String authorId,
            String date,
            String summary,
            String coverPath,
            String classify
    ) {
        this.id = id;
        this.name = name;
        this.authorId = authorId;
        this.date = date;
        this.summary = summary;
        this.coverPath = coverPath;
        this.classify = classify;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }

    @Override
    public String toString() {
        return "Periodical{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", authorId='" + authorId + '\'' +
                ", date='" + date + '\'' +
                ", summary='" + summary + '\'' +
                ", coverPath='" + coverPath + '\'' +
                ", classify='" + classify + '\'' +
                '}';
    }
}
