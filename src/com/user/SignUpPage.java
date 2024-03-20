package com.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="signUpPage", value="/signUpPage")
public class SignUpPage extends HttpServlet {

    static final Logger logger = LoggerFactory.getLogger(SignUpPage.class);

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/SignUp.jsp");
        requestDispatcher.forward(req, resp);
    }
}
