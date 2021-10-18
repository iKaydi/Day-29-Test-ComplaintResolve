package com.ComplainResolveService;

import java.sql.SQLException;

public class ShowAllResolve {
	public static void main(String[] args) {
		ComplaintDAO dao = new ComplaintDAO();
		try {
			Resolve[] r= dao.ShowAllResolve();
			for (Resolve resolve : r) {
				System.out.println(resolve);
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
