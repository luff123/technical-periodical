package org.luff.tp.model;

import java.util.List;

public class PeriodicalModel {
    public int id;
    public String name;
    public String author;
    public String date;
    public String summary;
    public String coverPath;
    public String classify;
    public List<PaperShortcut> paperShortcuts;

    public PeriodicalModel(
            int id,
            String name,
            String author,
            String date,
            String summary,
            String coverPath,
            String classify,
            List<PaperShortcut> paperShortcuts
    ) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.date = date;
        this.summary = summary;
        this.coverPath = coverPath;
        this.classify = classify;
        this.paperShortcuts = paperShortcuts;
    }
}
