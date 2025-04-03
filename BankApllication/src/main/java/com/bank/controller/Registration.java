package com.bank.controller;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.Model.Model;

@WebServlet("/Registration")
public class Registration extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String Name = request.getParameter("name");
		String CustomerID = request.getParameter("Custid");
		String s_Account_Number = request.getParameter("AccNo");
		int Account_Number = Integer.parseInt(s_Account_Number);
		String Password = request.getParameter("pwd");
		String s_Bank_Balance = request.getParameter("bal");
		int Bank_Balance = Integer.parseInt(s_Bank_Balance);
		String EmailID = request.getParameter("email");
		
		try {
			Model m = new Model();
			m.setName(Name);
			m.setCustomerID(CustomerID);
			m.setAccount_Number(Account_Number);
			m.setPassword(Password);
			m.setBank_Balance(Bank_Balance);
			m.setEmailID(EmailID);
			
			boolean b = m.Register();
			if(b==true) {
				response.sendRedirect("/BankApllication/Success.Reg.html");
			}
			else {
				response.sendRedirect("/BankApllication/FailureReg.html");
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}