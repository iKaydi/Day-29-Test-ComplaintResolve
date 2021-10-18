package com.ComplainResolveService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ComplaintDAO {

	Connection con;
	PreparedStatement pst;
	
	public int generateComplaintID() throws ClassNotFoundException, SQLException
	{
		con=ConnectionHelper.getConnection();
		String cmd = "select max(complaintid)+1 cid from Complaint";
		pst=con.prepareStatement(cmd);
		ResultSet rs = pst.executeQuery();
		rs.next();
		int cid = rs.getInt("cid");
		return cid;
	}
	
	public String AddComplaint(Complaint complaint) throws ClassNotFoundException, SQLException
	{
		con=ConnectionHelper.getConnection();
		String cmd = "insert into complaint values(?,?,?,?,?,?)";
		pst= con.prepareStatement(cmd);
		pst.setString(1, complaint.getComplaintid());
		pst.setString(2, complaint.getComplainttype());
		pst.setString(3, complaint.getCdescription());
		
		long millis=System.currentTimeMillis();  
        java.sql.Date date=new java.sql.Date(millis);  
		pst.setDate(4, date);
		
		pst.setString(5,complaint.getSeverity());
		pst.setString(6, complaint.getStatus());
		pst.executeUpdate();
		return "Complaint Added";
	}
	
	public Complaint[] ShowAllComplaint() throws ClassNotFoundException, SQLException
	{
		con = ConnectionHelper.getConnection();
		String cmd = "select * from complaint";
		pst = con.prepareStatement(cmd);
		ResultSet rs = pst.executeQuery();
		List<Complaint> listComplaint = new ArrayList<Complaint>();
		Complaint c = null;
		while(rs.next())
		{
			c = new Complaint();
			c.setComplaintid(rs.getString("complaintid"));
			c.setCdescription(rs.getString("cdescription"));
			c.setComplaintdate(rs.getDate("complaintdate"));
			c.setComplainttype(rs.getString("complainttype"));
			c.setSeverity(rs.getString("severity"));
			c.setStatus(rs.getString("status"));
			listComplaint.add(c);
		}
		return listComplaint.toArray(new Complaint[listComplaint.size()]);
	}
	
	public Resolve[] ShowAllResolve() throws ClassNotFoundException, SQLException
	{
		con = ConnectionHelper.getConnection();
		String cmd = "select * from Resolve";
		pst = con.prepareStatement(cmd);
		ResultSet rs = pst.executeQuery();
		List<Resolve> listResolve = new ArrayList<Resolve>();
		Resolve r = null;
		while(rs.next())
		{
			r = new Resolve();
			r.setComplaintid(rs.getString("complaintid"));
			r.setComplaintdate(rs.getDate("complaintdate"));
			r.setResolvedate(rs.getDate(("resolvedate")));
			r.setResolvedby(rs.getString("resolvedby"));
			r.setComments(rs.getString("comments"));
			listResolve.add(r);
		}
		return listResolve.toArray(new Resolve[listResolve.size()]);
	}
	
	public Complaint SearchComplaint(int cid) throws ClassNotFoundException, SQLException
	{
		con=ConnectionHelper.getConnection();
		String cmd = "select * from Complaint where complaintid = ?";
		pst=con.prepareStatement(cmd);
		pst.setInt(1, cid);
		ResultSet rs = pst.executeQuery();
		Complaint c = null;
		while(rs.next())
		{
			c= new Complaint();
			c.setComplaintid(rs.getString("complaintid"));
			c.setCdescription(rs.getString("cdescription"));
			c.setComplaintdate(rs.getDate("complaintdate"));
			c.setComplainttype(rs.getString("complainttype"));
			c.setSeverity(rs.getString("severity"));
			c.setStatus(rs.getString("status"));
			
		}
		return c;
	}
	
	public String ResolveComplaint(Resolve resolve) throws ClassNotFoundException, SQLException
	{
		con = ConnectionHelper.getConnection();
		String cmd = "select count(*) cnt,complaintdate cd from complaint where status = 'pending' and complaintid=?";
		pst=con.prepareStatement(cmd);
		pst.setString(1, resolve.getComplaintid());
		ResultSet rs = pst.executeQuery();
		rs.next();
		int cnt = rs.getInt("cnt");
		java.sql.Date cd = rs.getDate("cd");
		//System.out.println(cd);
		
		if(cnt==0)
		{
			return "No such record for ID";
		}
		else
		{
			String cmd2="insert into resolve values(?,?,?,?,?)";
			pst= con.prepareStatement(cmd2);
			pst.setString(1, resolve.getComplaintid());
			
			pst.setDate(2, cd);
			
			long millis=System.currentTimeMillis();  
	        java.sql.Date date=new java.sql.Date(millis);  
			pst.setDate(3, date);
			
			pst.setString(4, resolve.getResolvedby());
			pst.setString(5, resolve.getComments());
			pst.executeUpdate();
			
			String cmd3 = "update complaint set status='Resolved' where complaintid = ? ";
			pst=con.prepareStatement(cmd3);
			
			pst.setString(1, resolve.getComplaintid());
			pst.executeUpdate();
				
		}
		return "Resolved";
	}
}
