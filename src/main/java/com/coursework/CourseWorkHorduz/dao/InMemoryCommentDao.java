package com.coursework.CourseWorkHorduz.dao;

import com.coursework.CourseWorkHorduz.dao.interfaces.ICommentDao;
import com.coursework.CourseWorkHorduz.models.Comment;
import com.coursework.CourseWorkHorduz.models.Topic;
import com.coursework.CourseWorkHorduz.models.User;

import java.util.Collection;

public class InMemoryCommentDao implements ICommentDao {

    private InMemoryDatabase database;
    private Integer id;

    public InMemoryCommentDao(InMemoryDatabase database) {
        this.database = database;
        this.id = 0;
    }

    @Override
    public Collection<Comment> findByTopicId(Integer id) {
        return database.getComments().values();
    }

    @Override
    public void addComment(Topic topic, User user, String text) {
        Comment comment = new Comment(id, topic, user, text);
        database.getComments().put(id++, comment);
        topic.addComment(comment);
    }
}
