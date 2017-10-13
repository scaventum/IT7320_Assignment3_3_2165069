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
		
		expected=2;
		assertEquals(expected,obj.getSerial("0-472-06521-1"));
	}
	
	@Test
	public void testGetBookTitle() {
		String expected="The Official New Zealand Road Code";
		assertEquals(expected,obj.getBookTitle(ISBN));
		
		expected="Simulacra and Simulation";
		assertEquals(expected,obj.getBookTitle("0-472-06521-1"));
	}
	
	@Test
	public void testGetBookDescription() {
		String expected="Be prepared – set yourself up to pass first time. The official New Zealand road code is a user-friendly guide to New Zealand's traffic law and safe driving practices. It includes the questions that you could be asked when you sit the theory test for your learner license.";
		assertEquals(expected,obj.getBookDescription(ISBN));
		
		expected="Simulacra and Simulation is most known for its discussion of symbols, signs, and how they relate to contemporaneity (simultaneous existences). Baudrillard claims that our current society has replaced all reality and meaning with symbols and signs, and that human experience is a simulation of reality. Moreover, these simulacra are not merely mediations of reality, nor even deceptive mediations of reality; they are not based in a reality nor do they hide a reality, they simply hide that nothing like reality is relevant to our current understanding of our lives. The simulacra that Baudrillard refers to are the significations and symbolism of culture and media that construct perceived reality, the acquired understanding by which our lives and shared existence is and are rendered legible; Baudrillard believed that society has become so saturated with these simulacra and our lives so saturated with the constructs of society that all meaning was being rendered meaningless by being infinitely mutable. Baudrillard called this phenomenon the precession of simulacra.";
		assertEquals(expected,obj.getBookDescription("0-472-06521-1"));
	}
	
	@Test
	public void testGetMemFName() {
		String expected="Chester";
		assertEquals(expected,obj.getMemFName(MemID));
		
		expected="Mike";
		assertEquals(expected,obj.getMemFName("M00002"));
	}
	
	@Test
	public void testGetMemLName() {
		String expected="Bennington";
		assertEquals(expected,obj.getMemLName(MemID));
		
		expected="Shinoda";
		assertEquals(expected,obj.getMemLName("M00002"));
	}
	
	@Test
	public void testGetMemNotice() {
		String expected="Member has perfect record. ";
		assertEquals(expected,obj.getMemNotice(MemID));

		expected="Member has perfect record. ";
		assertEquals(expected,obj.getMemNotice("M00002"));
	}
	
	@Test
	public void testGetRetNotice() {
		String expected="Book is not in borrowed list. ";
		assertEquals(expected,obj.getRetNotice(ISBN,Serial));

		expected="";
		assertEquals(expected,obj.getRetNotice("0-472-06521-1",1));
	}
	
	@Test
	public void testGetMemID() {
		String expected="";
		assertEquals(expected,obj.getMemID(ISBN,Serial));
		
		expected="M00001";
		assertEquals(expected,obj.getMemID("0-472-06521-1",1));
	}
}
