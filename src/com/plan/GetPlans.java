package com.plan;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.time.format.DateTimeFormatter;

@WebServlet(name="getPlans",value="/getPlans")
public class GetPlans extends HttpServlet {

    private PlanService planService;

    static final Logger logger = LoggerFactory.getLogger(GetPlans.class);

    static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddhh24mm");

}
