/**
 * WeddingSits - Software Design, 236700 - Technion
 * 
 * Author: Assaf Israel, 2012
 */
package il.ac.technion.wedding;

import static org.junit.Assert.assertTrue;
import il.ac.technion.wedding.Guest;
import il.ac.technion.wedding.Person;

import org.junit.Test;

import com.google.java.contract.PreconditionError;


public class GuestTest {
	
	@Test
	public void testDislikes() {
		Guest g1 = new Guest("g1",20);
		Guest g2 = new Guest("g2",25);
		Person p1 = new Person("p1",28);
		
		g1.dislike(g2);
		assertTrue(g1.getDislikes().contains(g2));
		g1.dislike(p1);
		assertTrue(g1.getDislikes().contains(p1));
		assertTrue(g1.getDislikes().contains(g2));
	}
	
	@Test(expected = PreconditionError.class)
	public void testEmptyName() {
		new Guest("", 1);
	}
	
	@Test(expected = PreconditionError.class)
	public void testNullName() {
		new Guest(null, 1);
	}
	@Test(expected = PreconditionError.class)
	public void testNegativeAge() {
		new Guest(null, -1);
	}
	@Test(expected = PreconditionError.class)
	public void testNullAge() {
		new Guest(null, 0);
	}
	
	@Test(expected = PreconditionError.class)
	public void testNullDislike() {
		Guest g = new Guest("tg", 1);
		g.dislike(null);
	}
	@Test(expected = PreconditionError.class)
	public void testSelfDislike() {
		Guest g = new Guest("tg", 1);
		g.dislike(g);
	}
	
	@Test
	public void testGetDislikeUnique() {
		Guest g = new Guest("tg", 1);
		Guest gg = new Guest("tg", 1);
		g.dislike(gg);
		assertTrue(g.getDislikes().contains(gg));
		assertTrue(! g.getDislikes().contains(g));
	}
	
	@Test
	public void testGetDislikeUniqueCollections() {
		Guest g = new Guest("tg", 1);
		Guest gg = new Guest("tg", 1);
		g.dislike(gg);
		assertTrue(! (g.getDislikes() == g.getDislikes()) );
	}
}
