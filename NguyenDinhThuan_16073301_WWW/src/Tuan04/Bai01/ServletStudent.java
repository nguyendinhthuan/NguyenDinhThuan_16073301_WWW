package Tuan04.Bai01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletStudent
 */
@WebServlet("/servletStudent")
public class ServletStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletStudent() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String fname = request.getParameter("firstname");
		String lname = request.getParameter("lastname");
		String dateofbirth = request.getParameter("dateofbirth");
		String email = request.getParameter("email");
		String mobileNum = request.getParameter("mobilenumber");
		String gender = request.getParameter("gender");
		String address = request.getParameter("address");
		String city = request.getParameter("city");
		String pinCode = request.getParameter("pincode");
		String state = request.getParameter("state");
		String country = request.getParameter("country");
		String hobbies = request.getParameter("hobbies");
		String course = request.getParameter("course");
		
		Student sv = new Student();
		sv.setFirstname(fname);
		sv.setLastname(lname);
		sv.setEmail(email);
		sv.setGender(gender);
		sv.setDay(dateofbirth);
		sv.setHobbies(hobbies);

		request.setAttribute("student", sv);
		RequestDispatcher rd = request.getRequestDispatcher("Tuan04_Bai01_ResultFormStudent.jsp");
		rd.forward(request, response);
	}
}
