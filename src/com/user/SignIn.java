package com.user;

import com.util.Response;
import com.util.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="signIn", value="/signIn")
public class SignIn extends HttpServlet {

    private UserService userService;
    static final Logger logger = LoggerFactory.getLogger(SignIn.class);

    public SignIn() {
        super();
        userService = new UserService(new UserRepository());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        User user = new User();
        user.setId(req.getParameter("id"));
        user.setPw(req.getParameter("pw"));

        Response response = userService.signIn(user);

        if(response.getResult().equals(Result.OK)){
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/plan");
            requestDispatcher.forward(req,resp);
        }else {
            req.setAttribute("response",response);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/signIn");
            requestDispatcher.forward(req,resp);
        }
    }
}
