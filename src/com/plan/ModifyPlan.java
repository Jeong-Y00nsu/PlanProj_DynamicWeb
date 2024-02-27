package com.plan;

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
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@WebServlet(name="modifyPlan",value="/modifyPlan")
public class ModifyPlan extends HttpServlet {

    private PlanService planService;

    static final Logger logger = LoggerFactory.getLogger(ModifyPlan.class);

    static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddhh24mm");

    public ModifyPlan(){
        super();
        planService = new PlanService(new PlanRepository());
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        Plan plan = new Plan();
        plan.setId(req.getParameter("id"));
        plan.setTitle(req.getParameter("title"));
        plan.setText(req.getParameter("text"));
        plan.setStartDt(LocalDate.parse(req.getParameter("startDt"),dtf));
        plan.setEndDt(LocalDate.parse(req.getParameter("endDt"),dtf));

        Response response = planService.updatePlan(plan, plan.getId());

        if(response.getResult().equals(Result.OK)){
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("plan");
            requestDispatcher.forward(req,res);
        } else if(response.getResult().equals(Result.INVALID_PARAM)){
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("updatePlan");
            HttpServletRequest request = (HttpServletRequest) req;
            request.setAttribute("result",response.getResult());
            requestDispatcher.forward(request,res);
        }else{
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("updatePlan");
            requestDispatcher.forward(req,res);
            HttpServletRequest request = (HttpServletRequest) req;
            request.setAttribute("result","다시 시도해주세요.");
            requestDispatcher.forward(request,res);
        }
    }
}
