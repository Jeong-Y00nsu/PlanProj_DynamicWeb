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
import java.util.List;

@WebServlet(name="getDailyPlan",value="/getDailyPlan")
public class GetDailyPlan extends HttpServlet {
    private PlanService planService;

    static final Logger logger = LoggerFactory.getLogger(ModifyPlan.class);

    static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddhh24mm");

    public GetDailyPlan(){
        super();
        planService = new PlanService(new PlanRepository());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String year = req.getParameter("year");
        String month = req.getParameter("month");
        String day = req.getParameter("day");

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/plan.jsp");
        List<Plan> plans = planService.getDailyPlan(year,month,day);
        HttpServletRequest request = (HttpServletRequest) req;
        request.setAttribute("plans",plans);
        requestDispatcher.forward(request,resp);
    }
}
