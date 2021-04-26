package com.coursework.CourseWorkHorduz.dao;

import com.coursework.CourseWorkHorduz.dao.interfaces.*;


public class InMemoryDaoFactory implements IDaoFactory{
    private IUserDao userDao;
    private ITopicDao topicDao;
    private ICommentDao commentDao;

    public InMemoryDaoFactory(InMemoryDatabase database) {
        this.userDao = new InMemoryUserDao(database);
        this.topicDao = new InMemoryTopicDao(database);
        this.commentDao = new InMemoryCommentDao(database);
    }

    @Override
    public IUserDao getUserDao() {
        return userDao;
    }

    @Override
    public ITopicDao getTopicDao() {
        return topicDao;
    }

    @Override
    public ICommentDao getCommentDao() {
        return commentDao;
    }
}
