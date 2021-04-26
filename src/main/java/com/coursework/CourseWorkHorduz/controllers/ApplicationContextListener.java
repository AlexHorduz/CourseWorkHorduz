package com.coursework.CourseWorkHorduz.controllers;

import com.coursework.CourseWorkHorduz.dao.InMemoryDaoFactory;
import com.coursework.CourseWorkHorduz.dao.InMemoryDatabase;
import com.coursework.CourseWorkHorduz.models.Topic;
import com.coursework.CourseWorkHorduz.services.*;

import javax.servlet.*;
import javax.servlet.annotation.*;

@WebListener
public class ApplicationContextListener implements ServletContextListener {


    @Override
    public void contextInitialized(ServletContextEvent sce) {
        InMemoryDatabase database = new InMemoryDatabase();
        database.getTopics().put(1, new Topic(1, "CourseWork", "How to do this f*cking coursework?"));
        InMemoryDaoFactory daoFactory = database.getDaoFactory();

        UserService userService = new UserService(daoFactory);
        TopicService topicService = new TopicService(daoFactory);

        sce.getServletContext().setAttribute("userService", userService);
        sce.getServletContext().setAttribute("topicService", topicService);

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        /* This method is called when the servlet Context is undeployed or Application Server shuts down. */
    }



}
