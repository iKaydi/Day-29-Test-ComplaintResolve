package com.ComplainResolveService;

import java.sql.SQLException;
import java.util.Scanner;

public class ResolveMain {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Resolve resolve = new Resolve();
		
		System.out.println("Enter Complaint ID");
		resolve.setComplaintid(sc.next());
		
		System.out.println("Enter Resolver");
		resolve.setResolvedby(sc.next());
		
		System.out.println("Enter Resolver Comments");
		resolve.setComments(sc.next());
		
		ComplaintDAO dao = new ComplaintDAO();
		try {
			System.out.println(dao.ResolveComplaint(resolve));
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
