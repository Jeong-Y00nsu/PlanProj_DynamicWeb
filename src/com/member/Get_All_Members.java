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
 * Servlet implementation class Get_All_Members
 */
@WebServlet(name="get_all_members",value="/getAll")
public class Get_All_Members extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int i = 0;
		request.setCharacterEncoding("euc-kr");
		response.setContentType("text/html;charset=euc-kr");//
		MembersRepository members_repository = new MembersRepository();
		List<Member> members = members_repository.getAllMembers();

		PrintWriter writer = response.getWriter();

		if (members.size() != 0) {
			writer.println("<html><body><table><th>순서</th>");
			writer.println("<th>이름</th>");
			writer.println("<th>생년월일</th>");
			writer.println("<th>전화번호</th>");

			for (Member member : members) {
				writer.println("<tr><td>" + (++i) + "</td>");
				writer.println("<td>" + member.lastName +member.firstName + "</td>");
				writer.println("<td>" + member.birth + "</td>");
				writer.println("<td>" + member.telephone_number + "</td></tr>");
			}
			writer.println("</table></body></html>");
		} else
			writer.println("<html><body> DB is Empty! No one join this web page...</body></html>");
	}
}
