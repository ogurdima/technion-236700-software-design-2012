package il.ac.technion.wedding;



import static org.junit.Assert.*;

import com.google.java.contract.PreconditionError;


import org.junit.Test;

public class TableTest {

	@Test(expected = PreconditionError.class)
	public void testNegativeCapacity() {
		new Table(1, -1);
	}
	@Test(expected = PreconditionError.class)
	public void testZeroCapacity() {
		new Table(1, 0);
	}
	
	@Test
	public void testValuesAreSet() {
		int capacity = 2, id = 1;
		Table t = new Table(id, capacity);
		assertEquals(t.capacity, capacity);
		assertEquals(t.id, id);
	}

}
