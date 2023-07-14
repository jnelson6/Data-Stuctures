package homework4;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestTreap {

	@Test
	void test() {
		Treap<Integer> testTree = new Treap<Integer>();
		testTree.add(4,81);
	
		testTree.add(2,69);

		testTree.add(6,30);

		testTree.add(1,16);
	
		testTree.add(3,88);
	
		testTree.add(5,17);
		
		testTree.add(7,74);
		System.out.println(testTree.toString());
		
		testTree.delete(5);
		System.out.println(testTree.toString());
		
		testTree.delete(1);
		System.out.println(testTree.toString());
		



		
		
		
	
}
}
