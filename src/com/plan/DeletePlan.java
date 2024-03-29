package com.plan;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="deletePlan", value="/deletePlan")
public class DeletePlan extends HttpServlet {

    private PlanService planService;

    static final Logger logger = LoggerFactory.getLogger(DeletePlan.class);

    public DeletePlan(){
        super();
        planService = new PlanService(new PlanRepository());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        planService.deletePlan(req.getParameter("id"));
        resp.sendRedirect("/getPlans");
    }
}
