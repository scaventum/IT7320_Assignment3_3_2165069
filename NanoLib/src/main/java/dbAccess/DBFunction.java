package dbAccess;

import java.sql.*;

public class DBFunction implements DBFunctionInterface{
	private Connection myConn;
	private ResultSet myRs;
	private PreparedStatement myPstmt;
	private Statement myStmt;
	private int myEu;
	
	public DBFunction() throws Exception{
		String dbUrl = "jdbc:mysql://localhost:3306/db_nanolib";
		String user = "root";
		String password = "";
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		myConn = DriverManager.getConnection(dbUrl,user,password);
		myStmt = myConn.createStatement();
		
		myRs = null;
		myPstmt = null;
		myEu=0;
		
	}

	//Check validity of sign in credential
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

	//Retrieve available serial from ISBN
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

	//Retrieve book title from ISBN
	public String getBookTitle(String ISBN) {
		String result="";
		try {
			myPstmt = myConn.prepareStatement("Select a.BookTitle "
					                        + "From ms_book a "
					                        + "Where a.ISBN = ? ");
			myPstmt.setString(1, ISBN);;
			myRs = myPstmt.executeQuery();

			while(myRs.next()) {
				result = myRs.getString(1);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	//Retrieve book description from ISBN
	public String getBookDescription(String ISBN) {
		String result="";
		try {
			myPstmt = myConn.prepareStatement("Select a.BookDescription "
					                        + "From ms_book a "
					                        + "Where a.ISBN = ? ");
			myPstmt.setString(1, ISBN);;
			myRs = myPstmt.executeQuery();

			while(myRs.next()) {
				result = myRs.getString(1);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	//Retrieve member first name from member ID
	public String getMemFName(String MemID) {
		String result="";
		try {
			myPstmt = myConn.prepareStatement("Select a.MemFName "
					                        + "From ms_member a "
					                        + "Where a.MemID = ? ");
			myPstmt.setString(1, MemID);
			myRs = myPstmt.executeQuery();

			while(myRs.next()) {
				result = myRs.getString(1);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return result;
	}	
	
	//Retrieve member last name from member ID
	public String getMemLName(String MemID) {
		String result="";
		try {
			myPstmt = myConn.prepareStatement("Select a.MemLName "
					                        + "From ms_member a "
					                        + "Where a.MemID = ? ");
			myPstmt.setString(1, MemID);
			myRs = myPstmt.executeQuery();

			while(myRs.next()) {
				result = myRs.getString(1);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	//Retrieve member notice from member ID
	public String getMemNotice(String MemID) {
		String result="";
		try {

			//Counting late returns history
			myPstmt = myConn.prepareStatement("Select 1 " + 
											  "From tr_borrowing " + 
											  "Where BorReturnDate Is Not Null " + 
											  "And MemID = ? " + 
											  "And Date_add(BorDate, Interval Duration DAY) < BorReturnDate ");
			
			myPstmt.setString(1, MemID);
			myRs = myPstmt.executeQuery();
			int myRsRow1 = 0;
			while(myRs.next()) {
				myRsRow1++;
				System.out.println(myRsRow1);
			}
			if(myRsRow1>0)result += "Member has "+myRsRow1+" late return(s) record. ";
			
			//Counting must return books
			myPstmt = myConn.prepareStatement("Select 1 " + 
											  "From tr_borrowing " + 
											  "Where BorReturnDate Is Null " + 
											  "And MemID = ? " + 
											  "And Date_add(BorDate, Interval Duration DAY) < CURDATE() ");
			myPstmt.setString(1, MemID);
			myRs = myPstmt.executeQuery();
			int myRsRow2 = 0;
			while(myRs.next()) {
				myRsRow2++;
			}
			if(myRsRow2>0)result += "Member must return "+myRsRow2+" book(s). ";

			if(result.equals(""))result = "Member has perfect record. ";
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	//Retrieve return notice from ISBN and serial
	public String getRetNotice(String ISBN,int Serial) {
		String result="";
		try {
			boolean borrowed=false;
			myPstmt = myConn.prepareStatement("Select 1 " + 
					  "From tr_borrowing " + 
					  "Where BorReturnDate Is Null " + 
					  "And ISBN = ? " + 
					  "And Serial = ? ");
			myPstmt.setString(1, ISBN);
			myPstmt.setInt(2, Serial);
			myRs = myPstmt.executeQuery();
			if(myRs.next()) {
				borrowed=true;
			}
			
			if(borrowed) {
			//Counting must return books
				myPstmt = myConn.prepareStatement("Select 1 " + 
												  "From tr_borrowing " + 
												  "Where BorReturnDate Is Null " + 
												  "And ISBN = ? " + 
												  "And Serial = ? " + 
												  "And Date_add(BorDate, Interval Duration DAY) < CURDATE() ");
				myPstmt.setString(1, ISBN);
				myPstmt.setInt(2, Serial);
				myRs = myPstmt.executeQuery();
				if(myRs.next()) {
					result += "Member returns the book late. ";
				}
			}else {
				result += "Book is not in borrowed list. ";
			}

			if(result.equals(""))result = "";
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	//Retrieve member ID from ISBN and serial
	public String getMemID(String ISBN, int Serial) {
		String result="";
		try {
			myPstmt = myConn.prepareStatement("Select MemID "
					                        + "From tr_borrowing a "
					                        + "Where a.ISBN = ? and a.Serial = ? "
					                        + "And a.BorReturnDate Is Null");
			myPstmt.setString(1, ISBN);
			myPstmt.setInt(2, Serial);
			myRs = myPstmt.executeQuery();

			while(myRs.next()) {
				result = myRs.getString(1);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return result;
	}	
	
	//Add borrowing data
	public void insertBorrow(String MemID, String ISBN, int Serial, int Duration) throws SQLException {
		try {
			myConn.setAutoCommit(false);
			
			myPstmt = myConn.prepareStatement("Insert Into tr_borrowing (MemID,ISBN,Serial,Duration,BorDate) Values (?,?,?,?,NOW()) ");
			myPstmt.setString(1, MemID);
			myPstmt.setString(2, ISBN);
			myPstmt.setInt(3, Serial);
			myPstmt.setInt(4, Duration);
			myEu = myPstmt.executeUpdate();
			
			myPstmt = myConn.prepareStatement("Insert Into log_activity (TransID,LogModule,LogAction,LogTime) Values (?,?,?,NOW()) ");
			myPstmt.setString(1, MemID+"/"+ISBN+"/"+Serial);
			myPstmt.setString(2, "Borrow");
			myPstmt.setString(3, "Insert");
			myEu = myPstmt.executeUpdate();
			
			myConn.commit();
		}catch(SQLException e) {
			System.out.println(e.getMessage());
			myConn.rollback();
		}
	}	
	
	//Edit borrowing data to returned
	public void insertReturn(String MemID, String ISBN, int Serial) throws SQLException {
		try {
			myConn.setAutoCommit(false);
			
			myPstmt = myConn.prepareStatement("Update tr_borrowing Set BorReturnDate=NOW() Where ISBN=? And Serial=? And BorReturnDate Is Null");
			myPstmt.setString(1, ISBN);
			myPstmt.setInt(2, Serial);
			myEu = myPstmt.executeUpdate();
			
			myPstmt = myConn.prepareStatement("Insert Into log_activity (TransID,LogModule,LogAction,LogTime) Values (?,?,?,NOW()) ");
			myPstmt.setString(1, MemID+"/"+ISBN+"/"+Serial);
			myPstmt.setString(2, "Return");
			myPstmt.setString(3, "Insert");
			myEu = myPstmt.executeUpdate();
			
			myConn.commit();
		}catch(SQLException e) {
			System.out.println(e.getMessage());
			myConn.rollback();
		}
	}	
}
