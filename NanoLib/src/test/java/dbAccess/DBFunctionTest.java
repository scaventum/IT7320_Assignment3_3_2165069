package dbAccess;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DBFunctionTest {
	DBFunction obj;
	String UserID,Password,ISBN,MemID;
	int Serial;

	@Before
	public void setUp() throws Exception {
		obj = new DBFunction();
		UserID = "ADM";
		Password = "12345";
		ISBN = "978-0-478-39455-9";
		Serial = 1;
		MemID = "M00001";
	}

	@After
	public void tearDown() throws Exception {
		obj = null;
		UserID = null;
		Password = null;
		ISBN = null;
		Serial = 0;
		MemID = null;
	}

	@Test
	public void testValidSignIn() {
		assertTrue(obj.validSignIn(UserID,Password));
	}
	
	@Test
	public void testGetSerial() {
		int expected=1;
		assertEquals(expected,obj.getSerial(ISBN));
	}
	
	@Test
	public void testGetBookTitle() {
		String expected="The Official New Zealand Road Code";
		assertEquals(expected,obj.getBookTitle(ISBN));
	}
	
	@Test
	public void testGetBookDescription() {
		String expected="Be prepared – set yourself up to pass first time. The official New Zealand road code is a user-friendly guide to New Zealand's traffic law and safe driving practices. It includes the questions that you could be asked when you sit the theory test for your learner license.";
		assertEquals(expected,obj.getBookDescription(ISBN));
	}
	
	@Test
	public void testGetMemFName() {
		String expected="Chester";
		assertEquals(expected,obj.getMemFName(MemID));
	}
	
	@Test
	public void testGetMemLName() {
		String expected="Bennington";
		assertEquals(expected,obj.getMemLName(MemID));
	}
	
	@Test
	public void testGetMemNotice() {
		String expected="Member has perfect record. ";
		assertEquals(expected,obj.getMemNotice(MemID));
	}
	
	@Test
	public void testGetRetNotice() {
		String expected="Book is not in borrowed list. ";
		assertEquals(expected,obj.getRetNotice(ISBN,Serial));
	}
	
	@Test
	public void testGetMemID() {
		String expected="";
		assertEquals(expected,obj.getMemID(ISBN,Serial));
	}
}
