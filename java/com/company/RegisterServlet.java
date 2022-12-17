package com.company;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doPost(HttpServlet request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String pwd = request.getParameter("password");
		String mobile = request.getParameter("contact");
		RequestDispatcher dispatcher = null;
		pw.print(name);
		pw.print(email);
		pw.print(pwd);
		pw.print(mobile);

         try {
        		 String DB_URL="jdbc:mysql://localhost:3306/registration";
        		 String USERNAME="root";
        		 String PASSWORD="12345";
        		Class.forName("com.mysql.jdbc.Driver");
        		//establish connection with DB
        		Connection con=DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
        		PreparedStatement ps = con.prepareStatement("insert into users(Name,Password,Email,Mobile) values(?,?,?,?) ");
        		ps.setString(1, name);
        		ps.setString(2, pwd);
        		ps.setString(3, email);
        		ps.setString(4, mobile);
        		int row = ps.executeUpdate();
        		dispatcher = request.getRequestDispatcher("registration.jsp");
        		if(row>0) {
        			request.setAttribute("status", "success");
        		}else {
        			request.setAttribute("status", "failed");

        		}
                dispatcher.forward(request, response);
         }catch(Exception e) {
        	 e.printStackTrace();
         }
	}

}
