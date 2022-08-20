package org.luff.tp.model;

public class PeriodicalShortcut {
    public int id;
    public String name;
    public String author;
    public String date;
    public String summary;
    public String coverPath;
    public String classify;

    public PeriodicalShortcut(
            int id, String name, String author, String date, String summary, String coverPath, String classify
    ) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.date = date;
        this.summary = summary;
        this.coverPath = coverPath;
        this.classify = classify;
    }

    @Override
    public String toString() {
        return "PeriodicalShortcut{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", date='" + date + '\'' +
                ", summary='" + summary + '\'' +
                ", coverPath='" + coverPath + '\'' +
                ", classify='" + classify + '\'' +
                '}';
    }
}
