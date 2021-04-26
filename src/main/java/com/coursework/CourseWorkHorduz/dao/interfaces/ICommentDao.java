package com.coursework.CourseWorkHorduz.dao.interfaces;

import com.coursework.CourseWorkHorduz.models.*;

import java.util.Collection;

public interface ICommentDao {
    Collection<Comment> findByTopicId(Integer id);

    void addComment(Topic topic, User user, String text);
}
