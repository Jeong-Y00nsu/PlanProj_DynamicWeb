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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@WebServlet(name="getMonthlyPlan",value="/getMonthlyPlan")
public class GetMonthlyPlan extends HttpServlet {

    private PlanService planService;

    static final Logger logger = LoggerFactory.getLogger(GetMonthlyPlan.class);

    static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddhh24mm");

    public GetMonthlyPlan(){
        super();
        planService = new PlanService(new PlanRepository());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        logger.debug("[GetPlans][service] 일정 관리 메인 페이지");
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/plan.jsp");//

        String year = req.getParameter("year");
        String month = req.getParameter("month");

        List<Map<LocalDate, List<Plan>>> planList = planService.getMonthlyPlan(year, month);
        req.setAttribute("planList",planList);

        requestDispatcher.forward(req,resp);
    }
}
