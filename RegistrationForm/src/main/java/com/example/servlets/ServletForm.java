package com.example.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Servlet implementation class ServletForm
 */
public class ServletForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletForm() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */

//    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");


        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>User Data</h1>");
        out.println("</body></html>");


       
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    	String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        String jdbcUrl = "jdbc:oracle:thin:@localhost:1521/XE";
        String user = "system";
        String pass = "Nandini9271";
        Connection connection = null;
        try {
            // Load Oracle JDBC driver
            Class.forName("oracle.jdbc.driver.OracleDriver");

            // Create a connection to the Oracle database
            
            connection = DriverManager.getConnection(jdbcUrl, user, pass);
            // SQL Insert Query
            String query = "INSERT INTO abcd (username, email, password) VALUES (?,?,?)";

            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, username);
         	pstmt.setString(2, email);
            pstmt.setString(3, password);
            pstmt.executeUpdate();

            // Close resources
            pstmt.close();
            connection.close();

            response.getWriter().write("Registration Successful!");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            response.getWriter().write("Error: Registration failed.");
        }
    }
}