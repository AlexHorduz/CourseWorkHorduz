package com.coursework.CourseWorkHorduz.dao.interfaces;
import com.coursework.CourseWorkHorduz.models.User;

import java.util.Collection;

public interface IUserDao {
    public Collection<User> getAllUsers();

    public User getUserById(Integer id);

    public User getUserByLogin(String login);

    public void addUser(String login, String passwordHash);
}
