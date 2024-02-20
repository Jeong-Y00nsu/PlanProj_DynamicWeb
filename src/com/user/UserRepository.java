package com.user;

import com.util.ObjectRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;

public class UserRepository extends ObjectRepository {


    public UserRepository() {
        super("UserTable");
    }

    @Override
    protected Object translateToObject(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getString("id"));
        user.setPw(rs.getString("pw"));
        user.setName(rs.getString("name"));
        user.setPlanId(rs.getString("planId"));
        user.setSalt(rs.getString("salt"));
        return user;
    }

    @Override
    protected PreparedStatement prepareInsertQuery(PreparedStatement stmt, Object object) throws SQLException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddhh24mm");
        User user = (User)object;

        stmt.setString(1,user.getId());
        stmt.setString(2,user.getPw());
        stmt.setString(3,user.getName());
        stmt.setString(4,user.getPlanId());
        stmt.setString(5,user.getSalt());

        return stmt;
    }

    @Override
    protected PreparedStatement prepareUpdateQuery(PreparedStatement stmt, Object object, String key) throws SQLException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddhh24mm");
        User user = (User)object;

        if(user.getPw()!=null && user.getName()==null){
            stmt.setString(1,user.getPw());
        } else if(user.getName()!=null && user.getPw() == null){
            stmt.setString(1,user.getName());
        }
        stmt.setString(2,user.getId());

        return stmt;
    }
}
