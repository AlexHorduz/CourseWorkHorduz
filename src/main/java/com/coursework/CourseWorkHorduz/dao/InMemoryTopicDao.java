package com.coursework.CourseWorkHorduz.dao;

import com.coursework.CourseWorkHorduz.dao.interfaces.ITopicDao;
import com.coursework.CourseWorkHorduz.models.Topic;

import java.util.Collection;

public class InMemoryTopicDao implements ITopicDao {

    private InMemoryDatabase database;
    private Integer id;

    public InMemoryTopicDao(InMemoryDatabase database) {
        this.database = database;
        this.id = 0;
    }

    @Override
    public Collection<Topic> getAllTopics() {
        return database.getTopics().values();
    }

    @Override
    public Topic getTopicById(Integer id) {
        return database.getTopics().get(id);
    }



    @Override
    public void addTopic(String title, String description) {
        Topic topic = new Topic(id, title, description);
        database.getTopics().put(id++, topic);
    }

    @Override
    public void deleteTopic(Topic topic) {
        database.getTopics().remove(topic.getId());
    }

    @Override
    public void updateTopic(Topic topic, Integer id) {
        database.getTopics().put(id, topic);
    }
}
