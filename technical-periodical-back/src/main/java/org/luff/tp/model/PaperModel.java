package org.luff.tp.model;

import java.util.List;

public class PaperModel {
    public int id;
    public String title;
    public String author;
    public String date;
    public String periodical;
    public String summary;
    public String coverPath;
    public String content;
    public String classify;
    public List<CommentModel> commentModels;

    public PaperModel(
            int id,
            String title,
            String author,
            String date,
            String periodical,
            String summary,
            String coverPath,
            String content,
            String classify,
            List<CommentModel> commentModels
    ) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.date = date;
        this.periodical = periodical;
        this.summary = summary;
        this.coverPath = coverPath;
        this.content = content;
        this.classify = classify;
        this.commentModels = commentModels;
    }
}
