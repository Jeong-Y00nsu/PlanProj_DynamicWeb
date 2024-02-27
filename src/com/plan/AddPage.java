package com.plan;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

@WebServlet(name="addPage",value="/addPage")
public class AddPage extends HttpServlet {

    static final Logger logger = LoggerFactory.getLogger(ModifyPage.class);

    static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddhh24mm");

    public AddPage(){
        super();
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        logger.debug("[AddPage][service] 추가 페이지 진입");
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("addPlan");
        requestDispatcher.forward(req,res);
    }
}
