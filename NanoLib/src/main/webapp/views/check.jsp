<%@ page import="java.sql.*" %>

<% 
String dbUrl = "jdbc:mysql://localhost:3306/db_nanolib";
String user = "root";
String password = "";
Connection myConn;

Class.forName("com.mysql.cj.jdbc.Driver");
myConn = DriverManager.getConnection(dbUrl,user,password);

Statement myStmt = myConn.createStatement() ;
ResultSet SerialList = myStmt.executeQuery("Select a.ISBN,a.Serial, "
		                                  +"       Case "
		                                  +"       When b.BorNO Is Null Then 'Available' "
		                                  +"       Else Concat('Borrowed by ',c.MemID,' (',c.MemFName,' ',c.MemLName,')') "
		                                  +"       End As Status, "
		                                  +"       Case "
		                                  +"       When Date_add(b.BorDate, Interval b.Duration Day) < Curdate() Then 'Return date is due<br>' "
		                                  +"       Else '<br>' "
		                                  +"       End As ExtraStatus, "
		                                  +"       Case "
		                                  +"       When Date_add(b.BorDate, Interval b.Duration Day) < Curdate() Then Concat(Datediff(Curdate(), Date_add(b.BorDate, Interval b.Duration Day)),' day(s) late<br>') "
				                          +"       When Date_add(b.BorDate, Interval b.Duration Day) >= Curdate() Then Concat(Datediff( Date_add(b.BorDate, Interval b.Duration Day),Curdate()),' day(s) to return<br>') "
		                                  +"       Else '' "
		                                  +"       End As ExtraStatusDateDiff "
										  +"From ms_serial a "
										  +"Left Join tr_borrowing b On a.ISBN = b.ISBN "
										  +"                        And a.Serial = b.Serial "
										  +"                        And b.BorReturnDate Is Null "
										  +"Left Join ms_member c On b.MemID = c.MemID "
		                                  +"Order By b.BorDate Desc,a.ISBN,a.Serial Asc") ; 
%>
        
<div class="content">
	<div class="contentTitle">Check Book Status</div>
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
				<tr valign="top"
				<%
				if(SerialList.getString("Status").equals("Available")){
					out.print("style='background:#9f9;' ");
				}else{
					if(SerialList.getString("ExtraStatus").equals("Return date is due<br>")){
						out.print("style='background:#f99;' ");
					}else{
						out.print("style='background:#ff6;' ");
					}
				}
				%>
				>
					<td align="right"><%=i %></td>
					<td align="left"><%=SerialList.getString("ISBN") %></td>
					<td align="right"><%=SerialList.getString("Serial") %></td>
					<td align="left">
						<%=SerialList.getString("Status") %>
						<%=SerialList.getString("ExtraStatus") %>
						<%=SerialList.getString("ExtraStatusDateDiff") %>
					</td>
				</tr>
				
				<% i++;} %>
			</tbody>
		</table>
	</form>
</div>