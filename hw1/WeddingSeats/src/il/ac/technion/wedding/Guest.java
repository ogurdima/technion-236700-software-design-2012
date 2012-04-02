/**
 * WeddingSits - Software Design, 236700 - Technion
 * 
 * Author: Assaf Israel, 2012
 */
package il.ac.technion.wedding;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

import com.google.java.contract.Ensures;
import com.google.java.contract.Invariant;
import com.google.java.contract.Requires;

@Invariant("dislikes != null")
public class Guest extends Person {

	private Collection<Person> dislikes = new LinkedList<Person>();

	@Requires({ "_name != null", "!_name.isEmpty()", "_age > 0" })
	public Guest(String _name, int _age) {
		super(_name, _age);
	}

	@Override
	public String toString() {
		return "Wedding guest: " + super.toString();
	}

	/**
	 * Marks <code>p</code> as disliked by this guest.
	 */
	@Ensures({ "dislikes.contains(p)",
			"dislikes.size() == old(dislikes.size()) + 1" })
	@Requires({ "p != null",
				"p != (Person) this",
				"!dislikes.contains(p)" })
	public void dislike(Person p) {
		dislikes.add(p);

	}

	/**
	 * Returns a collection the disliked people by this guest.
	 */
	@Ensures({ "result != dislikes", "result.equals(dislikes)" })
	public Collection<Person> getDislikes() {
		return new ArrayList<Person>(dislikes);
	}
}
