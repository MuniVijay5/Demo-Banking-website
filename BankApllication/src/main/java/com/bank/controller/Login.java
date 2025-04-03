package com.bank.controller;

import java.io.IOException;

import com.Model.Model;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
@WebServlet("/Login")
public class Login extends HttpServlet {

	private HttpSession session;

	protected void service (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String CustomerID = request.getParameter("Custid");
		String Password = request.getParameter("pwd");
		session =request.getSession(true);
		System.out.println("mg");
		 try {
			 Model m = new Model();
			 m.setCustomerID(CustomerID);
			 m.setPassword(Password);
			 boolean b = m.login();
			 if(b==true) {
				 session.setAttribute("accno",m.getAccount_Number());
				 response.sendRedirect("/BankApllication/Home.html");
			 }
			 else {
				 response.sendRedirect("/BankApllication/Error.html");
			 }
		 }
		 catch (Exception e) {
			e.printStackTrace();
		}

	}

}
