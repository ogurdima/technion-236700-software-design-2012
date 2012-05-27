package technion.gc.api;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

import org.junit.Test;

public class GraphCrawlerTest {

	@Test
	public void testNullRoot() {

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

class TestCNode implements CrawlNode {
	public int id;
	protected List<TestCNode> neighbors = null;

	public TestCNode(int _id, List<TestCNode> _neighbors) {
		id = _id;
		neighbors = _neighbors;
	}

	@Override
	public List<TestCNode> getNeighbors() {
		return neighbors;
	}

	public void setNeighbors(List<TestCNode> nn) {
		neighbors = nn;
	}

	@Override
	public boolean equals(Object other) {
		if (other.getClass() != this.getClass())
			return false;
		if (id == ((TestCNode) other).id)
			return true;
		return false;
	}

	@Override
	public int hashCode() {
		return id;
	}

}

/*
 * Returns all neighbors one by one
 */
class TestC_BfsStrategy implements CrawlStrategy<TestCNode> {

	Queue<TestCNode> q = new LinkedList<TestCNode>();
	Set<TestCNode> visited = new HashSet<TestCNode>();

	@Override
	public TestCNode nextNode(TestCNode nd) {
		visited.add((TestCNode) nd);
		if (null != nd.getNeighbors()) {
			for (TestCNode n : nd.getNeighbors()) {
				q.add(((TestCNode) n));
			}
		}
		TestCNode next = null;
		while ((next = q.poll()) != null && visited.contains(next))
			;
		return next;
	}
}

class TestC_DfsStrategy implements CrawlStrategy<TestCNode> {

	Stack<TestCNode> q = new Stack<TestCNode>();
	Set<TestCNode> visited = new HashSet<TestCNode>();

	@Override
	public TestCNode nextNode(TestCNode nd) {

		visited.add((TestCNode) nd);
		if (null != nd.getNeighbors()) {
			for (TestCNode n : nd.getNeighbors()) {
				q.push(((TestCNode) n));
			}
		}
		TestCNode next = null;
		while (!q.empty() && (next = q.pop()) != null && visited.contains(next)) {
			next = null;
		}

		return next;
	}
}

class TestCAction implements CrawlAction<TestCNode> {

	public StringBuilder buff = new StringBuilder();

	@Override
	public void invoke(TestCNode nd) {
		buff.append(nd.id);
	}

}
