/**
 * WeddingSits - Software Design, 236700 - Technion
 * 
 * Author: Assaf Israel, 2012
 */
package il.ac.technion.wedding;

import com.google.java.contract.Invariant;
import com.google.java.contract.Requires;

@Invariant({
	"!\"\".equals(name)",
	"age > 0"
	})
public class Person {
	public final String name;
	public final int age;
	
	@Requires({
			"_name != null",
			"!_name.isEmpty()",
			"_age > 0"
				})
	public Person(String _name, int _age) {
		this.name = _name;
		this.age = _age;
	}
	
	@Override
	public String toString() {
		return name + " [" + age + "]";
	}
}
