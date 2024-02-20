package com.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class ObjectRepository {

    static final Logger logger = LoggerFactory.getLogger(ObjectRepository.class);

    private static String dbTableName;

    public ObjectRepository(String dbTableName){
        this.dbTableName = dbTableName;
    }

    public static Connection getConnection() {
        Connection conn = null;
        String url = "jdbc:mysql://localhost/"+dbTableName;//jdbc:mysql://192.168.200.166:3306/CLIENT
        String id = "root";
        String pw = "Zone@0225#mysql";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn =  DriverManager.getConnection(url, id, pw);
        } catch (Exception e) {

            e.printStackTrace();
            logger.info("Members_DAO.getConnection() : {}", e.toString());

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
                results.add(translateToObject(rs));
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

    protected abstract Object translateToObject(ResultSet rs) throws SQLException;

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
                results.add(translateToObject(rs));
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

    public void insertElement(String query, Object element){
        Connection conn = null;
        PreparedStatement stmt = null;

        try{
            conn = getConnection();
            stmt = conn.prepareStatement(query);
            stmt = prepareInsertQuery(stmt,element);

            int result = stmt.executeUpdate(query);
            if (result > 0) logger.info("insert success");
            else logger.info("insert fail");
        } catch ( Exception e){
            e.printStackTrace();
            logger.info("Insert Fail");
        } finally{
            try {
                if(stmt!=null&&!stmt.isClosed()) {
                    stmt.close();
                }
            }catch(Exception e1) {
                logger.info("DataBase Not Closed");
                System.exit(0);
            }
            try {
                if(conn!=null) {
                    conn.close();
                }
            }catch(SQLException e1) {
                logger.info("DataBase Not Disconnected");
                System.exit(0);
            }
        }
    }

    protected abstract PreparedStatement prepareInsertQuery(PreparedStatement stmt, Object object) throws SQLException;

    public void deleteElement(String query, String key){
        Connection conn = null;
        PreparedStatement stmt = null;

        logger.debug("delete element = {}",key);

        try{
            conn = getConnection();
            stmt = conn.prepareStatement(query);
            stmt.setString(1,key);

            int result = stmt.executeUpdate();
            if (result > 0) logger.info("delete success");
            else logger.info("delete fail");
        } catch (SQLException e){
            logger.info("Delete fail");
            e.printStackTrace();
        } finally{
            try {
                if(stmt!=null&&!stmt.isClosed()) {
                    stmt.close();
                }
            } catch (SQLException e) {
                logger.info("DataBase Not Closed");
                System.exit(0);
            }
            try {
                if(conn!=null) {
                    conn.close();
                }
            }catch(SQLException e1) {
                logger.info("DataBase Not Disconnected");
                System.exit(0);
            }
        }
    }

    public void updateElement(String query, Object element, String key){
        Connection conn = null;
        PreparedStatement stmt = null;

        try{
            conn = getConnection();
            stmt = conn.prepareStatement(query);
            stmt = prepareUpdateQuery(stmt,element,key);

            int result = stmt.executeUpdate(query);
            if (result > 0) logger.info("update success");
            else logger.info("update fail");
        } catch ( Exception e){
            e.printStackTrace();
            logger.info("update Fail");
        } finally{
            try {
                if(stmt!=null&&!stmt.isClosed()) {
                    stmt.close();
                }
            }catch(Exception e1) {
                logger.info("DataBase Not Closed");
                System.exit(0);
            }
            try {
                if(conn!=null) {
                    conn.close();
                }
            }catch(SQLException e1) {
                logger.info("DataBase Not Disconnected");
                System.exit(0);
            }
        }
    }

    protected abstract PreparedStatement prepareUpdateQuery(PreparedStatement stmt, Object object, String key) throws SQLException;
}
