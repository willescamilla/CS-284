package SpecialProject2;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;

public class PhoneBookTest {

	@Test
	public void constructorTest() {
		// setting up test case
		PhoneBook test = new PhoneBook();
		
		// testing for general cases
		for (Character letter : test.directory.keySet())
			assertEquals("null\n", test.directory.get(letter).toString());
	}
	
	@Test
	public void getFamilyTreeTest() {
		// setting up test case
		PhoneBook test = new PhoneBook();
		Character[] lowercaseLetters = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
				'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
				'w', 'x', 'y', 'z'};
		
		// testing for when letter is lowercase
		for (Character letter : lowercaseLetters)
			assertEquals("null\n", test.getFamilyTree(letter).toString());
		
		// testing for general cases
		BSFamilyTree sFamilies = test.getFamilyTree('S');
		
		sFamilies.addFamilyTreeNode("Smith");
		FamilyTreeNode smithFamily = sFamilies.getFamilyTreeNode("Smith");
		assertTrue(sFamilies.doesFamilyExist("Smith"));
		smithFamily.addFamilyMember("Smith", "John", "5551234567");
		assertEquals("[John Smith (5551234567)]", smithFamily.toString());
		assertEquals("[John Smith (5551234567)]\n" + 
				"  null\n" + 
				"  null\n" + 
				"", sFamilies.toString());
		assertEquals(1, smithFamily.getMembers().size());
		smithFamily.addFamilyMember("Smith", "May", "5551234568");
		smithFamily.addFamilyMember("Smith", "April", "5551234569");
		smithFamily.addFamilyMember("Smith", "August", "5551234570");
		assertEquals("[John Smith (5551234567), May Smith (5551234568), April Smith (5551234569), August Smith (5551234570)]", smithFamily.toString());
		assertEquals("[John Smith (5551234567), May Smith (5551234568), April Smith (5551234569), August Smith (5551234570)]\n" + 
				"  null\n" + 
				"  null\n" + 
				"", sFamilies.toString());
		assertEquals(4, smithFamily.getMembers().size());
		
		sFamilies.addFamilyTreeNode("Simon");
		FamilyTreeNode simonFamily = sFamilies.getFamilyTreeNode("Simon");
		assertTrue(sFamilies.doesFamilyExist("Simon"));
		simonFamily.addFamilyMember("Simon", "Michael", "5557654321");
		assertEquals("[Michael Simon (5557654321)]", simonFamily.toString());
		assertEquals("[John Smith (5551234567), May Smith (5551234568), April Smith (5551234569), August Smith (5551234570)]\n" + 
				"  [Michael Simon (5557654321)]\n" + 
				"    null\n" + 
				"    null\n" + 
				"  null\n" + 
				"", sFamilies.toString());
		assertEquals(1, simonFamily.getMembers().size());
		simonFamily.addFamilyMember("Simon", "Andrew", "5557654322");
		simonFamily.addFamilyMember("Simon", "Barry", "5557654323");
		simonFamily.addFamilyMember("Simon", "Kelly", "5557654324");
		assertEquals("[Michael Simon (5557654321), Andrew Simon (5557654322), Barry Simon (5557654323), Kelly Simon (5557654324)]", simonFamily.toString());
		assertEquals("[John Smith (5551234567), May Smith (5551234568), April Smith (5551234569), August Smith (5551234570)]\n" + 
				"  [Michael Simon (5557654321), Andrew Simon (5557654322), Barry Simon (5557654323), Kelly Simon (5557654324)]\n" + 
				"    null\n" + 
				"    null\n" + 
				"  null\n" + 
				"", sFamilies.toString());
		assertEquals(4, simonFamily.getMembers().size());
		
		sFamilies.addFamilyTreeNode("Swindler");
		FamilyTreeNode swindlerFamily = sFamilies.getFamilyTreeNode("Swindler");
		assertTrue(sFamilies.doesFamilyExist("Swindler"));
		swindlerFamily.addFamilyMember("Swindler", "Clara", "5551593570");
		assertEquals("[Clara Swindler (5551593570)]", swindlerFamily.toString());
		assertEquals("[John Smith (5551234567), May Smith (5551234568), April Smith (5551234569), August Smith (5551234570)]\n" + 
				"  [Michael Simon (5557654321), Andrew Simon (5557654322), Barry Simon (5557654323), Kelly Simon (5557654324)]\n" + 
				"    null\n" + 
				"    null\n" + 
				"  [Clara Swindler (5551593570)]\n" + 
				"    null\n" + 
				"    null\n" + 
				"", sFamilies.toString());
		assertEquals(1, swindlerFamily.getMembers().size());
		swindlerFamily.addFamilyMember("Swindler", "Virginia", "5551593571");
		swindlerFamily.addFamilyMember("Swindler", "Henry", "5551593572");
		swindlerFamily.addFamilyMember("Swindler", "Charles", "5551593573");
		assertEquals("[Clara Swindler (5551593570), Virginia Swindler (5551593571), Henry Swindler (5551593572), Charles Swindler (5551593573)]", swindlerFamily.toString());
		assertEquals("[John Smith (5551234567), May Smith (5551234568), April Smith (5551234569), August Smith (5551234570)]\n" + 
				"  [Michael Simon (5557654321), Andrew Simon (5557654322), Barry Simon (5557654323), Kelly Simon (5557654324)]\n" + 
				"    null\n" + 
				"    null\n" + 
				"  [Clara Swindler (5551593570), Virginia Swindler (5551593571), Henry Swindler (5551593572), Charles Swindler (5551593573)]\n" + 
				"    null\n" + 
				"    null\n" + 
				"", sFamilies.toString());
		assertEquals(4, swindlerFamily.getMembers().size());
	}
	
	@Test
	public void addFamilyTest() {
		// setting up test cases
		PhoneBook test = new PhoneBook();
		
		// testing for general cases
		assertFalse(test.getFamilyTree('S').doesFamilyExist("Smith"));
		test.addFamily("Smith");
		assertTrue(test.getFamilyTree('S').doesFamilyExist("Smith"));
		assertFalse(test.getFamilyTree('J').doesFamilyExist("Johnson"));
		test.addFamily("Johnson");
		assertTrue(test.getFamilyTree('J').doesFamilyExist("Johnson"));
		assertFalse(test.getFamilyTree('W').doesFamilyExist("Williams"));
		test.addFamily("Williams");
		assertTrue(test.getFamilyTree('W').doesFamilyExist("Williams"));
		
		// testing for edge case when family is already in phone book
		try {
			test.addFamily("Smith");
			fail("Expected an IllegalArgumentException to be thrown.");
		} catch (IllegalArgumentException e) {
//			assertThat(e.getMessage(), is("Last name is already in the Phone Book."));
		}
		try {
			test.addFamily("Johnson");
			fail("Expected an IllegalArgumentException to be thrown.");
		} catch (IllegalArgumentException e) {
//			assertThat(e.getMessage(), is("Last name is already in the Phone Book."));
		}
		try {
			test.addFamily("Williams");
			fail("Expected an IllegalArgumentException to be thrown.");
		} catch (IllegalArgumentException e) {
//			assertThat(e.getMessage(), is("Last name is already in the Phone Book."));
		}
	}
	
	@Test
	public void addPersonTest() {
		// setting up the test case
		PhoneBook test = new PhoneBook();
		
		// testing for when family isn't in phone book
		try {
			test.getFamilyTree('S').getFamilyTreeNode("Smith").doesFamilyMemberExist("Smith", "John");
			fail("Expected an IllegalArgumentException to be thrown.");
		} catch (IllegalArgumentException e) {
//			assertThat(e.getMessage(), is("Last name is not in the tree."));
		}
		assertFalse(test.getFamilyTree('S').doesFamilyExist("Smith"));
		assertFalse(test.getFamilyTree('S').doesNumberExist("5551234567"));
		test.addPerson("Smith", "John", "5551234567");
		assertTrue(test.getFamilyTree('S').getFamilyTreeNode("Smith").doesFamilyMemberExist("Smith", "John"));
		assertTrue(test.getFamilyTree('S').doesFamilyExist("Smith"));
		assertTrue(test.getFamilyTree('S').doesNumberExist("5551234567"));
		
		try {
			test.getFamilyTree('J').getFamilyTreeNode("Johnson").doesFamilyMemberExist("Johnson", "Michael");
			fail("Expected an IllegalArgumentException to be thrown.");
		} catch (IllegalArgumentException e) {
//			assertThat(e.getMessage(), is("Last name is not in the tree."));
		}
		assertFalse(test.getFamilyTree('J').doesFamilyExist("Johnson"));
		assertFalse(test.getFamilyTree('J').doesNumberExist("5557654321"));
		test.addPerson("Johnson", "Michael", "5557654321");
		assertTrue(test.getFamilyTree('J').getFamilyTreeNode("Johnson").doesFamilyMemberExist("Johnson", "Michael"));
		assertTrue(test.getFamilyTree('J').doesFamilyExist("Johnson"));
		assertTrue(test.getFamilyTree('J').doesNumberExist("5557654321"));
		
		try {
			test.getFamilyTree('W').getFamilyTreeNode("Williams").doesFamilyMemberExist("Williams", "Clara");
			fail("Expected an IllegalArgumentException to be thrown.");
		} catch (IllegalArgumentException e) {
//			assertThat(e.getMessage(), is("Last name is not in the tree."));
		}
		assertFalse(test.getFamilyTree('W').doesFamilyExist("Williams"));
		assertFalse(test.getFamilyTree('W').doesNumberExist("5551593570"));
		test.addPerson("Williams", "Clara", "5551593570");
		assertTrue(test.getFamilyTree('W').getFamilyTreeNode("Williams").doesFamilyMemberExist("Williams", "Clara"));
		assertTrue(test.getFamilyTree('W').doesFamilyExist("Williams"));
		assertTrue(test.getFamilyTree('W').doesNumberExist("5551593570"));
		
		// testing for when family is in phone book
		assertFalse(test.getFamilyTree('S').getFamilyTreeNode("Smith").doesFamilyMemberExist("Smith", "May"));
		assertFalse(test.getFamilyTree('S').doesNumberExist("5551234568"));
		test.addPerson("Smith", "May", "5551234568");
		assertTrue(test.getFamilyTree('S').getFamilyTreeNode("Smith").doesFamilyMemberExist("Smith", "May"));
		assertTrue(test.getFamilyTree('S').doesFamilyExist("Smith"));
		assertTrue(test.getFamilyTree('S').doesNumberExist("5551234568"));
		
		assertFalse(test.getFamilyTree('J').getFamilyTreeNode("Johnson").doesFamilyMemberExist("Johnson", "Andrew"));
		assertFalse(test.getFamilyTree('J').doesNumberExist("5557654322"));
		test.addPerson("Johnson", "Andrew", "5557654322");
		assertTrue(test.getFamilyTree('J').getFamilyTreeNode("Johnson").doesFamilyMemberExist("Johnson", "Andrew"));
		assertTrue(test.getFamilyTree('J').doesFamilyExist("Johnson"));
		assertTrue(test.getFamilyTree('J').doesNumberExist("5557654322"));
		
		assertFalse(test.getFamilyTree('W').getFamilyTreeNode("Williams").doesFamilyMemberExist("Williams", "Virginia"));
		assertFalse(test.getFamilyTree('W').doesNumberExist("5551593571"));
		test.addPerson("Williams", "Virginia", "5551593571");
		assertTrue(test.getFamilyTree('W').getFamilyTreeNode("Williams").doesFamilyMemberExist("Williams", "Virginia"));
		assertTrue(test.getFamilyTree('W').doesFamilyExist("Williams"));
		assertTrue(test.getFamilyTree('W').doesNumberExist("5551593571"));
		
		// testing for edge case when phone number is already in phone book
		try {
			test.addPerson("Hanson", "Christopher", "5551234567");
			fail("Expected an IllegalArgumentException to be thrown.");
		} catch (IllegalArgumentException e) {
//			assertThat(e.getMessage(), is("This phone number is already in the Phone Book."));
		}
		
		// testing for edge case when person is already in phone book
		try {
			test.addPerson("Smith", "John", "5559876543");
			fail("Expected an IllegalArgumentException to be thrown.");
		} catch (IllegalArgumentException e) {
//			assertThat(e.getMessage(), is("This person is already in the Phone Book."));
		}
	}
	
	@Test
	public void getPhoneNumberTest() {
		// setting up test case
		PhoneBook test = new PhoneBook();
		
		// testing general cases
		//assertEquals("Does not exist.", test.getPhoneNumber("Smith", "John"));
		test.addPerson("Smith", "John", "5551234567");
		assertEquals("5551234567", test.getPhoneNumber("Smith", "John"));
		//assertEquals("Does not exist.", test.getPhoneNumber("Johnson", "Michael"));
		test.addPerson("Johnson", "Michael", "5557654321");
		assertEquals("5557654321", test.getPhoneNumber("Johnson", "Michael"));
		//assertEquals("Does not exist.", test.getPhoneNumber("Williams", "Clara"));
		test.addPerson("Williams", "Clara", "5551593570");
		assertEquals("5551593570", test.getPhoneNumber("Williams", "Clara"));
		//assertEquals("Does not exist.", test.getPhoneNumber("Smith", "May"));
		test.addPerson("Smith", "May", "5551234568");
		assertEquals("5551234568", test.getPhoneNumber("Smith", "May"));
	}
	
	@Test
	public void toStringTest() {
		// setting up test case
		PhoneBook test = new PhoneBook();
		
		// testing for when phone book is empty
		assertEquals("A\n" + 
				"null\n" + 
				"B\n" + 
				"null\n" + 
				"C\n" + 
				"null\n" + 
				"D\n" + 
				"null\n" + 
				"E\n" + 
				"null\n" + 
				"F\n" + 
				"null\n" + 
				"G\n" + 
				"null\n" + 
				"H\n" + 
				"null\n" + 
				"I\n" + 
				"null\n" + 
				"J\n" + 
				"null\n" + 
				"K\n" + 
				"null\n" + 
				"L\n" + 
				"null\n" + 
				"M\n" + 
				"null\n" + 
				"N\n" + 
				"null\n" + 
				"O\n" + 
				"null\n" + 
				"P\n" + 
				"null\n" + 
				"Q\n" + 
				"null\n" + 
				"R\n" + 
				"null\n" + 
				"S\n" + 
				"null\n" + 
				"T\n" + 
				"null\n" + 
				"U\n" + 
				"null\n" + 
				"V\n" + 
				"null\n" + 
				"W\n" + 
				"null\n" + 
				"X\n" + 
				"null\n" + 
				"Y\n" + 
				"null\n" + 
				"Z\n" + 
				"null\n" + 
				"", test.toString());
		
		// testing for when 1 family is added
		test.addPerson("Andrews", "John", "5551234567");
		test.addPerson("Andrews", "May", "5551234568");
		test.addPerson("Andrews", "April", "5551234569");
		test.addPerson("Andrews", "August", "5551234570");
		assertEquals("A\n" + 
				"[John Andrews (5551234567), May Andrews (5551234568), April Andrews (5551234569), August Andrews (5551234570)]\n" + 
				"  null\n" + 
				"  null\n" + 
				"B\n" + 
				"null\n" + 
				"C\n" + 
				"null\n" + 
				"D\n" + 
				"null\n" + 
				"E\n" + 
				"null\n" + 
				"F\n" + 
				"null\n" + 
				"G\n" + 
				"null\n" + 
				"H\n" + 
				"null\n" + 
				"I\n" + 
				"null\n" + 
				"J\n" + 
				"null\n" + 
				"K\n" + 
				"null\n" + 
				"L\n" + 
				"null\n" + 
				"M\n" + 
				"null\n" + 
				"N\n" + 
				"null\n" + 
				"O\n" + 
				"null\n" + 
				"P\n" + 
				"null\n" + 
				"Q\n" + 
				"null\n" + 
				"R\n" + 
				"null\n" + 
				"S\n" + 
				"null\n" + 
				"T\n" + 
				"null\n" + 
				"U\n" + 
				"null\n" + 
				"V\n" + 
				"null\n" + 
				"W\n" + 
				"null\n" + 
				"X\n" + 
				"null\n" + 
				"Y\n" + 
				"null\n" + 
				"Z\n" + 
				"null\n" + 
				"", test.toString());
		
		// testing for when multiple families is added
		test.addPerson("Bart", "Jonathan", "4567891231");
		test.addPerson("Bart", "Aubrey", "4567891232");
		test.addPerson("Bart", "Sean", "4567891233");
		test.addPerson("Brady", "Mike", "5551234571");
		test.addPerson("Brady", "Carol", "5551045839");
		test.addPerson("Brady", "Marcia", "5057071339");
		test.addPerson("Bunyan", "Carl", "5559513570");
		test.addPerson("Bunyan", "William", "5559513571");
		test.addPerson("Bunyan", "Diana", "5559513572");
		test.addPerson("Bob", "Sean", "1237418529");
		test.addPerson("Bob", "Ryan", "1237418528");
		test.addPerson("Bob", "Clancy", "1237418527");
		assertEquals("A\n" + 
				"[John Andrews (5551234567), May Andrews (5551234568), April Andrews (5551234569), August Andrews (5551234570)]\n" + 
				"  null\n" + 
				"  null\n" + 
				"B\n" + 
				"[Jonathan Bart (4567891231), Aubrey Bart (4567891232), Sean Bart (4567891233)]\n" + 
				"  null\n" + 
				"  [Mike Brady (5551234571), Carol Brady (5551045839), Marcia Brady (5057071339)]\n" + 
				"    [Sean Bob (1237418529), Ryan Bob (1237418528), Clancy Bob (1237418527)]\n" + 
				"      null\n" + 
				"      null\n" + 
				"    [Carl Bunyan (5559513570), William Bunyan (5559513571), Diana Bunyan (5559513572)]\n" + 
				"      null\n" + 
				"      null\n" + 
				"C\n" + 
				"null\n" + 
				"D\n" + 
				"null\n" + 
				"E\n" + 
				"null\n" + 
				"F\n" + 
				"null\n" + 
				"G\n" + 
				"null\n" + 
				"H\n" + 
				"null\n" + 
				"I\n" + 
				"null\n" + 
				"J\n" + 
				"null\n" + 
				"K\n" + 
				"null\n" + 
				"L\n" + 
				"null\n" + 
				"M\n" + 
				"null\n" + 
				"N\n" + 
				"null\n" + 
				"O\n" + 
				"null\n" + 
				"P\n" + 
				"null\n" + 
				"Q\n" + 
				"null\n" + 
				"R\n" + 
				"null\n" + 
				"S\n" + 
				"null\n" + 
				"T\n" + 
				"null\n" + 
				"U\n" + 
				"null\n" + 
				"V\n" + 
				"null\n" + 
				"W\n" + 
				"null\n" + 
				"X\n" + 
				"null\n" + 
				"Y\n" + 
				"null\n" + 
				"Z\n" + 
				"null\n" + 
				"", test.toString());
	}
}
