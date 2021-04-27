package com.coursework.CourseWorkHorduz.dao;

import com.coursework.CourseWorkHorduz.dao.interfaces.IUserDao;
import com.coursework.CourseWorkHorduz.models.User;

import java.util.Collection;

public class InMemoryUserDao implements IUserDao {
    InMemoryDatabase database;
    Integer id;

    public InMemoryUserDao(InMemoryDatabase database) {
        this.database = database;
        this.id = 0;
    }

    @Override
    public User getUserById(Integer id)
    {
        return database.getUsers().get(id);
    }

    public User getUserByLogin(String login) {
        for (User user : database.getUsers().values()) {
            if (user.getLogin().equals(login))
                return user;
        }
        return null;
    }

    @Override
    public void addUser(String login, String passwordHash) {
        User user = new User(id, login, passwordHash, false);
        database.getUsers().put(id++, user);
    }

    @Override
    public Collection<User> getAllUsers() {
        return database.getUsers().values();
    }
}
