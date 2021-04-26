package com.coursework.CourseWorkHorduz.models;

import java.time.Instant;

public class Comment {
    private Integer id;
    private Topic topic;
    private User user;
    private String text;
    private Instant instant;

    public Comment(Integer id, Topic topic, User user, String text, Instant instant) {
        this.id = id;
        this.topic = topic;
        this.user = user;
        this.text = text;
        this.instant = instant;
    }

    public Comment(Integer id, Topic topic, User user, String text) {
        this(id, topic, user, text, Instant.now());
    }

    public Integer getId() {
        return id;
    }

    public Topic getTopic() {
        return topic;
    }

    public User getUser() {
        return user;
    }

    public String getText() {
        return text;
    }

    public Instant getInstant() { return instant;}
}
