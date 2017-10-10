<%@ page import="java.sql.*" %>

<% 
String dbUrl = "jdbc:mysql://localhost:3306/db_nanolib";
String user = "root";
String password = "";
Connection myConn;

Class.forName("com.mysql.cj.jdbc.Driver");
myConn = DriverManager.getConnection(dbUrl,user,password);

Statement myStmt = myConn.createStatement() ;
ResultSet SerialList = myStmt.executeQuery("Select * From ms_serial Order By ISBN,Serial Asc") ; 
%>
        
<div class="content">
	<div class="contentTitle">Check</div>
	<div class="vmargin" style="height:10px;"></div>
	<form method="post" action="borAction" class="formSingle">
		<table cellspacing=0>
			<thead>
				<tr>
					<th width="10%">No.</th>
					<th width="30%">ISBN</th>
					<th width="10%">#</th>
					<th width="50%">Status</th>
				</tr>
			</thead>
			<tbody>
				<% int i=1;
				while(SerialList.next()){ %>
				<tr>
					<td align="right"><%=i %></td>
					<td><%=SerialList.getString("ISBN") %></td>
					<td align="right"><%=SerialList.getString("Serial") %></td>
					<td>Available</td>
				</tr>
				
				<% i++;} %>
			</tbody>
		</table>
	</form>
</div>