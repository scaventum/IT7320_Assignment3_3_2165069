<%
String UserID=(String) session.getAttribute("UserID");
if(UserID!=null){
	session.invalidate();
}
response.sendRedirect("index.jsp");
%>