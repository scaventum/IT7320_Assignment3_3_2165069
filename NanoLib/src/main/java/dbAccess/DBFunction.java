package dbAccess;

import java.sql.*;

public class DBFunction implements DBFunctionInterface{
	private Connection myConn;
	private ResultSet myRs;
	private PreparedStatement myPstmt;
	private Statement myStmt;
	
	public DBFunction() throws Exception{
		String dbUrl = "jdbc:mysql://localhost:3306/db_nanolib";
		String user = "root";
		String password = "";
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		myConn = DriverManager.getConnection(dbUrl,user,password);
		myStmt = myConn.createStatement();
		
		myRs = null;
		myPstmt = null;
		
	}

	public boolean validSignIn(String UserID, String Password) {
		boolean result=false;
		
		try {
			myPstmt = myConn.prepareStatement("Select 1 From ms_user Where UserID = ? And Password = ? ");
			myPstmt.setString(1, UserID);
			myPstmt.setString(2, Password);
			myRs = myPstmt.executeQuery();
			
			while(myRs.next()) {
				result = true;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return result;
		
	}

	public int getSerial(String ISBN) {
		int result=0;
		try {
			myPstmt = myConn.prepareStatement("Select a.Serial "
					                        + "From ms_serial a "
					                        + "Where a.ISBN = ? "
					                        + "  And Not Exists (Select 1 "
					                        + "                  From tr_borrowing x "
					                        + "                  Where x.ISBN = a.ISBN "
					                        + "                    And x.Serial = a.Serial"
					                        + "                    And x.BorReturnDate Is Null) "
					                        + "Order By Serial ASC Limit 0,1");
			myPstmt.setString(1, ISBN);;
			myRs = myPstmt.executeQuery();
			
			while(myRs.next()) {
				result = myRs.getInt(1);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return result;
	}	

}
