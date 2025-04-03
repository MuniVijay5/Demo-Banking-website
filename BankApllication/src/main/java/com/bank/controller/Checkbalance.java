package com.bank.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.Model.Model;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpSession;

@WebServlet("/Checkbalance")
public class Checkbalance extends HttpServlet {
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	HttpSession session =request.getSession();
	 int  Account_Number=(int)session.getAttribute("accno");
	 
	 try {
		 Model m = new Model();
		 m.setAccount_Number(Account_Number);
		
		 boolean b = m.CheckBalance();
		 if(b==true) {
			 session.setAttribute("bal",m.getBank_Balance());
			 response.sendRedirect("/BankApllication/BalanceView.jsp");
		 }
		 else {
			 response.sendRedirect("/BankApllication/BalanceFail.");

		 }
	 }
	 catch (Exception e) {
		e.printStackTrace();
	}
	}

}
