package com.plan;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

@WebServlet(name="modifyPage",value="/modifyPage")
public class ModifyPage extends HttpServlet {

    private PlanService planService;
    static final Logger logger = LoggerFactory.getLogger(ModifyPage.class);

    static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddhh24mm");

    public ModifyPage(){
        super();
        planService = new PlanService(new PlanRepository());
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        logger.debug("[ModifyPage][service] 수정 페이지 진입");
        RequestDispatcher requestDispatcher =  req.getRequestDispatcher("updatePlan");

        Plan plan = planService.getPlan(req.getParameter("id"));
        HttpServletRequest request = (HttpServletRequest) req;
        request.setAttribute("plan",plan);//

        requestDispatcher.forward(request,res);
    }
}
