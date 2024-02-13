package com.user;

import java.sql.Connection;
import java.sql.DriverManager;

public class UserRepository {
    public static Connection getConnection() {
        Connection conn = null;
        String url = "jdbc:mysql://localhost/Members";//jdbc:mysql://192.168.200.166:3306/CLIENT
        String id = "root";
        String pw = "Zone@0225#mysql";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn =  DriverManager.getConnection(url, id, pw);
        } catch (Exception e) {

            e.printStackTrace();
            System.out.println("Members_DAO.getConnection() : " + e.toString());

            return null;
        }
        return conn;
    }


}
