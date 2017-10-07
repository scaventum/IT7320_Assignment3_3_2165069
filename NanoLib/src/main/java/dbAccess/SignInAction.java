package dbAccess;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class SignInAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DBFunction newConnection; 
       
    public SignInAction() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String BtnSignIn = request.getParameter("BtnSignIn");
		String UserID = request.getParameter("UserID");
		String Password = request.getParameter("Password");
		
		if(BtnSignIn!=null){
			boolean transaction = false;
			try {
				newConnection = new DBFunction();
				if (newConnection.validSignIn(UserID,Password)) {
					transaction=true;
				}
				
				if(transaction){
					HttpSession session=request.getSession(true);
					session.setAttribute("UserID",UserID);

					//request.getRequestDispatcher("borrow.jsp").forward(request, response);
					response.sendRedirect("borrow.jsp");
					//response.sendRedirect("display.jsp?TicketNO="+request.getParameter("txtTicketNO"));
				}else {
					response.sendRedirect("index.jsp?alert=inv_signin");
					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}

}
