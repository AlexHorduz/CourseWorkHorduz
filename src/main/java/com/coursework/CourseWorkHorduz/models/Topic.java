package com.coursework.CourseWorkHorduz.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Topic {
    private Integer id;
    private String title;
    private String description;
    private Collection<Comment> comments;

    public Topic(Integer id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.comments = new ArrayList<>();
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Collection<Comment> getComments() {
        return comments;
    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }

    //TODO equals and hashCode


}
