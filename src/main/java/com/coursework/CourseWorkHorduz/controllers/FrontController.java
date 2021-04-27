package com.coursework.CourseWorkHorduz.controllers;

import com.coursework.CourseWorkHorduz.exceptions.FailedPasswordConfirmationException;
import com.coursework.CourseWorkHorduz.exceptions.IncorrectPasswordException;
import com.coursework.CourseWorkHorduz.exceptions.NoSuchUserException;
import com.coursework.CourseWorkHorduz.exceptions.UserAlreadyExistException;
import com.coursework.CourseWorkHorduz.models.Topic;
import com.coursework.CourseWorkHorduz.models.User;
import com.coursework.CourseWorkHorduz.services.TopicService;
import com.coursework.CourseWorkHorduz.services.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;


@WebServlet(name = "FrontController", urlPatterns = {"/forum/*"})
public class FrontController extends HttpServlet {

    private UserService userService;
    private TopicService topicService;

    @Override
    public void init(ServletConfig config)  {
        userService = (UserService) config.getServletContext().getAttribute("userService");
        topicService = (TopicService) config.getServletContext().getAttribute("topicService");
    }

    public void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String pathInfo = request.getPathInfo();
        if (pathInfo == null) {
            pathInfo = "";
        }
        try {
            switch (pathInfo) {
                case "/login":
                    logIn(request, response);
                    break;
                case "/logout":
                    logOut(request, response);
                    break;
                case "/signup":
                    signUp(request, response);
                    break;
                case "/topic":
                    topic(request, response);
                    break;
                case "/comment":
                    comment(request, response);
                    break;
                case "/addTopic":
                    addTopic(request, response);
                    break;
                case "/deleteTopic":
                    deleteTopic(request, response);
                    break;
                case "/editTopic":
                    editTopic(request, response);
                    break;
                case "/topics":
                    topics(request, response);
                    break;
                case "":
                    response.sendRedirect("/forum/topics");
                    break;
                default:
                    response.sendRedirect("./topics");
                    break;
            }
        } catch (RuntimeException ex) {
            error(request, response);
        }
    }

    private void error(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/html/error.html").forward(request, response);
    }

    private void editTopic(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String idParam = request.getParameter("id");
        User user = (User) request.getSession().getAttribute("user");
        if (user != null && user.getIsAdmin()) {
            int id;
            try {
                id = Integer.parseInt(idParam);
            } catch (NumberFormatException e) {
                response.sendRedirect("topics");
                return;
            }
            Topic topic = topicService.getTopicById(id);
            if (topic == null) {
                response.sendRedirect("topics");
                return;
            }


            String title = request.getParameter("title");
            if (title == null || title == "") {
                request.setAttribute("topic", topic);
                request.getRequestDispatcher("/WEB-INF/jsp/editTopic.jsp").forward(request, response);
                return;
            }

            String descr = request.getParameter("description");
            topicService.updateTopic(topic, title, descr);
        }
        response.sendRedirect("topics");

    }

    private void addTopic(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        User user = (User) request.getSession().getAttribute("user");
        if (user != null && user.getIsAdmin()) {
            if (title == null || title == "") {
                request.getRequestDispatcher("/WEB-INF/jsp/addTopic.jsp").forward(request, response);
            } else {
                String descr = request.getParameter("description");
                if (descr == null) {
                    descr = "";
                }
                topicService.addTopic(title, descr);
                response.sendRedirect("topics");
            }
        } else {
            response.sendRedirect("topics");
        }
    }

    private void deleteTopic(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String idParam = request.getParameter("id");
        User user = (User) request.getSession().getAttribute("user");
        if (idParam == null || user == null || !user.getIsAdmin()) {
            response.sendRedirect("topics");
            return;
        }
        int id;
        try {
            id = Integer.parseInt(idParam);
        } catch (NumberFormatException e) {
            response.sendRedirect("topics");
            return;
        }
        Topic topic = topicService.getTopicById(id);
        if (topic == null) {
            response.sendRedirect("topics");
            return;
        }
        topicService.deleteTopic(topic);
        topicService.getAllTopics().forEach(topic1 -> System.err.println(topic1.getTitle()));
        response.sendRedirect("topics");
    }

    private void comment(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String idParam = request.getParameter("id");
        String text = request.getParameter("text");
        User user = (User) request.getSession().getAttribute("user");
        if (idParam == null || text == null || text == "" || user == null) {
            response.sendRedirect("topics");
            return;
        }
        int id;
        try {
            id = Integer.parseInt(idParam);
        } catch (NumberFormatException e) {
            response.sendRedirect("topics");
            return;
        }

        Topic topic = topicService.getTopicById(id);
        if (topic == null) {
            response.sendRedirect("topics");
            return;
        }

        topicService.addComment(topic, user, text);
        response.sendRedirect("topic?id=" + topic.getId());
    }

    private void topic(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String idParam = request.getParameter("id");
        if (idParam == null) {
            response.sendRedirect("topics");
            return;
        }
        int id;
        try {
            id = Integer.parseInt(idParam);
        } catch (NumberFormatException e) {
            response.sendRedirect("topics");
            return;
        }

        Topic topic = topicService.getTopicById(id);
        if (topic == null) {
            response.sendRedirect("topics");
            return;
        }
        request.setAttribute("topic", topic);
        request.setAttribute("comments", topic.getComments());
        request.getRequestDispatcher("/WEB-INF/jsp/topic.jsp").forward(request, response);
    }

    private void logOut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().invalidate();
        response.sendRedirect("./topics");
    }

    private void topics(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String searchText = request.getParameter("searchText");
        if (searchText == null) {
            request.setAttribute("topics", topicService.getAllTopics());
        } else {
            Collection<Topic> topics = topicService.searchByText(searchText);
            request.setAttribute("topics", topics);
        }
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
            request.setAttribute("errorMessage", "Please, enter an existing login");
            request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
            return;
        } catch (IncorrectPasswordException e) {
            request.setAttribute("errorMessage", "Password is incorrect");
            request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
            return;
        } catch (NoSuchAlgorithmException e) {
            error(request, response);
        }

        response.sendRedirect("./topics");
    }

    private void signUp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().invalidate();
        String login = request.getParameter("login");
        if (login == null) {
            request.getRequestDispatcher("/WEB-INF/jsp/signup.jsp").forward(request, response);
            return;
        }
        String password1 = request.getParameter("password1");
        String password2 = request.getParameter("password2");

        try {
            userService.signUp(login, password1, password2);
        } catch (FailedPasswordConfirmationException e) {
            request.setAttribute("errorMessage", "Passwords don't match");
            request.getRequestDispatcher("/WEB-INF/jsp/signup.jsp").forward(request, response);
            return;
        } catch (UserAlreadyExistException e) {
            request.setAttribute("errorMessage", "Login already taken");
            request.getRequestDispatcher("/WEB-INF/jsp/signup.jsp").forward(request, response);
            return;
        } catch (NoSuchAlgorithmException e) {
            error(request, response);
            return;
        }
        request.setAttribute("greeting", "You have been registered! Now log in.");
        request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
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

