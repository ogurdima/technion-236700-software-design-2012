package technion.gc.simpleImplementation;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import technion.gc.api.CrawlStrategy;

public class TestC_DfsStrategy implements CrawlStrategy<TestCNode> {
	TestCNode currNode = null;
	Stack<TestCNode> q = null;
	Set<TestCNode> visited = null;

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
		currNode = null;
		while (!q.empty() && (currNode = q.pop()) != null && visited.contains(currNode))
			currNode = null;
		return currNode;
	}

	@Override
	public void startNewCrawl(TestCNode nd) {
		q = new Stack<TestCNode>();
		q.add(nd);
		visited = new HashSet<TestCNode>();
	}
}