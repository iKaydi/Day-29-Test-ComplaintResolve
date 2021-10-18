package com.ComplainResolveService;

import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/complaint")
public class ComplaintService {

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/addComplaint")
	public String AddComplaint(Complaint complaint) throws ClassNotFoundException, SQLException {
		return new ComplaintDAO().AddComplaint(complaint);
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/addResolve")
	public String AddResolve(Resolve resolve) throws ClassNotFoundException, SQLException {
		return new ComplaintDAO().ResolveComplaint(resolve);
	}
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Complaint[] ShowAllComplaint() throws ClassNotFoundException, SQLException {
		ComplaintDAO dao = new ComplaintDAO();
		return dao.ShowAllComplaint();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/resolve")
	public Resolve[] ShowAllResolve() throws ClassNotFoundException, SQLException {
		ComplaintDAO dao = new ComplaintDAO();
		return dao.ShowAllResolve();
	}
	
	@GET
	@Path("{complaintid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Complaint SearchComplaint(@PathParam("complaintid") int cid) throws ClassNotFoundException, SQLException {
		ComplaintDAO dao = new ComplaintDAO();
		return dao.SearchComplaint(cid);
	}
}