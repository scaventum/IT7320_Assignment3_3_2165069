package dbAccess;

public interface DBFunctionInterface {
	public boolean validSignIn(String UserID,String Password);
	public int getSerial(String ISBN);
}
