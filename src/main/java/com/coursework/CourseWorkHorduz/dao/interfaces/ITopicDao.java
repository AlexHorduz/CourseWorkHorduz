package com.coursework.CourseWorkHorduz.dao.interfaces;

import com.coursework.CourseWorkHorduz.models.Topic;

import java.util.Collection;

public interface ITopicDao {
    Collection<Topic> getAllTopics();

    Topic getTopicById(Integer id);

    void addTopic(String title, String description);
}
