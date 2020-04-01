package SpecialProject;
/*import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import java.util.List;
import java.util.ArrayList;

import org.junit.Test;

public class ListCollectionTest {

	@Test
	public void addListTest() {
		// setting up the test cases
		ListCollection<Integer> testCollection = new ListCollection<>();
		SingleLL<Integer> testList1 = new SingleLL<>();
		SingleLL<Integer> testList2 = new SingleLL<>();
		List<Integer> testList3 = new ArrayList<>();
		for (int i = 1; i < 11; i++)
			testList1.append(i);
		for (int i = 11; i < 21; i++)
			testList2.append(i);
		for (int i = 21; i < 31; i++)
			testList3.add(i);
		
		// testing addList(SingleLL<E>)
		testCollection.addList(testList1);
		assertEquals("[[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]]", testCollection.toString());
		assertEquals(10, testCollection.getNodeCount());
		testCollection.addList(testList2);
		assertEquals("[[1, 2, 3, 4, 5, 6, 7, 8, 9, 10], [11, 12, 13, 14, 15, 16, 17, 18, 19, 20]]", testCollection.toString());
		assertEquals(20, testCollection.getNodeCount());
		
		// testing addList(List<E>)
		testCollection.addList(testList3);
		assertEquals("[[1, 2, 3, 4, 5, 6, 7, 8, 9, 10], [11, 12, 13, 14, 15, 16, 17, 18, 19, 20], [21, 22, 23, 24, 25, 26, 27, 28, 29, 30]]", testCollection.toString());
		assertEquals(30, testCollection.getNodeCount());
		
		// more testing for addList(SingleLL<E>) & addList(List<E>)
		testCollection.addList(new SingleLL<Integer>());
		assertEquals("[[1, 2, 3, 4, 5, 6, 7, 8, 9, 10], [11, 12, 13, 14, 15, 16, 17, 18, 19, 20], [21, 22, 23, 24, 25, 26, 27, 28, 29, 30], []]", testCollection.toString());
		assertEquals(30, testCollection.getNodeCount());
		List<Integer> testList4 = new ArrayList<>();
		testCollection.addList(testList4);
		assertEquals("[[1, 2, 3, 4, 5, 6, 7, 8, 9, 10], [11, 12, 13, 14, 15, 16, 17, 18, 19, 20], [21, 22, 23, 24, 25, 26, 27, 28, 29, 30], [], []]", testCollection.toString());
		assertEquals(30, testCollection.getNodeCount());
		testCollection.addList(new ArrayList<Integer>());
		assertEquals("[[1, 2, 3, 4, 5, 6, 7, 8, 9, 10], [11, 12, 13, 14, 15, 16, 17, 18, 19, 20], [21, 22, 23, 24, 25, 26, 27, 28, 29, 30], [], [], []]", testCollection.toString());
		assertEquals(30, testCollection.getNodeCount());
	}
	
	@Test
	public void sizeTest() {
		ListCollection<Integer> testCollection = new ListCollection<>();
		
		// testing size() when the collection is empty
		assertEquals(0, testCollection.size());
		
		// setting up a test case
		SingleLL<Integer> testList1 = new SingleLL<>();
		for (int i = 1; i <= 5; i++)
			testList1.append(i);
		
		// testing size() when the collection is non-empty
		testCollection.addList(testList1);
		assertEquals(1, testCollection.size());
		
		// setting up another test case
		SingleLL<Integer> testList2 = new SingleLL<>();
		for (int i = 6; i <= 10; i++)
			testList2.append(i);
		
		// testing size() when the collection is non-empty
		testCollection.addList(testList2);
		assertEquals(2, testCollection.size());
		
		// more test cases for size()
		SingleLL<Integer> testList3 = new SingleLL<>();
		testCollection.addList(testList3);
		assertEquals(3, testCollection.size());
		
		List<Integer> testList4 = new ArrayList<>();
		for (int i = 11; i <= 15; i++)
			testList4.add(i);
		testCollection.addList(testList4);
		assertEquals(4, testCollection.size());
	}
	
	@Test
	public void setAndGetNodeCountTest() {
		// testing getNodeCount() when the collection is empty
		ListCollection<Integer> testCollection = new ListCollection<>();
		assertEquals(0, testCollection.getNodeCount());
		
		// testing setNodeCount() in general
		testCollection.setNodeCount(5);
		assertEquals(5, testCollection.getNodeCount());
		
		// testing getNodeCount() when the collection is non-empty and setNodeCount() in general
		SingleLL<Integer> testList1 = new SingleLL<>();
		testCollection.addList(testList1);
		assertEquals(5, testCollection.getNodeCount());
		SingleLL<Integer> testList2 = new SingleLL<>();
		for (int i = 1; i <= 5; i++)
			testList2.append(i);
		testCollection.addList(testList2);
		assertEquals(10, testCollection.getNodeCount());
	}
	
	@Test
	public void getList() {
		ListCollection<Integer> testCollection1 = new ListCollection<>();
		
		// testing for edge case when listIndex < 0
		try {
			testCollection1.getList(-1);
			fail("Expected an IllegalArgumentException to be thrown.");
		} catch (IllegalArgumentException e) {
			assertThat(e.getMessage(), is("Index is out of bounds."));
		}
		// testing for edge case when listIndex >= collection.size()
		try {
			testCollection1.getList(0);
			fail("Expected an IllegalArgumentException to be thrown.");
		} catch (IllegalArgumentException e) {
			assertThat(e.getMessage(), is("Index is out of bounds."));
		}
		try {
			testCollection1.getList(1);
			fail("Expected an IllegalArgumentException to be thrown.");
		} catch (IllegalArgumentException e) {
			assertThat(e.getMessage(), is("Index is out of bounds."));
		}
		
		ListCollection<Integer> testCollection2 = new ListCollection<>();
		// setting up the general cases
		SingleLL<Integer> testList1 = new SingleLL<>();
		SingleLL<Integer> testList2 = new SingleLL<>();
		List<Integer> testList3 = new ArrayList<>();
		for (int i = 1; i <= 5; i++)
			testList1.append(i);
		for (int i = 6; i <= 10; i++)
			testList2.append(i);
		for (int i = 11; i <= 15; i++)
			testList3.add(i);
		
		// testing getList() when the collection is non-empty
		testCollection2.addList(testList1);
		assertEquals("[1, 2, 3, 4, 5]", testCollection2.getList(0).toString());
		testCollection2.addList(testList2);
		testCollection2.addList(testList3);
		assertEquals("[1, 2, 3, 4, 5]", testCollection2.getList(0).toString());
		assertEquals("[6, 7, 8, 9, 10]", testCollection2.getList(1).toString());
		assertEquals("[11, 12, 13, 14, 15]", testCollection2.getList(2).toString());
		testCollection2.addList(new SingleLL<Integer>());
		assertEquals("[]", testCollection2.getList(3).toString());
	}
	
	@Test
	public void addElemTest() {
		ListCollection<Integer> testCollection1 = new ListCollection<>();
		
		// testing for edge case when listIndex < 0
		try {
			testCollection1.addElem(-1, 0, 0);
			fail("Expected an IllegalArgumentException to be thrown.");
		} catch (IllegalArgumentException e) {
			assertThat(e.getMessage(), is("Index is out of bounds."));
		}
		// testing for edge case when listIndex >= collection.size()
		try {
			testCollection1.addElem(0, 0, 0);
			fail("Expected an IllegalArgumentException to be thrown.");
		} catch (IllegalArgumentException e) {
			assertThat(e.getMessage(), is("Index is out of bounds."));
		}
		try {
			testCollection1.addElem(1, 0, 0);
			fail("Expected an IllegalArgumentException to be thrown.");
		} catch (IllegalArgumentException e) {
			assertThat(e.getMessage(), is("Index is out of bounds."));
		}
		testCollection1.addList(new SingleLL<Integer>());
		// testing for edge case when elemIndex < 0
		try {
			testCollection1.addElem(0, -1, 0);
			fail("Expected an IllegalArgumentException to be thrown.");
		} catch (IllegalArgumentException e) {
			assertThat(e.getMessage(), is("Index is out of bounds."));
		}
		try {
			testCollection1.addElem(0, 1, 0);
			fail("Expected an IllegalArgumentException to be thrown.");
		} catch (IllegalArgumentException e) {
			assertThat(e.getMessage(), is("Index is out of bounds."));
		}
		
		// setting up the general cases
		ListCollection<Integer> testCollection2 = new ListCollection<>();
		SingleLL<Integer> testList1 = new SingleLL<>();
		for (int i = 1; i <= 3; i++)
			testList1.append(i);
		testCollection2.addList(testList1);
		
		// testing addElem() when the collection is non-empty
		assertEquals("[[1, 2, 3]]", testCollection2.toString());
		assertEquals(3, testCollection2.getNodeCount());
		testCollection2.addElem(0, 0, 0);
		assertEquals("[[0, 1, 2, 3]]", testCollection2.toString());
		assertEquals(4, testCollection2.getNodeCount());
		
		// setting up more test cases
		SingleLL<Integer> testList2 = new SingleLL<>();
		SingleLL<Integer> testList3 = new SingleLL<>();
		testList1.append(4);
		testCollection2.setNodeCount(5);
		for (int i = 5; i <= 8; i++)
			testList2.append(i);
		for (int i = 9; i <= 12; i++)
			testList3.append(i);
		testCollection2.addList(testList2);
		testCollection2.addList(testList3);
		
		// testing addElem() when the collection is non-empty
		assertEquals("[[0, 1, 2, 3, 4], [5, 6, 7, 8], [9, 10, 11, 12]]", testCollection2.toString());
		assertEquals(13, testCollection2.getNodeCount());
		testCollection2.addElem(1, 2, 3);
		assertEquals("[[0, 1, 2, 3, 4], [5, 6, 3, 7, 8], [9, 10, 11, 12]]", testCollection2.toString());
		assertEquals(14, testCollection2.getNodeCount());
	}
	
	@Test
	public void setElemTest() {
		ListCollection<Integer> testCollection1 = new ListCollection<>();
		
		// testing for edge case when listIndex < 0
		try {
			testCollection1.setElem(-1, 0, 0);
			fail("Expected an IllegalArgumentException to be thrown.");
		} catch (IllegalArgumentException e) {
			assertThat(e.getMessage(), is("Index is out of bounds."));
		}
		// testing for edge case when listIndex >= collection.size()
		try {
			testCollection1.setElem(0, 0, 0);
			fail("Expected an IllegalArgumentException to be thrown.");
		} catch (IllegalArgumentException e) {
			assertThat(e.getMessage(), is("Index is out of bounds."));
		}
		try {
			testCollection1.setElem(1, 0, 0);
			fail("Expected an IllegalArgumentException to be thrown.");
		} catch (IllegalArgumentException e) {
			assertThat(e.getMessage(), is("Index is out of bounds."));
		}
		testCollection1.addList(new SingleLL<Integer>());
		// testing for edge case when elemIndex < 0
		try {
			testCollection1.setElem(0, -1, 0);
			fail("Expected an IllegalArgumentException to be thrown.");
		} catch (IllegalArgumentException e) {
			assertThat(e.getMessage(), is("Out of bounds"));
		}
		// testing for edge case when elemIndex >= SingleLL.size()
		try {
			testCollection1.setElem(0, 0, 0);
			fail("Expected an IllegalArgumentException to be thrown.");
		} catch (IllegalArgumentException e) {
			assertThat(e.getMessage(), is("Out of bounds"));
		}
		try {
			testCollection1.setElem(0, 1, 0);
			fail("Expected an IllegalArgumentException to be thrown.");
		} catch (IllegalArgumentException e) {
			assertThat(e.getMessage(), is("Out of bounds"));
		}
		
		// setting up the general cases
		ListCollection<Integer> testCollection2 = new ListCollection<>();
		SingleLL<Integer> testList1 = new SingleLL<>();
		for (int i = 1; i <= 3; i++)
			testList1.append(i);
		testCollection2.addList(testList1);
		
		// testing setElem() when the collection is non-empty
		assertEquals("[[1, 2, 3]]", testCollection2.toString());
		testCollection2.setElem(0, 0, 0);
		assertEquals("[[0, 2, 3]]", testCollection2.toString());
		testCollection2.setElem(0, 0, 1);
		
		// setting up more test cases
		SingleLL<Integer> testList2 = new SingleLL<>();
		SingleLL<Integer> testList3 = new SingleLL<>();
		testList1.append(4);
		for (int i = 5; i <= 8; i++)
			testList2.append(i);
		for (int i = 9; i <= 12; i++)
			testList3.append(i);
		testCollection2.addList(testList2);
		testCollection2.addList(testList3);
		
		// testing setElem() when the collection is non-empty
		assertEquals("[[1, 2, 3, 4], [5, 6, 7, 8], [9, 10, 11, 12]]", testCollection2.toString());
		testCollection2.setElem(1, 2, 3);
		assertEquals("[[1, 2, 3, 4], [5, 6, 3, 8], [9, 10, 11, 12]]", testCollection2.toString());
	}
	
	@Test
	public void getElemTest() {
		ListCollection<Integer> testCollection1 = new ListCollection<>();
		
		// testing for edge case when listIndex < 0
		try {
			testCollection1.getElem(-1, 0);
			fail("Expected an IllegalArgumentException to be thrown.");
		} catch (IllegalArgumentException e) {
			assertThat(e.getMessage(), is("Index is out of bounds."));
		}
		// testing for edge case when listIndex >= collection.size()
		try {
			testCollection1.getElem(0, 0);
			fail("Expected an IllegalArgumentException to be thrown.");
		} catch (IllegalArgumentException e) {
			assertThat(e.getMessage(), is("Index is out of bounds."));
		}
		try {
			testCollection1.getElem(1, 0);
			fail("Expected an IllegalArgumentException to be thrown.");
		} catch (IllegalArgumentException e) {
			assertThat(e.getMessage(), is("Index is out of bounds."));
		}
		testCollection1.addList(new SingleLL<Integer>());
		// testing for edge case when elemIndex < 0
		try {
			testCollection1.getElem(0, -1);
			fail("Expected an IllegalArgumentException to be thrown.");
		} catch (IllegalArgumentException e) {
			assertThat(e.getMessage(), is("Out of bounds"));
		}
		// testing for edge case when elemIndex >= SingleLL.size()
		try {
			testCollection1.getElem(0, 0);
			fail("Expected an IllegalArgumentException to be thrown.");
		} catch (IllegalArgumentException e) {
			assertThat(e.getMessage(), is("Out of bounds"));
		}
		try {
			testCollection1.getElem(0, 1);
			fail("Expected an IllegalArgumentException to be thrown.");
		} catch (IllegalArgumentException e) {
			assertThat(e.getMessage(), is("Out of bounds"));
		}
		
		// setting up the general cases
		ListCollection<Integer> testCollection2 = new ListCollection<>();
		SingleLL<Integer> testList1 = new SingleLL<>();
		for (int i = 1; i <= 3; i++)
			testList1.append(i);
		testCollection2.addList(testList1);
		
		// testing getElem() when the collection is non-empty
		assertEquals("[[1, 2, 3]]", testCollection2.toString());
		assertEquals(new Integer(1), testCollection2.getElem(0, 0));
		assertEquals(new Integer(2), testCollection2.getElem(0, 1));
		assertEquals(new Integer(3), testCollection2.getElem(0, 2));
		
		
		// setting up more test cases
		SingleLL<Integer> testList2 = new SingleLL<>();
		SingleLL<Integer> testList3 = new SingleLL<>();
		testList1.append(4);
		for (int i = 5; i <= 8; i++)
			testList2.append(i);
		for (int i = 9; i <= 12; i++)
			testList3.append(i);
		testCollection2.addList(testList2);
		testCollection2.addList(testList3);
		
		// testing getElem() when the collection is non-empty
		assertEquals("[[1, 2, 3, 4], [5, 6, 7, 8], [9, 10, 11, 12]]", testCollection2.toString());
		assertEquals(new Integer(1), testCollection2.getElem(0, 0));
		assertEquals(new Integer(2), testCollection2.getElem(0, 1));
		assertEquals(new Integer(3), testCollection2.getElem(0, 2));
		assertEquals(new Integer(4), testCollection2.getElem(0, 3));
		assertEquals(new Integer(5), testCollection2.getElem(1, 0));
		assertEquals(new Integer(6), testCollection2.getElem(1, 1));
		assertEquals(new Integer(7), testCollection2.getElem(1, 2));
		assertEquals(new Integer(8), testCollection2.getElem(1, 3));
		assertEquals(new Integer(9), testCollection2.getElem(2, 0));
		assertEquals(new Integer(10), testCollection2.getElem(2, 1));
		assertEquals(new Integer(11), testCollection2.getElem(2, 2));
		assertEquals(new Integer(12), testCollection2.getElem(2, 3));
	}
	
	@Test
	public void toStringTest() {
		// setting up test cases
		ListCollection<Integer> testCollection = new ListCollection<>();
		SingleLL<Integer> testList1 = new SingleLL<>();
		SingleLL<Integer> testList2 = new SingleLL<>();
		SingleLL<Integer> testList3 = new SingleLL<>();
		
		// testing toString() when the collection is empty
		assertEquals("[]", testCollection.toString());
		
		// testing toString() when the collection is non-empty
		testCollection.addList(testList1);
		assertEquals("[[]]", testCollection.toString());
		
		// more testing for toString()
		testCollection.addList(testList2);
		testCollection.addList(testList3);
		assertEquals("[[], [], []]", testCollection.toString());
		
		// more testing for toString()
		for (int i = 1; i <= 4; i++)
			testList1.append(i);
		for (int i = 5; i <= 8; i++)
			testList2.append(i);
		testList3.append(9);
		for (int i = 0; i < 3; i++)
			testList3.append(i);
		assertEquals("[[1, 2, 3, 4], [5, 6, 7, 8], [9, 0, 1, 2]]", testCollection.toString());
	}
	
	@Test
	public void miscTest() {
		// testing the ListCollection(numLists) constructor because I never did in any other test
		
		ListCollection<Integer> testCollection = new ListCollection<>(3);
		assertEquals(0, testCollection.getNodeCount());
		assertEquals(3, testCollection.size());
		
		try {
			ListCollection<Integer> test = new ListCollection<>(-1);
			fail("Expected an IllegalArgumentException to be thrown.");
		} catch (IllegalArgumentException e) {
			assertThat(e.getMessage(), is("Parameter is invalid."));
		}
	}
}*/