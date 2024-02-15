package com.plan;

import com.util.ObjectRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PlanRepository  extends ObjectRepository {

    public PlanRepository() {
        super("UserTable");
    }

    @Override
    protected Object translateToObject(ResultSet rs) throws SQLException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        Plan plan = new Plan();

        plan.setId(rs.getString("id"));
        plan.setTitle(rs.getString("title"));
        plan.setText(rs.getString("text"));
        plan.setStartDt(LocalDate.parse(rs.getString("startDt"),formatter));
        plan.setEndDt(LocalDate.parse(rs.getString("endDt"),formatter));

        return plan;
    }
}
