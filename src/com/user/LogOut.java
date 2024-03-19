package com.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name="logOut", value="/logOut")
public class LogOut extends HttpServlet {

    private UserService userService;

    static final Logger logger = LoggerFactory.getLogger(LogOut.class);

    public LogOut(){
        super();
        userService = new UserService(new UserRepository());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();

        session.invalidate();

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/signIn.jsp");
    }
}
