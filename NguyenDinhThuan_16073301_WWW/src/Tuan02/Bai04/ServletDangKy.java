package Tuan02.Bai04;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletDangKy
 */
@WebServlet("/servletDangKy")
public class ServletDangKy extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletDangKy() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String facebook = request.getParameter("facebook");
		String shortbio = request.getParameter("shortbio");

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		out.println("<html>");
		out.println("<head><title>Thông tin</title></head>");
		out.println("<body>");
		out.println("<p> First Name: " + firstname + "</p>");
		out.println("<p> Last Name: " + lastname + "</p>");
		out.println("<p> User Name: " + username + "</p>");
		out.println("<p> Password: " + password + "</p>");
		out.println("<p> Facebook: " + facebook + "</p>");
		out.println("<p> Short Bio: " + shortbio + "</p>");
		out.println("</body>");
		out.println("</html>");
	}

}
