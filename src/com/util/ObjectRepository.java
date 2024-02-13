package com.util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class ObjectRepository {
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

    public List<Object> selectAll(String query){
        List<Object> results = new ArrayList<>();

        Connection conn = getConnection();

        PreparedStatement stmt = null;
        ResultSet rs = null;

        try{
            stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery(query);

            while(rs.next()){
                results.add(translateToObject());
            }
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            try {
                if(rs!=null )
                    rs.close();
                if(stmt!=null)
                    stmt.close();
                if( conn!=null)
                    conn.close();
            } catch(SQLException e) {
                e.printStackTrace();
            }
        }
        return results;
    }

    protected abstract Object translateToObject();

    public List<Object> selectByElement(String query, String element){
        List<Object> results = new ArrayList<>();
        Connection conn = getConnection();

        PreparedStatement stmt = null;
        ResultSet rs = null;

        String q = String.format(query,element);
        try{
            stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery();

            while(rs.next()){
                results.add(translateToObject());
            }
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if(rs!=null )
                    rs.close();
                if(stmt!=null)
                    stmt.close();
                if( conn!=null)
                    conn.close();
            } catch(SQLException e) {
                e.printStackTrace();
            }
        }
        return results;
    }

}
