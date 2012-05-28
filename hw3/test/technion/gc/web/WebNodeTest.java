package technion.gc.web;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Test;

public class WebNodeTest {

	@Test
	public void badUrlTest() {
		String url = "";
		WebNode wn = new WebNode(url);
		assertEquals(new LinkedList<WebNode>(), wn.getNeighbors());
		assertEquals(url, wn.toString());
		assertEquals(url.hashCode(), wn.hashCode());
		assertEquals(true, wn.equals(new WebNode(url)));
		
	}
	
	@Test
	public void nullUrlTest() {
		String url = null;
		WebNode wn = new WebNode(url);
		assertEquals(new LinkedList<WebNode>(), wn.getNeighbors());
		assertEquals(url, wn.toString());
		assertEquals(0, wn.hashCode());
		assertEquals(true, wn.equals(new WebNode(url)));
	}
	
	@Test
	public void badUrl2Test() {
		String url = "aaa";
		WebNode wn = new WebNode(url);
		assertEquals(new LinkedList<WebNode>(), wn.getNeighbors());
		assertEquals(url, wn.toString());
		assertEquals(url.hashCode(), wn.hashCode());
		assertEquals(true, wn.equals(new WebNode(url)));
		
	}

}
