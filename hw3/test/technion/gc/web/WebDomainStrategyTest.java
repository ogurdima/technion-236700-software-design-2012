package technion.gc.web;

import static org.junit.Assert.*;

import org.junit.Test;

public class WebDomainStrategyTest {

	@Test
	public void badDepthTest1() {
		String domain = "a";
		WebStrategy s = new WebDomainStrategy(-42, domain);
		assertEquals(1, s.maxDepth);
		assertEquals(null, s.nextNode());
	}
	
	@Test
	public void badDepthTest2() {
		String domain = "a";	
		WebStrategy s = new WebDomainStrategy(0, domain);
		assertEquals(1, s.maxDepth);
		assertEquals(null, s.nextNode());
	}
	
	@Test
	public void badDepthTest3() {
		String domain = "a";
		String url = "aaa";
		WebNode n = new WebNode(url);
		WebStrategy s = new WebDomainStrategy(-42,domain, n);
		assertEquals(1, s.maxDepth);
		assertEquals(n, s.nextNode());
	}
	
	@Test
	public void nullPointerTest1() {
		WebStrategy s = new WebDomainStrategy(42, null);
		s.startNewCrawl(null);
		assertEquals(42, s.maxDepth);
		assertEquals(null, s.nextNode());
	}
	
	@Test
	public void nullPointerTest2() {
		WebStrategy s = new WebDomainStrategy(42, null, null);
		assertEquals(42, s.maxDepth);
		assertEquals(null, s.nextNode());
	}
	
	
	@Test
	public void Test1() {
		String domain = "a";
		String url = "aaa";
		WebNode n = new WebNode(url);
		WebStrategy s = new WebDomainStrategy(42, domain);
		assertEquals(42, s.maxDepth);
		assertEquals(null, s.nextNode());
		s.startNewCrawl(n);
		assertEquals(n, s.nextNode());
		assertEquals(null, s.nextNode());
	}
	
	@Test
	public void Test2() {
		String domain = "a";
		String url = "aaa";
		WebNode n = new WebNode(url);
		WebStrategy s  = new WebDomainStrategy(42, domain, n);
		assertEquals(42, s.maxDepth);
		assertEquals(n, s.nextNode());
		assertEquals(null, s.nextNode());
	}

}
