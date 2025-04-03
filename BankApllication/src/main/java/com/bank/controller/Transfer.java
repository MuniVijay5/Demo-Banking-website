package com.bank.controller;

import java.io.IOException;

import com.Model.Model;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/Transfer")
public class Transfer extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session =request.getSession();
		int Account_Number = (int) session.getAttribute("Accno");
		String samt = request.getParameter("amt");
		
		int amt= Integer.parseInt(samt);
		
String sraccno = request.getParameter("raccno");
		
		int raccno= Integer.parseInt(sraccno);
		
		try {
			Model m = new Model();
			m.setAccount_Number(Account_Number);
			m.setRaccno(raccno);
			m.setBank_Balance(amt);
			 boolean b =m.transfer();
			 if(b==true) {
				 response.sendRedirect("/BankApplication/TransferSuccess.html");
			 }
			 else {
				 response.sendRedirect("/BankApplication/TransferFail.html");
			 }
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}

}
