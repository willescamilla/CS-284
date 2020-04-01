package SpecialProject;
/*import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;

public class ListMatrixTest {

	@Test
	public void constructorTest() {
		// testing ListMatrix(rows, columns)
		ListMatrix test1 = new ListMatrix(2, 2);
		assertEquals(2, test1.size());
		assertEquals(2, test1.numRows());
		assertEquals(2, test1.numColumns());
		assertEquals("[[0, 0], [0, 0]]", test1.toString());
		assertEquals(4, test1.getNodeCount());
		
		ListMatrix test2 = new ListMatrix(2, 3);
		assertEquals(2, test2.size());
		assertEquals(2, test2.numRows());
		assertEquals(3, test2.numColumns());
		assertEquals("[[0, 0, 0], [0, 0, 0]]", test2.toString());
		assertEquals(6, test2.getNodeCount());
		
		ListMatrix test3 = new ListMatrix(3, 2);
		assertEquals(3, test3.size());
		assertEquals(3, test3.numRows());
		assertEquals(2, test3.numColumns());
		assertEquals("[[0, 0], [0, 0], [0, 0]]", test3.toString());
		assertEquals(6, test3.getNodeCount());
		
		// testing for edge case when the rows, columns, or both are less than or equal to 0
		try {
			ListMatrix test = new ListMatrix(0, 3);
			fail("Excepted an IllegalArgumentException to be thrown.");
		} catch (IllegalArgumentException e) {
			assertThat(e.getMessage(), is("Parameter is invalid."));
		}
		try {
			ListMatrix test = new ListMatrix(3, 0);
			fail("Expected an IllegalArgumentException to be thrown.");
		} catch (IllegalArgumentException e) {
			assertThat(e.getMessage(), is("Parameter is invalid."));
		}
		try {
			ListMatrix test = new ListMatrix(0, 0);
			fail("Expected an IllegalArgumentException to be thrown.");
		} catch (IllegalArgumentException e) {
			assertThat(e.getMessage(), is("Parameter is invalid."));
		}
		try {
			ListMatrix test = new ListMatrix(-1, 0);
			fail("Expected an IllegalArgumentException to be thrown.");
		} catch (IllegalArgumentException e) {
			assertThat(e.getMessage(), is("Parameter is invalid."));
		}
		try {
			ListMatrix test = new ListMatrix(0, -1);
			fail("Expected an IllegalArgumentException to be thrown.");
		} catch (IllegalArgumentException e) {
			assertThat(e.getMessage(), is("Parameter is invalid."));
		}
		try {
			ListMatrix test = new ListMatrix(-1, -1);
			fail("Expected an IllegalArgumentException to be thrown.");
		} catch (IllegalArgumentException e) {
			assertThat(e.getMessage(), is("Parameter is invalid."));
		}
	}
	
	@Test
	public void addTest() {
		// setting up the test cases
		ListMatrix test1 = new ListMatrix(2, 2);
		ListMatrix test2 = new ListMatrix(2, 2);
		test1.setElem(0, 0, 10);
		test1.setElem(0, 1, 0);
		test1.setElem(1, 0, -4);
		test1.setElem(1, 1, 5);
		test2.setElem(0, 0, -6);
		test2.setElem(0, 1, 3);
		test2.setElem(1, 0, 1);
		test2.setElem(1, 1, -7);
		assertEquals("[[10, 0], [-4, 5]]", test1.toString());
		assertEquals("[[-6, 3], [1, -7]]", test2.toString());
		
		// testing for edge case when the dimensions do not match
		try {
			test1.add(new ListMatrix(1, 2));
			fail("Expected an IllegalArgumentException to be thrown.");
		} catch (IllegalArgumentException e) {
			assertThat(e.getMessage(), is("Dimensions do not properly coincide"));
		}
		try {
			test1.add(new ListMatrix(2, 1));
			fail("Expected an IllegalArgumentException to be thrown.");
		} catch (IllegalArgumentException e) {
			assertThat(e.getMessage(), is("Dimensions do not properly coincide"));
		}
		try {
			test1.add(new ListMatrix(1, 1));
			fail("Expected an IllegalArgumentException to be thrown.");
		} catch (IllegalArgumentException e) {
			assertThat(e.getMessage(), is("Dimensions do not properly coincide"));
		}
		
		// testing for general case
		test1.add(test2);
		assertEquals("[[4, 3], [-3, -2]]", test1.toString());
	}
	
	@Test
	public void transposeTest() {
		// setting up the test cases
		ListMatrix test1 = new ListMatrix(2, 3);
		test1.setElem(0, 0, 6);
		test1.setElem(0, 1, 4);
		test1.setElem(0, 2, 24);
		test1.setElem(1, 0, 1);
		test1.setElem(1, 1, -9);
		test1.setElem(1, 2, 8);
		assertEquals("[[6, 4, 24], [1, -9, 8]]", test1.toString());
		assertEquals(2, test1.size());
		assertEquals(6, test1.getNodeCount());
		assertEquals(2, test1.numRows());
		assertEquals(3, test1.numColumns());
		
		ListMatrix test2 = new ListMatrix(3, 3);
		test2.setElem(0, 0, 1);
		test2.setElem(0, 1, 2);
		test2.setElem(0, 2, 3);
		test2.setElem(1, 0, 4);
		test2.setElem(1, 1, 5);
		test2.setElem(1, 2, 6);
		test2.setElem(2, 0, 7);
		test2.setElem(2, 1, 8);
		test2.setElem(2, 2, 9);
		assertEquals("[[1, 2, 3], [4, 5, 6], [7, 8, 9]]", test2.toString());
		assertEquals(3, test2.size());
		assertEquals(9, test2.getNodeCount());
		assertEquals(3, test2.numRows());
		assertEquals(3, test2.numColumns());
		
		// testing transpose()
		assertEquals("[[6, 1], [4, -9], [24, 8]]", ListMatrix.transpose(test1).toString());
		assertEquals(3, ListMatrix.transpose(test1).size());
		assertEquals(6, ListMatrix.transpose(test1).getNodeCount());
		assertEquals(3, ListMatrix.transpose(test1).numRows());
		assertEquals(2, ListMatrix.transpose(test1).numColumns());
		
		assertEquals("[[1, 4, 7], [2, 5, 8], [3, 6, 9]]", ListMatrix.transpose(test2).toString());
		assertEquals(3, ListMatrix.transpose(test2).size());
		assertEquals(9, ListMatrix.transpose(test2).getNodeCount());
		assertEquals(3, ListMatrix.transpose(test2).numRows());
		assertEquals(3, ListMatrix.transpose(test2).numColumns());
	}
	
	@Test
	public void multiplyTest() {
		// setting up the test cases
		ListMatrix test1 = new ListMatrix(2, 3);
		test1.setElem(0, 0, 1);
		test1.setElem(0, 1, 2);
		test1.setElem(0, 2, 3);
		test1.setElem(1, 0, 4);
		test1.setElem(1, 1, 5);
		test1.setElem(1, 2, 6);
		assertEquals("[[1, 2, 3], [4, 5, 6]]", test1.toString());
		
		ListMatrix test2 = new ListMatrix(3, 2);		
		test2.setElem(0, 0, 6);
		test2.setElem(0, 1, 3);
		test2.setElem(1, 0, 5);
		test2.setElem(1, 1, 2);
		test2.setElem(2, 0, 4);
		test2.setElem(2, 1, 1);
		assertEquals("[[6, 3], [5, 2], [4, 1]]", test2.toString());
		
		ListMatrix test3 = new ListMatrix(2, 2);
		test3.setElem(0, 0, 1);
		test3.setElem(0, 1, 7);
		test3.setElem(1, 0, 2);
		test3.setElem(1, 1, 4);
		assertEquals("[[1, 7], [2, 4]]", test3.toString());
		
		ListMatrix test4 = new ListMatrix(2, 2);
		test4.setElem(0, 0, 3);
		test4.setElem(0, 1, 3);
		test4.setElem(1, 0, 5);
		test4.setElem(1, 1, 2);
		assertEquals("[[3, 3], [5, 2]]", test4.toString());
		
		ListMatrix test5 = new ListMatrix(4, 4);
		test5.setElem(0, 0, 5);
		test5.setElem(0, 1, 2);
		test5.setElem(0, 2, 6);
		test5.setElem(0, 3, 1);
		test5.setElem(1, 0, 0);
		test5.setElem(1, 1, 6);
		test5.setElem(1, 2, 2);
		test5.setElem(1, 3, 0);
		test5.setElem(2, 0, 3);
		test5.setElem(2, 1, 8);
		test5.setElem(2, 2, 1);
		test5.setElem(2, 3, 4);
		test5.setElem(3, 0, 1);
		test5.setElem(3, 1, 8);
		test5.setElem(3, 2, 5);
		test5.setElem(3, 3, 6);
		assertEquals("[[5, 2, 6, 1], [0, 6, 2, 0], [3, 8, 1, 4], [1, 8, 5, 6]]", test5.toString());
		
		ListMatrix test6 = new ListMatrix(4, 4);
		test6.setElem(0, 0, 7);
		test6.setElem(0, 1, 5);
		test6.setElem(0, 2, 8);
		test6.setElem(0, 3, 0);
		test6.setElem(1, 0, 1);
		test6.setElem(1, 1, 8);
		test6.setElem(1, 2, 2);
		test6.setElem(1, 3, 6);
		test6.setElem(2, 0, 9);
		test6.setElem(2, 1, 4);
		test6.setElem(2, 2, 3);
		test6.setElem(2, 3, 8);
		test6.setElem(3, 0, 5);
		test6.setElem(3, 1, 3);
		test6.setElem(3, 2, 7);
		test6.setElem(3, 3, 9);
		assertEquals("[[7, 5, 8, 0], [1, 8, 2, 6], [9, 4, 3, 8], [5, 3, 7, 9]]", test6.toString());
		
		ListMatrix test7 = new ListMatrix(1, 3);
		test7.setElem(0, 0, 3);
		test7.setElem(0, 1, 4);
		test7.setElem(0, 2, 2);
		assertEquals("[[3, 4, 2]]", test7.toString());
		
		ListMatrix test8 = new ListMatrix(3, 4);
		test8.setElem(0, 0, 13);
		test8.setElem(0, 1, 9);
		test8.setElem(0, 2, 7);
		test8.setElem(0, 3, 15);
		test8.setElem(1, 0, 8);
		test8.setElem(1, 1, 7);
		test8.setElem(1, 2, 4);
		test8.setElem(1, 3, 6);
		test8.setElem(2, 0, 6);
		test8.setElem(2, 1, 4);
		test8.setElem(2, 2, 0);
		test8.setElem(2, 3, 3);
		assertEquals("[[13, 9, 7, 15], [8, 7, 4, 6], [6, 4, 0, 3]]", test8.toString());
		
		// testing for edge case when the dimensions for matrix multiplication don't match
		try {
			test1.multiply(new ListMatrix(2, 3));
			fail("Expected an IllegalArgumentException to be thrown.");
		} catch (IllegalArgumentException e) {
			assertThat(e.getMessage(), is("Dimensions do not properly coincide"));
		}
		
		// testing for general case
		assertEquals("[[28, 10], [73, 28]]", test1.multiply(test2).toString());
		assertEquals("[[38, 17], [26, 14]]", test3.multiply(test4).toString());
		assertEquals("[[96, 68, 69, 69], [24, 56, 18, 52], [58, 95, 71, 92], [90, 107, 81, 142]]", test5.multiply(test6).toString());
	}
}*/