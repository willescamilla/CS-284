package HW4;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TreapTest {

	@Test
	void test1() {
		Treap<Integer> test = new Treap<Integer>();
		assertTrue(test.add(4,19)); 
		assertTrue(test.add(2,31));
		assertTrue(test.add(6,70)); 
		assertTrue(test.add(1,84));
		assertTrue(test.add(3,12)); 
		assertTrue(test.add(5,83));
		assertTrue(test.add(7,26));
		assertTrue(test.find(7));
		assertFalse(test.find(90));
		assertTrue(test.delete(7));
		assertFalse(test.delete(55));
	}
	
	void test2() {
		Treap<Integer> test = new Treap<Integer>();
		assertTrue(test.add(8,12)); 
		assertTrue(test.add(3,55));
		assertTrue(test.add(90,7)); 
		assertTrue(test.add(11,84));
		assertTrue(test.add(34,3)); 
		assertTrue(test.add(5,81));
		assertTrue(test.add(7,268));
		assertTrue(test.find(34));
		assertFalse(test.find(9));
		assertTrue(test.delete(34));
		assertFalse(test.delete(55));
		assertFalse(test.find(34));
	}
	
	void test3() {
		Treap<Integer> test = new Treap<Integer>();
		assertTrue(test.add(6, 7)); 
		assertTrue(test.add(4, 77));
		assertTrue(test.add(9, 0)); 
		assertTrue(test.add(12, 3));
		assertTrue(test.add(8, 65)); 
		assertTrue(test.add(52,8));
		assertTrue(test.add(7,34));
		assertTrue(test.find(54));
		assertFalse(test.find(13));
		assertTrue(test.delete(12));
		assertFalse(test.delete(78));
		assertFalse(test.find(12));
	}
	

}