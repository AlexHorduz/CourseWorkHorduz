package com.coursework.CourseWorkHorduz.models;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;
import java.util.Objects;

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

    public String getInstant() {
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime( FormatStyle.SHORT )
                        .withLocale( Locale.UK )
                        .withZone( ZoneId.systemDefault() );
        return formatter.format(instant);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return id.equals(comment.id) && topic.equals(comment.topic) && user.equals(comment.user) &&
                text.equals(comment.text) && instant.equals(comment.instant);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, topic, user, text, instant);
    }
}
