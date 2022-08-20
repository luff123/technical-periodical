package org.luff.tp.model;

public class PaperShortcut {
    public int id;
    public String title;
    public String author;
    public String date;
    public String summary;
    public String coverPath;

    public PaperShortcut(int id, String title, String author, String date, String summary, String coverPath) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.date = date;
        this.summary = summary;
        this.coverPath = coverPath;
    }
}
