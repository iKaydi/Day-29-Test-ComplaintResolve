package com.ComplainResolveService;

import java.sql.SQLException;

public class ShowAllComaplainMain {

	public static void main(String[] args) {
		ComplaintDAO dao = new ComplaintDAO();
		try {
			Complaint[] carr = dao.ShowAllComplaint();
			for (Complaint complaint : carr) {
				System.out.println(complaint);
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
