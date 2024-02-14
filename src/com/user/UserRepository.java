package com.user;

import com.util.ObjectRepository;

import java.sql.ResultSet;
import java.sql.SQLException;

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
}
