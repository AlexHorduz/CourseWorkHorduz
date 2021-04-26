package com.coursework.CourseWorkHorduz.services;

import com.coursework.CourseWorkHorduz.dao.interfaces.IDaoFactory;
import com.coursework.CourseWorkHorduz.models.Topic;

import java.util.Collection;

public class TopicService {
    private IDaoFactory daoFactory;

    public TopicService(IDaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public void addTopic(String title, String description) {
        daoFactory.getTopicDao().addTopic(title, description);
    }

    public Collection<Topic> getAllTopics() {
        return daoFactory.getTopicDao().getAllTopics();
    }
}
