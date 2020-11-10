package Tuan03.Bai07;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Servlet implementation class ServletUploadfileToDB
 */
@WebServlet("/servletUploadfileToDB")
public class ServletUploadfileToDB extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final int BUFFER_SIZE = 4096;
	private static final String SAVE_DIR = "images";

	private String dbURL = "jdbc:sqlserver://localhost:1433;databaseName=UploadFileToDB";
	private String dbUser = "sa";
	private String dbPass = "sapassword";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletUploadfileToDB() {
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
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		InputStream inputStream = null;

		Part filePart = request.getPart("photo");
		String fileUploadName = "";
		if (filePart != null) {
			fileUploadName = filePart.getName(); // filePart.getSize());
			inputStream = filePart.getInputStream();
		}
		Connection conn = null;
		String message = null;
		String filePath = "C:/" + fileUploadName + ".jpg";

		try {
			// connects to the database
			DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
			conn = DriverManager.getConnection(dbURL, dbUser, dbPass);

			String sql = "INSERT INTO Contact (first_name, last_name, photo) values (?, ?, ?)";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, firstName);
			statement.setString(2, lastName);
			statement.setBlob(3, inputStream);
//			if (inputStream != null) {
//				statement.setBlob(3, inputStream);
//			}
			int row = statement.executeUpdate(); // thực hiện lưu thông tin vào CSDL
			if (row > 0) {
				message = "File uploaded and saved into database";
			}

			String sql1 = "SELECT photo FROM Contact WHERE first_name=? AND last_name=?";
			statement = conn.prepareStatement(sql1);
			statement.setString(1, firstName);
			statement.setString(2, lastName);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				Blob blob = result.getBlob("photo");
				inputStream = blob.getBinaryStream();
				OutputStream outputStream = new FileOutputStream(filePath);
				int bytesRead = -1;
				byte[] buffer = new byte[BUFFER_SIZE];
				while ((bytesRead = inputStream.read(buffer)) != -1) {
					outputStream.write(buffer, 0, bytesRead);
				}
				inputStream.close();
				outputStream.close();
			}
		} catch (SQLException ex) {
			message = "ERROR: " + ex.getMessage();
			ex.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
			request.setAttribute("message", message);
			getServletContext().getRequestDispatcher("/messageServletUploadfileToDB").forward(request, response);
		}
	}
}
