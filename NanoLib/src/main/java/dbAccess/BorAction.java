package dbAccess;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BorAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DBFunction newConnection; 
       
    public BorAction() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String Action = request.getParameter("Action");
		
		if(Action.equals("getSerial")) {
			try {
				newConnection = new DBFunction();
				String ISBN = request.getParameter("ISBN");
				String Serial = newConnection.getSerial(ISBN)+"";
				response.setContentType("text/plain");
				response.getWriter().write(Serial);
			}catch(SQLException e) {
				e.printStackTrace();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		if(Action.equals("getBookTitle")) {
			try {
				newConnection = new DBFunction();
				String ISBN = request.getParameter("ISBN");
				String BookTitle = newConnection.getBookTitle(ISBN)+"";
				response.setContentType("text/plain");
				response.getWriter().write(BookTitle);
			}catch(SQLException e) {
				e.printStackTrace();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		if(Action.equals("getBookDescription")) {
			try {
				newConnection = new DBFunction();
				String ISBN = request.getParameter("ISBN");
				String BookDescription = newConnection.getBookDescription(ISBN)+"";
				response.setContentType("text/plain");
				response.getWriter().write(BookDescription);
			}catch(SQLException e) {
				e.printStackTrace();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		if(Action.equals("getMemFName")) {
			try {
				newConnection = new DBFunction();
				String MemID = request.getParameter("MemID");
				String BookDescription = newConnection.getMemFName(MemID)+"";
				response.setContentType("text/plain");
				response.getWriter().write(BookDescription);
			}catch(SQLException e) {
				e.printStackTrace();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		if(Action.equals("getMemLName")) {
			try {
				newConnection = new DBFunction();
				String MemID = request.getParameter("MemID");
				String BookDescription = newConnection.getMemLName(MemID)+"";
				response.setContentType("text/plain");
				response.getWriter().write(BookDescription);
			}catch(SQLException e) {
				e.printStackTrace();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		if(Action.equals("getMemNotice")) {
			try {
				newConnection = new DBFunction();
				String MemID = request.getParameter("MemID");
				String BookDescription = newConnection.getMemNotice(MemID)+"";
				response.setContentType("text/plain");
				response.getWriter().write(BookDescription);
			}catch(SQLException e) {
				e.printStackTrace();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

}
