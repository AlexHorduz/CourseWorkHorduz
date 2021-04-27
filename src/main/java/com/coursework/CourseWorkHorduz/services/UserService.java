package com.coursework.CourseWorkHorduz.services;

import com.coursework.CourseWorkHorduz.dao.interfaces.IDaoFactory;
import com.coursework.CourseWorkHorduz.exceptions.*;
import com.coursework.CourseWorkHorduz.models.User;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UserService {
    private IDaoFactory daoFactory;

    public UserService(IDaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public String getHash(String password) throws NoSuchAlgorithmException {
        StringBuilder hash = new StringBuilder();
        MessageDigest sha = MessageDigest.getInstance("SHA-512");
        byte[] hashedBytes = sha.digest(password.getBytes());
        char[] digits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        for (int idx = 0; idx < hashedBytes.length; idx++) {
            byte b = hashedBytes[idx];
            hash.append(digits[(b & 0xf0) >> 4]);
            hash.append(digits[b & 0x0f]);
        }

        return hash.toString();
    }

    public User logIn(String login, String password)
            throws NoSuchUserException, IncorrectPasswordException, NoSuchAlgorithmException, NullPointerException {
        if (login == null) {
            throw new NullPointerException("Null passed as login");
        }

        if (password == null) {
            throw new NullPointerException("Null passed as password");
        }
        User user = daoFactory.getUserDao().getUserByLogin(login);
        if (user == null) {
            throw new NoSuchUserException("No user with login '" + login + "'");
        }

        if (!user.getPasswordHash().equals(getHash(password))) {
            throw new IncorrectPasswordException("Incorrect Password");
        }
        return user;
    }

    public void signUp(String login, String password, String confirmedPassword)
            throws FailedPasswordConfirmationException, UserAlreadyExistException, NoSuchAlgorithmException {

        if (daoFactory.getUserDao().getUserByLogin(login) != null) {
            throw new UserAlreadyExistException("User with login '" + login + "' already exist");
        }

        if (!password.equals(confirmedPassword)) {
            throw new FailedPasswordConfirmationException("Passwords don't match");
        }



        daoFactory.getUserDao().addUser(login, getHash(password));
    }
}
