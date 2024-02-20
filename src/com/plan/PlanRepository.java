package com.plan;

import com.util.ObjectRepository;

import java.sql.Date;
import java.sql.PreparedStatement;
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

    @Override
    protected PreparedStatement prepareInsertQuery(PreparedStatement stmt, Object object) throws SQLException{
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddhh24mm");
        Plan plan = (Plan)object;

        stmt.setString(1,plan.getId());
        stmt.setString(2,plan.getTitle());
        stmt.setString(3,plan.getText());
        stmt.setString(4,plan.getStartDt().format(formatter));
        stmt.setString(5,plan.getEndDt().format(formatter));

        return stmt;
    }

    @Override
    protected PreparedStatement prepareUpdateQuery(PreparedStatement stmt, Object object, String key) throws SQLException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddhh24mm");
        Plan plan = (Plan)object;

        stmt.setString(1,plan.getTitle());
        stmt.setString(2, plan.getText());
        stmt.setString(3,plan.getStartDt().format(formatter));
        stmt.setString(4, plan.getEndDt().format(formatter));
        stmt.setString(5, key);

        return stmt;
    }
}
