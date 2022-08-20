package org.luff.tp.model;

import java.util.List;

public class CommentModel {
    public Integer id;
    public String user;
    public String date;
    public String content;
    public List<ReplyModel> replyModels;

    public CommentModel(Integer id, String user, String date, String content, List<ReplyModel> replyModels) {
        this.id = id;
        this.user = user;
        this.date = date;
        this.content = content;
        this.replyModels = replyModels;
    }
}
