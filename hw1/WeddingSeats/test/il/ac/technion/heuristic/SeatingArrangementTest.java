/**
 * WeddingSits - Software Design, 236700 - Technion
 * 
 * Author: Assaf Israel, 2012
 */
package il.ac.technion.heuristic;

import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.Random;

import il.ac.technion.heuristic.SeatingArrangements;
import il.ac.technion.wedding.Guest;
import il.ac.technion.wedding.Table;

import org.junit.Test;

public class SeatingArrangementTest {

	@Test
	public void testAddGuest() {
		Guest groom = new Guest("Shrek", 25);
		Guest bride = new Guest("Fiona", 25);
		SeatingArrangements sa = new SeatingArrangements(groom, bride, 1);
		Guest g1 = new Guest("Donkey", 10);
		Guest g2 = new Guest("Puss in Boots", 7);
		sa.addGuest(g1);
		sa.addGuest(g2);
		assertTrue(sa.guests().contains(groom));
		assertTrue(sa.guests().contains(g1));
	}

	@Test
	public void testArrangement() {
		final int NUM_OF_GUESTS = 100;
		Guest groom = new Guest("Shrek", 25);
		Guest bride = new Guest("Fiona", 25);
		SeatingArrangements sa = new SeatingArrangements(groom, bride, 4);
		Guest[] guestArr = new Guest[NUM_OF_GUESTS];
		for (int i = 0; i < guestArr.length; i++) {
			guestArr[i] = new Guest("GG " + i, i + 1);
		}

		Random rd = new Random();

		for (int i = 0; i < guestArr.length; i++) {
			Guest tg = guestArr[i];
			for (int j = 0; j < guestArr.length; j++) {
				if (i == j)
					continue;

				if (rd.nextBoolean()) {
					tg.dislike(guestArr[j]);
				}
			}
		}

		for (Guest g : guestArr) {
			sa.addGuest(g);
		}

		Collection<Guest> cg = sa.guests();
		Collection<Table> ct = sa.tables();

		for (Guest g : guestArr) {
			assertTrue(cg.contains(g));
		}
		
		for (Table t : ct) {
			Collection<Guest> gsts = t.getGuests();
			for (Guest g1 : gsts) {
				for (Guest g2 : gsts) {
					if (g1 == g2) continue;
					assertTrue(! g1.getDislikes().contains(g2));
				}
			}
		}

	}
}
