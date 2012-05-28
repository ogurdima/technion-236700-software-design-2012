package technion.gc.web;

import static org.junit.Assert.*;

import org.junit.Test;

public class WebStrategyTest {

	@Test
	public void badDepthTest1() {
		WebStrategy s = new WebStrategy(-42);
		assertEquals(1, s.maxDepth);
		assertEquals(null, s.nextNode());
	}
	@Test
	public void badDepthTest2() {
		WebStrategy s = new WebStrategy(0, null);
		assertEquals(1, s.maxDepth);
		assertEquals(null, s.nextNode());
	}
	
	@Test
	public void badDepthTest3() {		
		WebNode n = new WebNode("aaa");
		WebStrategy s = new WebStrategy(-42, n);
		assertEquals(1, s.maxDepth);
		assertEquals(n, s.nextNode());
	}
	
	@Test
	public void nullPointerTest1() {
		WebStrategy s = new WebStrategy(42, null);
		assertEquals(42, s.maxDepth);
		assertEquals(null, s.nextNode());
	}
	
	@Test
	public void nullPointerTest2() {
		WebStrategy s = new WebStrategy(42);
		s.startNewCrawl(null);
		assertEquals(42, s.maxDepth);
		assertEquals(null, s.nextNode());
	}
	
	@Test
	public void Test1() {
		WebNode n = new WebNode("aaa");
		WebStrategy s = new WebStrategy(42, n);
		assertEquals(42, s.maxDepth);
		assertEquals(n, s.nextNode());
		assertEquals(null, s.nextNode());
	}
	
	@Test
	public void Test2() {
		WebNode n = new WebNode("aaa");
		WebStrategy s = new WebStrategy(42);
		s.startNewCrawl(n);
		assertEquals(42, s.maxDepth);
		assertEquals(n, s.nextNode());
		assertEquals(null, s.nextNode());
	}
}
