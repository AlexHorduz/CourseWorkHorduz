package com.coursework.CourseWorkHorduz.controllers;

import com.coursework.CourseWorkHorduz.exceptions.IncorrectPasswordException;
import com.coursework.CourseWorkHorduz.exceptions.NoSuchUserException;
import com.coursework.CourseWorkHorduz.models.User;
import com.coursework.CourseWorkHorduz.services.TopicService;
import com.coursework.CourseWorkHorduz.services.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;


@WebServlet(name = "FrontController", urlPatterns = {"/forum/*"})
public class FrontController extends HttpServlet {

    private UserService userService;
    private TopicService topicService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        userService = (UserService) config.getServletContext().getAttribute("userService");
        topicService = (TopicService) config.getServletContext().getAttribute("topicService");
    }

    public void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String pathInfo = request.getPathInfo();
        if (pathInfo == null) {
            pathInfo = "/";
        }
        try {
            switch (pathInfo) {
                case "/login":
                    logIn(request, response);
                    break;
                case "/signup":
                    //TODO
                    break;
                case "/comment":
                    //TODO
                    break;
                case "/addTopic":
                    //TODO
                    break;
                case "/deleteTopic":
                    //TODO
                    break;
                case "/editTopic":
                    //TODO
                    break;
                case "/topics":
                default:
                    topics(request, response);
                    break;
            }
        } catch (RuntimeException ex) {
            //error(request, response, "Oops, " + ex.getMessage());
        }
    }

    private void topics(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("topics", topicService.getAllTopics());
        request.getRequestDispatcher("/WEB-INF/jsp/topics.jsp").forward(request, response);
    }

    private void logIn(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().invalidate();
        String login = request.getParameter("login");
        if (login == null) {
            request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
            return;
        }
        String password = request.getParameter("password");
        try {
            request.getSession().setAttribute("user", userService.logIn(login, password));
        } catch (NoSuchUserException e) {
            request.setAttribute("alertMessage", "Please, enter an existing login");
        } catch (IncorrectPasswordException e) {
            request.setAttribute("alertMessage", "Password is incorrect");
        } catch (NoSuchAlgorithmException e) {
            //TODO redirect to undefined error page
        }


    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

}

