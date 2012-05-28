package technion.gc.simpleImplementation;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import technion.gc.api.CrawlStrategy;

/*
 * Returns all neighbors one by one
 */
public class TestC_BfsStrategy implements CrawlStrategy<TestCNode> {
	TestCNode currNode;
	Queue<TestCNode> q = new LinkedList<TestCNode>();
	Set<TestCNode> visited = new HashSet<TestCNode>();

	@Override
	public TestCNode nextNode() {
		visited.add(currNode);
		List<TestCNode> neighbors = null;
		if (null != currNode)
			neighbors = currNode.getNeighbors();
		if (null != neighbors) {
			for (TestCNode n : neighbors) {
				q.add(((TestCNode) n));
			}
		}
		while ((currNode = q.poll()) != null && visited.contains(currNode))
			;
		return currNode;
	}

	@Override
	public void startNewCrawl(TestCNode nd) {
		q = new LinkedList<TestCNode>();
		q.add(nd);
		visited = new HashSet<TestCNode>();
	}
}