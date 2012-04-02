/**
 * WeddingSits - Software Design, 236700 - Technion
 * 
 * Author: Assaf Israel, 2012
 */
package il.ac.technion.wedding;

import java.util.ArrayList;
import java.util.Collection;

import com.google.java.contract.Ensures;
import com.google.java.contract.Invariant;
import com.google.java.contract.Requires;

/**
 * Represents a wedding table with a predefined capacity. Guests can be seated
 * at the table as long as everyone gets along.
 */
@Invariant({ 	"capacity > 0",
				"guests != null"
		})
public class Table {
	public final int capacity;
	public final int id;
	private Collection<Guest> guests;
	
	@Requires({	"_capacity > 0",
		
	})
	public Table(int _id, int _capacity) {
		this.id = _id;
		this.capacity = _capacity;
		this.guests = new ArrayList<Guest>(capacity);
	}

	@Override
	public String toString() {
		return "Number of guests at table #" + id + ": " + guests.size();
	}

	/**
	 * @return The guests seated at this table.
	 */
	public Collection<Guest> getGuests() {
		return guests;
	}

	/**
	 * @return The number of guests seated at this table.
	 */
	@Ensures("result >= 0")
	public int size() {
		return guests.size();
	}

	/**
	 * Adds a wedding guest <code>g</code> to the table. The guests sitting at
	 * the table must all like each other after this operation. If this is not
	 * possible the operation fails.
	 * 
	 * @param g
	 *            The wedding guest to be seated at this table.
	 * @return <b>true</b> if operation succeeded, <b>false</b> otherwise.
	 */
	public boolean addGuest(Guest g) {
		if (guests.size() == capacity) {
			return false;
		}
		for (Person p : g.getDislikes()) {
			if (guests.contains(p)) {
				return false;
			}
		}
		return guests.add(g);
	}

	/**
	 * Move a wedding guest <code>g</code> from table <code>t</code> to the
	 * current table. If <code>g</code> cannot be seated at this table for
	 * whatever reason, the operation fails.
	 * 
	 * @param g
	 *            The guest to be moved to this table.
	 * @param t
	 *            The source Table from which <code>g</code> will be moved.
	 * @return <b>true</b> if operation succeeded, <b>false</b> otherwise.
	 */
	@Ensures("!t.guests.contains(g) || guests.contains(g)")
	@Requires({	"g != null",
				"t != null"
	})
	public boolean moveFrom(Guest g, Table t) {
		if (t == this) 
				return true;
		if (!t.guests.contains(g)) {
			return false;
		}
		t.guests.remove(g);
		return addGuest(g);
	}
}
