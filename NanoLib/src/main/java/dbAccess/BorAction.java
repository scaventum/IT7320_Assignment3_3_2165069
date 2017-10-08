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
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

}
