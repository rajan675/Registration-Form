package com.company;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.cj.Session;
import com.mysql.cj.protocol.Resultset;

/**
 * Servlet implementation class Login
 */@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String pwd = request.getParameter("password");
		RequestDispatcher dispatcher = null;
		HttpSession session = request.getSession();
		try {
		 String DB_URL="jdbc:mysql://localhost:3306/registration";
   		 String USERNAME="root";
   		 String PASSWORD="12345";
   		Class.forName("com.mysql.jdbc.Driver");
   		//establish connection with DB
   		Connection con=DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
   		PreparedStatement ps = con.prepareStatement("select from users where Name=? and Password=? ");
   		ps.setString(1, name);
   		ps.setString(2, pwd);
   		ResultSet rs = ps.executeQuery();
   		if(rs.next()) {
   			session.getAttribute(rs.getString("name"));
   			dispatcher = request.getRequestDispatcher("index.jsp");
   		}else {
   			session.getAttribute("failed");
   			dispatcher = request.getRequestDispatcher("login.jsp");
   		}
   		dispatcher.forward(request, response);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
