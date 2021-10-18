package com.ComplainResolveService;

import java.sql.Date;

public class Resolve {

	private String complaintid;
	private Date complaintdate;
	private Date resolvedate;
	private String resolvedby;
	private String comments;
	
	public String getComplaintid() {
		return complaintid;
	}
	public void setComplaintid(String complaintid) {
		this.complaintid = complaintid;
	}
	public Date getComplaintdate() {
		return complaintdate;
	}
	public void setComplaintdate(Date complaintdate) {
		this.complaintdate = complaintdate;
	}
	public Date getResolvedate() {
		return resolvedate;
	}
	public void setResolvedate(Date resolvedate) {
		this.resolvedate = resolvedate;
	}
	public String getResolvedby() {
		return resolvedby;
	}
	public void setResolvedby(String resolvedby) {
		this.resolvedby = resolvedby;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	@Override
	public String toString() {
		return "Resolve [complaintid=" + complaintid + ", complaintdate=" + complaintdate + ", resolvedate="
				+ resolvedate + ", resolvedby=" + resolvedby + ", comments=" + comments + "]";
	}
	
	
}
