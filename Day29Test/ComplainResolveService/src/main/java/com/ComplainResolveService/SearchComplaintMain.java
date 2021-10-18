package com.ComplainResolveService;

import java.sql.SQLException;

public class SearchComplaintMain {

	public static void main(String[] args) {
		
		ComplaintDAO dao = new ComplaintDAO();
		try {
			Complaint c = dao.SearchComplaint(2);
			System.out.println(c);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
