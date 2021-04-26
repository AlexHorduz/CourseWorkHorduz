package com.coursework.CourseWorkHorduz.dao.interfaces;

public interface IDaoFactory {

    public IUserDao getUserDao();

    public ITopicDao getTopicDao();

    public ICommentDao getCommentDao();
}
