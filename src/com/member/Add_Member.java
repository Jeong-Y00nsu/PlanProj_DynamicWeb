package com.member;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="add_member",value="/add")
public class Add_Member extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Member new_member = new Member();
		new_member.firstName = request.getParameter("firstName");
		new_member.lastName = request.getParameter("lastName");
		new_member.birth = request.getParameter("birth");
		new_member.telephone_number = request.getParameter("telephone_number");

		MembersRepository members_repository = new MembersRepository();
		members_repository.InsertMember(new_member);
		
		if(new_member.firstName!=null&&new_member.lastName!=null) {
			response.setContentType("text/html;charset=euc-kr");//
			request.setCharacterEncoding("euc-kr");
			PrintWriter writer = response.getWriter();
			writer.println("<html><body> Adding \'"+new_member.lastName+new_member.firstName+"\' success. </body></html>");
		}	
	}
}
