package dbAccess;

public interface DBFunctionInterface {
	public boolean validSignIn(String UserID,String Password);
	public int getSerial(String ISBN);
	public String getBookTitle(String ISBN);
	public String getBookDescription(String ISBN);
	public String getMemFName(String MemID);
	public String getMemLName(String MemID);
	public String getMemNotice(String MemID);
	public String getRetNotice(String ISBN,int Serial);
}
