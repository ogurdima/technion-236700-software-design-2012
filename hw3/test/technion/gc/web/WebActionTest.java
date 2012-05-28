package technion.gc.web;

import org.junit.Test;

public class WebActionTest {

	@Test
	public void invokeNullArgumentTest() {
		WebAction a = new WebAction();
		a.invoke(null);
	}
	
	@Test
	public void invokeNodeWithNullUrlTest() {
		WebAction a = new WebAction();
		WebNode n = new WebNode(null);
		a.invoke(n);
	}
	
	@Test
	public void invokeNodeWithBadUrlTest() {
		WebAction a = new WebAction();
		WebNode n = new WebNode("aaa");
		a.invoke(n);
	}
}
