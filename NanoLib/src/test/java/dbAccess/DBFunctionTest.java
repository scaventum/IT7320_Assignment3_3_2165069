package dbAccess;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DBFunctionTest {
	DBFunction obj;
	String UserID;
	String Password;
	String ISBN;

	@Before
	public void setUp() throws Exception {
		obj = new DBFunction();
		UserID = "ADM";
		Password = "12345";
		ISBN = "978-0-478-39455-9";
	}

	@After
	public void tearDown() throws Exception {
		obj = null;
		UserID = null;
		Password = null;
		ISBN = null;
	}

	@Test
	public void testValidSignIn() {
		assertTrue(obj.validSignIn(UserID,Password));
	}
	
	@Test
	public void testGetSerial() {
		int expected=1;
		assertEquals(1,obj.getSerial(ISBN));
	}
}
