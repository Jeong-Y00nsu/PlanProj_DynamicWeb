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
import java.io.IOException;

@WebServlet(name="signUp", value="/signUp")
public class SignUp extends HttpServlet{

    private UserService userService;
    static final Logger logger = LoggerFactory.getLogger(SignUp.class);
    public SignUp() {
        super();
        userService = new UserService(new UserRepository());
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        User user = new User();
        user.setId(req.getParameter("id"));
        user.setPw(req.getParameter("pw"));
        user.setName(req.getParameter("name"));

        Response response = userService.signUp(user);
        if(response.getResult()== Result.OK){
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/signIn");
            requestDispatcher.forward(req,res);
        }else {
            req.setAttribute("response",response);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/signUp");
            requestDispatcher.forward(req,res);
        }
    }
}