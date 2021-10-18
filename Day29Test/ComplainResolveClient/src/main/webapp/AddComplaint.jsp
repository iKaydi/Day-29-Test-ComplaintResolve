<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.ComplainResolveClient.Complaint"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="com.sun.jersey.api.client.ClientResponse"%>
<%@page import="com.sun.jersey.api.client.WebResource"%>
<%@page import="com.sun.jersey.api.client.Client"%>
<%@page import="javax.ws.rs.client.Invocation"%>
<%@page import="javax.ws.rs.client.Entity"%>
<%@page import="javax.ws.rs.core.MediaType"%>
<%@page import="javax.ws.rs.core.Response"%>
<%@page import="javax.ws.rs.client.WebTarget"%>
<%@page import="javax.ws.rs.client.ClientBuilder"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
	Complaint c = new Complaint();

	c.setComplaintid(request.getParameter("cid"));
	
	c.setCdescription(request.getParameter("cdesc"));
	
	long millis=java.lang.System.currentTimeMillis();  
    java.sql.Date date=new java.sql.Date(millis);  
    c.setComplaintdate(date);
	
	c.setComplainttype(request.getParameter("ctype"));
	
	c.setSeverity("None");
	c.setStatus("Pending");
	
	 Client client = Client.create();
     WebResource webResource = client.resource("http://localhost:8080/ComplainResolveService/webapi/complaint/addComplaint");
	
  	Gson obj = new Gson();  
  String input = obj.toJson(c);

     ClientResponse res = webResource.type("application/json")
        .post(ClientResponse.class, input);
     if (response.getStatus() != 200) {
         throw new RuntimeException("Failed : HTTP error code : "
              + res.getStatus());
     } 

  
     String output = res.getEntity(String.class);
     out.println(output); 
%>
</body>
</html>