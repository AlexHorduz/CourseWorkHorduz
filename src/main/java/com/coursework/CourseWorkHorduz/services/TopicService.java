package com.coursework.CourseWorkHorduz.services;

import com.coursework.CourseWorkHorduz.dao.interfaces.IDaoFactory;
import com.coursework.CourseWorkHorduz.models.Topic;
import com.coursework.CourseWorkHorduz.models.User;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TopicService {
    private IDaoFactory daoFactory;

    public TopicService(IDaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public void addTopic(String title, String description) {
        daoFactory.getTopicDao().addTopic(title, description);
    }

    public void deleteTopic(Topic topic) {
        daoFactory.getTopicDao().deleteTopic(topic);
    }

    public Collection<Topic> getAllTopics() {
        return daoFactory.getTopicDao().getAllTopics();
    }

    public Collection<Topic> searchByText(String text) {
        String[] words = text.toLowerCase().split(" ");
        return daoFactory.getTopicDao().getAllTopics().stream()
                .filter(topic -> containsAllWords(topic, words))
                .collect(Collectors.toList());
    }

    public Topic getTopicById(Integer id) {
        return daoFactory.getTopicDao().getTopicById(id);
    }

    public void addComment(Topic topic, User user, String text) {
        daoFactory.getCommentDao().addComment(topic, user, text);
    }

    public void updateTopic(Topic topic, String title, String description) {
        daoFactory.getTopicDao().updateTopic(new Topic(topic.getId(), title, description), topic.getId());
    }

    private static boolean containsAllWords(Topic topic, String[] words) {
        String string = topic.getTitle() + " " + topic.getDescription();
        string = string.toLowerCase();
        return Stream.of(words).allMatch(string::contains);
    }


}
