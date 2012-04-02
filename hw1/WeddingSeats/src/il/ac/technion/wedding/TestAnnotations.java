package il.ac.technion.wedding;

import com.google.java.contract.Ensures;
import com.google.java.contract.Requires;

public class TestAnnotations {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int x = 5;
		System.out.println(f(x));
		System.out.println("Got here and did not get an exception");
	}
	
	@Requires("x < 0")
	@Ensures("result < 0")
	public static int f(int x) {
		return -x;
	}

}
