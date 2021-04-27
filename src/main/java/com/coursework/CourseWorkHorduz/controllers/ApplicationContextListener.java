package com.coursework.CourseWorkHorduz.controllers;

import com.coursework.CourseWorkHorduz.dao.InMemoryDaoFactory;
import com.coursework.CourseWorkHorduz.dao.InMemoryDatabase;
import com.coursework.CourseWorkHorduz.models.Comment;
import com.coursework.CourseWorkHorduz.models.Topic;
import com.coursework.CourseWorkHorduz.models.User;
import com.coursework.CourseWorkHorduz.services.*;

import javax.servlet.*;
import javax.servlet.annotation.*;
import java.security.NoSuchAlgorithmException;

@WebListener
public class ApplicationContextListener implements ServletContextListener {


    @Override
    public void contextInitialized(ServletContextEvent sce) {
        InMemoryDatabase database = new InMemoryDatabase();
        Topic topic = new Topic(1, "CourseWork", "How to do this f*cking coursework?");
        database.getTopics().put(1, topic);
        database.getTopics().put(2, new Topic(2, "Well", "Actually that's not that hard"));
        database.getTopics().put(3, new Topic(3, "Shit", "These topics don't appear to be cool"));

        InMemoryDaoFactory daoFactory = database.getDaoFactory();


        UserService userService = new UserService(daoFactory);
        TopicService topicService = new TopicService(daoFactory);

        try {
            User admin =  new User(1, "admin", userService.getHash("12345"), true);
            database.getUsers().put(1, admin);
            daoFactory.getCommentDao().addComment(topic, admin, "Try to make some tea and relax");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        sce.getServletContext().setAttribute("userService", userService);
        sce.getServletContext().setAttribute("topicService", topicService);

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        /* This method is called when the servlet Context is undeployed or Application Server shuts down. */
    }



}
