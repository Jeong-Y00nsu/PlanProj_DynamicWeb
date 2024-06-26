package com.plan;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

@WebServlet(name="getPlan", value="/getPlan")
public class GetPlan extends HttpServlet {

    private PlanService planService;

    static final Logger logger = LoggerFactory.getLogger(GetPlan.class);

    static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddhh24mm");

    public GetPlan(){
        super();
        planService = new PlanService(new PlanRepository());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String planId = req.getParameter("planId");

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/plan.jsp");
        Plan plan = planService.getPlan(planId);
        req.setAttribute("plan",plan);
        requestDispatcher.forward(req,resp);
    }
}
