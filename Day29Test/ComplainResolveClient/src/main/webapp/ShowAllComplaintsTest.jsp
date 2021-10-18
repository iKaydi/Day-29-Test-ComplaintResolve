<%@page import="com.ComplainResolveClient.Resolve"%>
<%@page import="java.sql.Date"%>
<%@page import="com.ComplainResolveClient.Complaint"%>
<%@page import="org.codehaus.jackson.map.ObjectMapper"%>
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
<table border="3">
<tr>
	<th>Complaint ID</th>
	<th>Complaint Type</th>
	<th>Complaint Description</th>
	<th>Complaint Date</th>
	<th>Complaint Severity</th>
	<th>Complaint Status</th>
</tr>

<%
//THIS IS FOR COMPLAINT TABLE
		 URL url = new URL("http://localhost:8080/ComplainResolveService/webapi/complaint");
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
	       
	            
	        }
	        ObjectMapper objectMapper = new ObjectMapper();
	        Complaint[] complaint = objectMapper.readValue(res, Complaint[].class);
	 
	
//THIS IS FOR RESOLVE
	       URL url2 = new URL("http://localhost:8080/ComplainResolveService/webapi/complaint/resolve");
	        HttpURLConnection conn2 = (HttpURLConnection) url2.openConnection();
	        conn2.setRequestMethod("GET");
	        conn2.setRequestProperty("Accept", "application/json");
	        if (conn2.getResponseCode() != 200) {
	            throw new RuntimeException("Failed : HTTP error code : "
	                    + conn2.getResponseCode());
	        }
	        BufferedReader br2 = new BufferedReader(new InputStreamReader(
	            (conn2.getInputStream())));
	        String output2;
	        String res2="";
	        while ((output2 = br2.readLine()) != null) {
	        	res2+=output2;
	
	            
	        }
	        ObjectMapper objectMapper2 = new ObjectMapper();
	        Resolve[] resolve = objectMapper.readValue(res2, Resolve[].class);


	   
	      for(Complaint c : complaint) {
		        
	    	     for(Resolve r : resolve)
		    	  {
		        	String stat = c.getStatus();
	    		  	Date cdate = c.getComplaintdate();
	    			Date rdate = r.getResolvedate();
	    			
	    			long ms = rdate.getTime() - cdate.getTime();
	    			int days = (int) ms/(1000*60*60*24);
	    			days+=1;
	    			out.println(days);
	    			String cid = c.getComplaintid();
	    			String rid = r.getComplaintid();
					
	    			if(stat.equals("Pending") && days<=7)
	    			{

				
  %>
  	<tr style="background-color:LightBlue">
  		<td> <%=c.getComplaintid() %> </td>
  		<td> <%=c.getComplainttype() %> </td>
  		<td><%=c.getCdescription() %>  </td>
  		<td> <%=c.getComplaintdate() %> </td>
  		<td> <%=c.getSeverity() %> </td>
  		<td><%=c.getStatus() %> </td>
  	</tr>
<%
	break;
  		}
	    			
	  else if(stat.equals("Resolved") && days<=1)
  
  		{

  %>
    	<tr style="background-color:Green">
  		<td> <%=c.getComplaintid() %> </td>
  		<td> <%=c.getComplainttype() %> </td>
  		<td><%=c.getCdescription() %>  </td>
  		<td> <%=c.getComplaintdate() %> </td>
  		<td> <%=c.getSeverity() %> </td>
  		<td><%=c.getStatus() %> </td>
  	</tr>
  	<%
	break;
  		}
	    			
	  else if(stat.equals("Resolved") && days>5)
  
  		{

  %>
    	<tr style="background-color:Pink">
  		<td> <%=c.getComplaintid() %> </td>
  		<td> <%=c.getComplainttype() %> </td>
  		<td><%=c.getCdescription() %>  </td>
  		<td> <%=c.getComplaintdate() %> </td>
  		<td> <%=c.getSeverity() %> </td>
  		<td><%=c.getStatus() %> </td>
  	</tr>
  	 	<%
	break;
  		}
	    			
	  else if(stat.equals("Pending") && days>7)
  
  		{

  %>
    	<tr style="background-color:Red">
  		<td> <%=c.getComplaintid() %> </td>
  		<td> <%=c.getComplainttype() %> </td>
  		<td><%=c.getCdescription() %>  </td>
  		<td> <%=c.getComplaintdate() %> </td>
  		<td> <%=c.getSeverity() %> </td>
  		<td><%=c.getStatus() %> </td>
  	</tr>
  	 	<%
	break;
  		}
	    			
	  else 
  
  		{

  %>
    	<tr style="background-color:Yellow">
  		<td> <%=c.getComplaintid() %> </td>
  		<td> <%=c.getComplainttype() %> </td>
  		<td><%=c.getCdescription() %>  </td>
  		<td> <%=c.getComplaintdate() %> </td>
  		<td> <%=c.getSeverity() %> </td>
  		<td><%=c.getStatus() %> </td>
  	</tr>
  	
  	
<%

	break;
		}
	   }
	}
%>
	  </table>
</body>
</html>