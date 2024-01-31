package com.member;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;

public class MembersRepository {
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
	public List<Member> getAllMembers(){
		List<Member> members = new ArrayList<Member>();
		
		Connection conn = getConnection();
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String query ="SELECT * FROM Member ORDER BY id ASC";
		try {
			stmt= conn.prepareStatement(query);
			rs = stmt.executeQuery(query);
		
			while(rs.next()) {
				Member member = new Member();
				member.firstName=rs.getString("firstName");
				member.lastName=rs.getString("lastName");
				member.birth=rs.getString("birth");
				member.telephone_number=rs.getString("telephone_number");
				member.id=rs.getString("id");
				members.add(member);
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
		return members;
	}
	public List<Member> getMemberByFirstName(String firstName){
		List<Member> members = new ArrayList<Member>();
		
		Connection conn = getConnection();
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String query =String.format("SELECT * FROM Member WHERE firstName=\"%s\"ORDER BY id ASC",firstName);
		try {
			stmt= conn.prepareStatement(query);
			rs = stmt.executeQuery(query);
		
			while(rs.next()) {
				Member member = new Member();
				member.firstName=rs.getString("firstName");
				member.lastName=rs.getString("lastName");
				member.birth=rs.getString("birth");
				member.telephone_number=rs.getString("telephone_number");
				member.id=rs.getString("id");
				members.add(member);
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
		return members;
	}
	public List<Member> getMemberByLastName(String lastName){
		List<Member> members = new ArrayList<Member>();
		
		Connection conn = getConnection();
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String query =String.format("SELECT * FROM Member WHERE lastName=\"%s\"ORDER BY id ASC",lastName);
		try {
			stmt= conn.prepareStatement(query);
			rs = stmt.executeQuery(query);
		
			while(rs.next()) {
				Member member = new Member();
				member.firstName=rs.getString("firstName");
				member.lastName=rs.getString("lastName");
				member.birth=rs.getString("birth");
				member.telephone_number=rs.getString("telephone_number");
				member.id=rs.getString("id");
				members.add(member);
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
		return members;
	}
	public List<Member> getMemberByWholeName(String lastName, String firstName){
		List<Member> members = new ArrayList<Member>();
		
		Connection conn = getConnection();
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String query =String.format("SELECT * FROM Member WHERE lastName=\"%s\" AND firstName=\"%s\" ORDER BY id ASC",lastName,firstName);
		try {
			stmt= conn.prepareStatement(query);
			rs = stmt.executeQuery(query);
		
			while(rs.next()) {
				Member member = new Member();
				member.firstName=rs.getString("firstName");
				member.lastName=rs.getString("lastName");
				member.birth=rs.getString("birth");
				member.telephone_number=rs.getString("telephone_number");
				member.id=rs.getString("id");
				members.add(member);
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
		return members;
	}

	public String MakeCode() {
		String new_code = null;
		String lastest_code = null;
		
		Connection conn = getConnection();
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String query ="SELECT * FROM Member ORDER BY id DESC LIMIT 1";
		try {
			stmt= conn.prepareStatement(query);
			rs = stmt.executeQuery(query);
			if(rs.next()) {
				String firstName = null, lastName = null ,birth = null, telephone_number = null;
				firstName=rs.getString("firstName");
				lastName=rs.getString("lastName");
				birth =rs.getString("birth");
				telephone_number = rs.getString("telephone_number");
				lastest_code =rs.getString("id");
				
				int code_num = Integer.parseInt(lastest_code.substring(1)) + 1;
				new_code = "M"+String.format("%05d", code_num);
			}
			else {
				new_code ="M00001";
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		return new_code;
	}

	public void InsertMember(Member member) {
		Connection conn = null;
		PreparedStatement stmt = null;
		
		//member.setCode(MakeCode());
		try {
			String query =  String.format("INSERT INTO Member (firstName, lastName ,birth, telephone_number, id) VALUES (\"%s\" , \"%s\", \"%s\" , \"%s\", \"%s\" )",
					member.firstName,member.lastName, member.birth,member.telephone_number, MakeCode());
			conn = getConnection(); 
			stmt = conn.prepareStatement(query);
			
			int result = stmt.executeUpdate(query);
			if(result ==1) {
				System.out.println("Member ������ ���� ����");
			}
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("Member ������ ���� ����");
		} finally {
			try {
				if(stmt!=null&&!stmt.isClosed()) {
					stmt.close();
				} 
			}catch(Exception e1) {
				System.out.println("DataBase Not Closed");
				System.exit(0);
			}
			try {
				if(conn!=null) {
					conn.close();
				} 
			}catch(SQLException e1) {
				System.out.println("DataBase Not Disconnected");
				System.exit(0);
			}
		}
	}

	public void DeleteMember(String id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		
		String query ="DELETE from Member where id=?";
		System.out.println(id);
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(query);
			stmt.setString(1, id);
			
			int result = stmt.executeUpdate();
			if(result==1) {
				System.out.println("Member ������ ���� ����");
			}
		} catch(SQLException e) {
			System.out.println("Member ������ ���� ����");
			e.printStackTrace();
			
		} finally {
			try {
				if(stmt!=null&&!stmt.isClosed()) {
					stmt.close();
				}
			} catch (SQLException e) {
				System.out.println("DataBase Not Closed");
				System.exit(0);
			}
			try {
				if(conn!=null) {
					conn.close();
				} 
			}catch(SQLException e1) {
				System.out.println("DataBase Not Disconnected");
				System.exit(0);
			}
		}
	}
}
