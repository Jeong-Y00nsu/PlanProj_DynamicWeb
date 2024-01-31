package com.member;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Find_Member_ByName
 */
@WebServlet(name="find_member",value="/find")
public class Find_Member_ByName extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int i = 0;
		response.setContentType("text/html;charset=euc-kr");//
		request.setCharacterEncoding("euc-kr");

		String lastName = request.getParameter("find_lastName");
		String firstName =  request.getParameter("find_firstName");
		MembersRepository members_repository = new MembersRepository();
		
		List<Member> members;
		if (lastName != "" && firstName != "") {
			members = members_repository.getMemberByWholeName(lastName,firstName);
		} else if(lastName==""&&firstName!="") {
			members = members_repository.getMemberByFirstName(firstName);
		} else if(lastName!=""&&firstName=="") {
			members= members_repository.getMemberByLastName(lastName);
		} else {
			System.err.println("[ERROR] There is no input value. <Find_Member_ByName.java line 49>");
			return;
		}
		
		PrintWriter writer = response.getWriter();

		if (members.size() != 0) {
			writer.println("<html><body><table><th>����</th>");
			writer.println("<th>�̸�</th>");
			writer.println("<th>�������</th>");
			writer.println("<th>��ȭ��ȣ</th>");

			for (Member member : members) {
				writer.println("<tr><td>" + (++i) + "</td>");
				writer.println("<td>" + member.lastName +member.firstName + "</td>");
				writer.println("<td>" + member.birth + "</td>");
				writer.println("<td>" + member.telephone_number + "</td></tr>");
			}
			writer.println("</table></body></html>");
		} else
			writer.println("<html><body> " + lastName + firstName + " is not member of webpage.</body></html>");
	}

}
