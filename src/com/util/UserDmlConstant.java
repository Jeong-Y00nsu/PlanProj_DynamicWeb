package com.util;

public class UserDmlConstant {

    public static final String SELECT_USER_BY_ID = "SELECT * FROM UserTable WHERE id= \"%s\" AND pw= \"%s\" ";

    public static final String INSERT_USER = "INSERT INTO UserTable(id, pw, name, planId, salt) VALUES (\"%s\",\"%s\",\"%s\",\"%s\",\"%s\")";

    public static final String DELETE_USER_BY_ID = "DELETE FROM UserTable WHERE id= \"%s\"";

    public static final String UPDATE_USERNAME_BY_ID = "UPDATE UserTable SET name = \"%s\" WHERE id = \"%s\"";

    public static final String UPDATE_USERPW_BY_ID = "UPDATE UserTable SET pw = \"%s\" WHERE id = \"%s\"";
}
