package com.ComplainResolveService;

import java.sql.SQLException;
import java.util.Scanner;

public class AddComplainMain {
	public static void main(String[] args) {
		Complaint c = new Complaint();
		ComplaintDAO dao = new ComplaintDAO();
		Scanner sc = new Scanner(System.in);
		try {
			System.out.println("Enter ID Number");
			//String cid=dao.generateComplaintID();
			c.setComplaintid(sc.next());
			c.setCdescription("Input from Main");
			c.setComplainttype("Test");
			c.setSeverity("None");
			c.setStatus("Pending");
			 long millis=System.currentTimeMillis();  
		        java.sql.Date date=new java.sql.Date(millis);  
			c.setComplaintdate(date);
			System.out.println(dao.AddComplaint(c));
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
