package com.bank.controller;

import java.io.IOException;
import java.util.ArrayList;

import com.Model.Model;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet("/GetStatement")
public class GetStatement extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session =request.getSession();
		int Account_Number =(int) session.getAttribute("AccNo");
		try {
			Model m= new Model();
			m.setAccount_Number(Account_Number);
			ArrayList al =m.getStatement();
			
			
			if(al.isEmpty()==true) {
				response.sendRedirect("/BankApplication/StatementFail.html");
			}
			else {
				session.setAttribute("sal", m.sal);
				session.setAttribute("ral", m.ral);
				session.setAttribute("al", m.al);
				response.sendRedirect("/BankApplication/StatementSuccess.jsp");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
