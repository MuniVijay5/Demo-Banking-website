package com.bank.controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;

import com.Model.Model;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpSession;


@WebServlet("/ChangePassword")
public class ChangePassword extends HttpServlet {
	protected void service(jakarta.servlet.http.HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String pwd =request.getParameter("npwd");
		HttpSession session = request.getSession();
		
		int Account_Number = (int)session.getAttribute("accno");
		
		try {
			Model m =new Model();
			m.setAccount_Number(Account_Number);
			m.setPassword(pwd);
			boolean b = m.changepwd();
			if(b==true) {
				response.sendRedirect("/BankApllication/ChangepwdSuccess.html");
			}
			else {
				response.sendRedirect("/BankApllication/ChangepwdFail.html");			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
