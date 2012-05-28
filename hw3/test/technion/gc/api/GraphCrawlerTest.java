package technion.gc.api;

import static org.junit.Assert.*;
import java.util.LinkedList;
import java.util.List;
import org.junit.Test;
import technion.gc.simpleImplementation.*;

public class GraphCrawlerTest {

	@Test
	public void testNullRoot() {
		GraphCrawler<TestCNode> gc = new GraphCrawler<TestCNode>();
		gc.crawl(null, new TestCAction(), new TestC_BfsStrategy());
	}

	@Test
	public void testNullAction() {
		GraphCrawler<TestCNode> gc = new GraphCrawler<TestCNode>();
		gc.crawl(new TestCNode(1, new LinkedList<TestCNode>()), null,
				new TestC_BfsStrategy());
	}

	@Test
	public void testNullStrategy() {
		GraphCrawler<TestCNode> gc = new GraphCrawler<TestCNode>();
		gc.crawl(new TestCNode(1, new LinkedList<TestCNode>()),
				new TestCAction(), null);
	}

	@Test
	public void testDfs() {

		TestCNode n1 = new TestCNode(1, new LinkedList<TestCNode>());
		TestCNode n2 = new TestCNode(2, new LinkedList<TestCNode>());
		TestCNode n3 = new TestCNode(3, new LinkedList<TestCNode>());
		TestCNode n4 = new TestCNode(4, new LinkedList<TestCNode>());
		TestCNode n5 = new TestCNode(5, new LinkedList<TestCNode>());
		TestCNode n6 = new TestCNode(6, new LinkedList<TestCNode>());
		TestCNode n7 = new TestCNode(7, new LinkedList<TestCNode>());

		List<TestCNode> l = n1.getNeighbors();
		l.add(n2);
		l = n2.getNeighbors();
		l.add(n3);
		l.add(n4);
		l = n3.getNeighbors();
		l.add(n1);
		l.add(n6);
		l = n4.getNeighbors();
		l.add(n7);
		l = n5.getNeighbors();
		l.add(n4);

		TestCAction action = new TestCAction();
		GraphCrawler<TestCNode> crawler = new GraphCrawler<TestCNode>();
		crawler.crawl(n1, action, new TestC_DfsStrategy());
		assertEquals("124736", action.buff.toString());
	}

	@Test
	public void testBfs() {

		TestCNode n1 = new TestCNode(1, new LinkedList<TestCNode>());
		TestCNode n2 = new TestCNode(2, new LinkedList<TestCNode>());
		TestCNode n3 = new TestCNode(3, new LinkedList<TestCNode>());
		TestCNode n4 = new TestCNode(4, new LinkedList<TestCNode>());
		TestCNode n5 = new TestCNode(5, new LinkedList<TestCNode>());

		List<TestCNode> l = n1.getNeighbors();
		l.add(n2);
		l = n2.getNeighbors();
		l.add(n3);
		l.add(n4);
		l = n3.getNeighbors();
		l.add(n1);
		l = n5.getNeighbors();
		l.add(n4);

		TestCAction action = new TestCAction();
		GraphCrawler<TestCNode> crawler = new GraphCrawler<TestCNode>();
		crawler.crawl(n1, action, new TestC_BfsStrategy());
		assertEquals("1234", action.buff.toString());
	}

	@Test
	public void testBfs2() {

		TestCNode n1 = new TestCNode(1, null);
		TestCNode n2 = new TestCNode(2, null);
		List<TestCNode> l = new LinkedList<TestCNode>();
		l.add(n1);
		l.add(n2);
		TestCNode n3 = new TestCNode(3, l);
		TestCAction action = new TestCAction();
		GraphCrawler<TestCNode> crawler = new GraphCrawler<TestCNode>();
		crawler.crawl(n3, action, new TestC_BfsStrategy());
		assertEquals("312", action.buff.toString());
	}
}
