/**
 * WeddingSits - Software Design, 236700 - Technion
 * 
 * Author: Assaf Israel, 2012
 */
package il.ac.technion.heuristic;

import static org.junit.Assert.assertTrue;
import il.ac.technion.heuristic.SeatingArrangements;
import il.ac.technion.wedding.Guest;

import org.junit.Test;

public class SeatingArrangementTest {

	@Test
	public void testAddGuest() {
		Guest groom = new Guest("Shrek",25);
		Guest bride = new Guest("Fiona",25);
		SeatingArrangements sa = new SeatingArrangements(groom,bride,1);
		Guest g1 = new Guest("Donkey",10);
		Guest g2 = new Guest("Puss in Boots",7);
		sa.addGuest(g1);
		sa.addGuest(g2);
		assertTrue(sa.guests().contains(groom));
		assertTrue(sa.guests().contains(g1));
	}
}
