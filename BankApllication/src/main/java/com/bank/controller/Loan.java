package com.bank.controller;

import java.io.IOException;

import com.Model.Model;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


public class Loan extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session =request.getSession();
		int Account_Number = (int) session.getAttribute("accno");

		try {
			Model m = new Model();
			m.setAccount_Number(Account_Number);
			boolean b = m.ApplyLoan();
			if(b==true) {
				session.setAttribute("Name", m.getName());
				session.setAttribute("email", m.getEmailID());
				response.sendRedirect("/BnakApplicatin/LoanSuccess.jsp");
			}
			else {
				response.sendRedirect("/BnakApplicatin/LoanFail.html");
			}
		}
		catch (Exception e) {
          e.printStackTrace();
		}
	}

}
