/**
 * WeddingSits - Software Design, 236700 - Technion
 * 
 * Author: Assaf Israel, 2012
 */
package il.ac.technion.heuristic;

import il.ac.technion.wedding.Guest;
import il.ac.technion.wedding.Person;
import il.ac.technion.wedding.Table;

import java.util.Collection;
import java.util.LinkedList;

import com.google.java.contract.Ensures;
import com.google.java.contract.Invariant;
import com.google.java.contract.Requires;

@Invariant({ "tables != null", "guests != null", "groom != null", "bride != null" })
public class SeatingArrangements {

	private final Person groom, bride;

	private Collection<Table> tables = new LinkedList<Table>();
	private Collection<Guest> guests = new LinkedList<Guest>();

	private final int tablesCapacity;
	private final static String delim = System.getProperty("line.separator");
	
	@Requires({	"groom != null",
				"bride != null",
				"groom != bride",
				"tablesCapacity > 0"
	})
	@Ensures("guests.contains(groom) && guests.contains(bride)")
	public SeatingArrangements(Guest groom, Guest bride, int tablesCapacity) {
		this.groom = groom;
		this.bride = bride;
		this.tablesCapacity = tablesCapacity;
		addGuest(groom);
		addGuest(bride);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("Welcome to the wedding of "
				+ groom.name + " & " + bride.name + delim);
		sb.append("======= Wedding Summary ======" + delim);
		sb.append("# tables: " + tables.size() + delim);
		sb.append("# guests: " + guests.size() + delim);
		sb.append("======= Tables =======" + delim);
		for (Table t : tables) {
			sb.append(t + delim);
		}
		sb.append("======= Guests =======" + delim);
		for (Guest g : guests) {
			sb.append(g + delim);
		}
		return sb.toString();
	}

	/**
	 * Adds a guest to the seating arrangement. The table assignment is
	 * implemented as a (very) simple Local-Search heuristic. <code>g</code> is
	 * assigned to a new table. Then the algorithm tries to transfer the guest to
	 * an existing table, if possible.
	 */
	@Requires("!guests.contains(g)")
	@Ensures("guests.contains(g)")
	public void addGuest(Guest g) {
		guests.add(g);

		Table newTable = new Table(tables.size() + 1, tablesCapacity);
		newTable.addGuest(g);

		for (Table t : tables) {
			if (t.moveFrom(g, newTable)) {
				return;
			}
		}

		tables.add(newTable);
	}

	/**
	 * @return The guests seated at the wedding.
	 */
	@Ensures("result != null && result.size() >= 2")
	public Collection<Guest> guests() {
		return guests;
	}
	
	public Collection<Table> tables() {
		return tables; 
	}
}
