<%@page import="org.codehaus.jackson.map.ObjectMapper"%>
<%@page import="com.ComplainResolveClient.Complaint"%>
<%@page import="java.io.InputStreamReader"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.net.HttpURLConnection"%>
<%@page import="java.net.URL"%>
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
String a = request.getParameter("cid");
		 URL url = new URL("http://localhost:8080/ComplainResolveService/webapi/complaint/"+a);
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("GET");
	        conn.setRequestProperty("Accept", "application/json");
	        if (conn.getResponseCode() != 200) {
	            throw new RuntimeException("Failed : HTTP error code : "
	                    + conn.getResponseCode());
	        }
	        BufferedReader br = new BufferedReader(new InputStreamReader(
	            (conn.getInputStream())));
	        String output;
	        String res="";
	        while ((output = br.readLine()) != null) {
	        	res+=output;
	            //out.println(output);
	        }
	       // out.println(res);
	      Complaint c = new ObjectMapper().readValue(res, Complaint.class);
	    out.println("Complaint ID  " +c.getComplaintid() + "<br/>");
	    out.println("Complaint Type " +c.getComplainttype() + "<br/>");
	    out.println("Complaint Description  " +c.getCdescription() + "<br/>"); 
	    out.println("Complaint Date " +c.getComplaintdate() + "<br/>");
	    out.println("Complaint Status " +c.getStatus() + "<br/>");
	    out.println("Complaint Severity " +c.getSeverity() + "<br/>");
	  %>
</body>
</html>