package com.coursework.CourseWorkHorduz.dao;

import com.coursework.CourseWorkHorduz.models.*;

import java.util.Map;
import java.util.TreeMap;

public class InMemoryDatabase {
    private Map<Integer, User> users;
    private Map<Integer, Comment> comments;
    private Map<Integer, Topic> topics;

    public InMemoryDatabase() {
        users = new TreeMap<>();
        comments = new TreeMap<>();
        topics = new TreeMap<>();
    }

    public InMemoryDaoFactory getDaoFactory() {
        return new InMemoryDaoFactory(this);
    }

    public Map<Integer, User> getUsers() {
        return users;
    }

    public Map<Integer, Comment> getComments() {
        return comments;
    }

    public Map<Integer, Topic> getTopics() {
        return topics;
    }
}
