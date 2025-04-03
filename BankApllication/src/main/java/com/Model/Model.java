package com.Model;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.tomcat.dbcp.dbcp2.DriverManagerConnectionFactory;

public class Model {
	private String Name;
	private String CustomerID;
	private int Account_Number;
	private String Password;
	private int Bank_Balance;
	private String EmailID;
	private int raccno;

	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet res;
	 public ArrayList al = new ArrayList();
	 public ArrayList sal = new ArrayList();
	 public ArrayList ral = new ArrayList();

	public String getName() {
		return Name;
	}
	public void setName(String name) {
		this.Name = name;
	}
	public String getCustomerID() {
		return CustomerID;
	}
	public void setCustomerID(String customerID) {
		this.CustomerID = customerID;
	}
	public int getAccount_Number() {
		return Account_Number;
	}
	public void setAccount_Number(int account_Number) {
		this.Account_Number = account_Number;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		this.Password = password;
	}
	public int getBank_Balance() {
		return Bank_Balance;
	}
	public void setBank_Balance(int bank_Balance) {
		this.Bank_Balance = bank_Balance;
	}
	public String getEmailID() {
		return EmailID;
	}
	public void setEmailID(String emailID) {
		this.EmailID = emailID;
	}
	public int getRaccno() {
		return raccno;
	}
	public void setRaccno(int raccno) {
		this.raccno= raccno;
	}

	public Model() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BankApplication","root","root123");
		System.out.println("driver is loaded ");
	}
	public boolean Register() throws Exception{
		String s = "insert into Our_Bank values(?,?,?,?,?,?)";
		pstmt = con.prepareStatement(s);
		pstmt.setString(1,Name);
		pstmt.setString(2,CustomerID);
		pstmt.setInt(3,Account_Number);
		pstmt.setString(4,Password);
		pstmt.setInt(5,Bank_Balance);
		pstmt.setString(6,EmailID);

		int x = pstmt.executeUpdate();
		if(x>0) {
			return true;
		}
		else {
			return false;
		}
	}
	public boolean login() throws SQLException {
		String s = "select * from Our_Bank where CustomerID=? and Password=?";
		pstmt =con.prepareStatement(s);
		pstmt.setString(1, CustomerID);
		pstmt.setString(2, Password);
		ResultSet res= pstmt.executeQuery();
		while(res.next()==true) {
			Account_Number=res.getInt("Account_Number");
			return true;
		}
		return false;
	}
	public boolean CheckBalance() throws SQLException {
		String s ="select Bank_Balance from Our_Bank where Account_Number=? ";
		pstmt =con.prepareStatement(s);
		pstmt.setInt(1,Account_Number );
		ResultSet res= pstmt.executeQuery();
		while(res.next()==true) {
			Bank_Balance = res.getInt("Bank_Balance");
			return true;
		}

		return false;
	}
	public boolean changepwd() throws SQLException {
		String s = "Update  Our_Bank set Password=? and Account_Number=?";
		pstmt =con.prepareStatement(s);
		pstmt.setString(1, Password);
		pstmt.setInt(2,Account_Number );
		int x =pstmt.executeUpdate();
		if(x>0) {
			return true;
		}
		return false;
	}
	public boolean transfer() throws SQLException {
		String s1="select * from Our_Bank where Account_Number=?";
		pstmt =con.prepareStatement(s1);
		pstmt.setInt(1, raccno);
		res =pstmt.executeQuery();
		while(res.next()==true) {
			String s2="update Our_Bank set Bank_Balance=Bank_Balance-? where Account_Number=?";
			pstmt = con.prepareStatement(s2);
			pstmt.setInt(1, Bank_Balance);
			pstmt.setInt(2, Account_Number);
		int y1=	pstmt.executeUpdate();
		if(y1>0) {
		    System.out.println("Inside while - 2");
				int x=	res.getInt("Balance");
				if(x>0) {
					String s3 ="update Our_Bank set Bank_Balance=Bank_Balance+? where Account_Number=?";
					pstmt = con.prepareStatement(s3);
					pstmt.setInt(1, Bank_Balance);
					pstmt.setInt(2, raccno);
				int y2 =	pstmt.executeUpdate();
					if(y2>0) {
						String s4="insert into GetStatement values(?,?,?)";
						pstmt=  con.prepareStatement(s4);
						pstmt.setInt(1, Account_Number);
						pstmt.setInt(2,raccno);
						pstmt.setInt(3,Bank_Balance);
						int y=  pstmt.executeUpdate();
						if(y>0) {
							return true;
						}

						else {
							return false;
						}
					}
				}
				else {
					return false;
				}
			}
		}
		return false;
	}
	public ArrayList getStatement() throws SQLException {
		String s ="select * from GetStatement where Account_Number=? ";
		pstmt =con.prepareStatement(s);
		pstmt.setInt(1, Account_Number);
		res =pstmt.executeQuery();
		
		while(res.next()==true) {
		sal.add(res.getInt("Accno"));
		ral.add(res.getInt("RACNNO"));
		al.add(res.getInt("Balnce"));
	}
	return al;
	}
	public boolean ApplyLoan() throws SQLException {
		String s ="select * from Our_Bank where Account_Number=? ";
		pstmt =con.prepareStatement(s);
		pstmt.setInt(1, Account_Number);
		res =pstmt.executeQuery();
		while(res.next()==true) {
		
			 Name =res.getString("Nmae");
			EmailID =res.getString("Email");
			return true;
		}
		return false;
	}
}