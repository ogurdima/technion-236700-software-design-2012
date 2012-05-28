package technion.gc.web;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import technion.gc.api.CrawlStrategy;

public class WebStrategy implements CrawlStrategy<WebNode> {
	public final int maxDepth;
	private WebNode currNode;
	private Queue<WebNode> q = new LinkedList<WebNode>();
	private Map<WebNode, Integer> depth = new HashMap<WebNode, Integer>();

	public WebStrategy(int _maxDepth) {
		if (_maxDepth < 1)
			maxDepth = 1;
		else
			maxDepth = _maxDepth;
	}

	public WebStrategy(int _maxDepth, WebNode root) {
		if (_maxDepth < 1)
			maxDepth = 1;
		else
			maxDepth = _maxDepth;
		startNewCrawl(root);
	}

	@Override
	public WebNode nextNode() {
		if (null == currNode && 0 == q.size())
			return null;
		Integer d = depth.get(currNode);
		List<WebNode> neighbors;
		if (null != currNode && null != d && d < maxDepth
				&& null != (neighbors = currNode.getNeighbors())) {
			for (WebNode n : neighbors) {
				if (!depth.containsKey(n)) {
					depth.put(n, depth.get(currNode) + 1);
					q.add(n);
				}
			}
		}
		currNode = q.poll();
		return currNode;
	}

	@Override
	public void startNewCrawl(WebNode root) {
		currNode = null;
		q = new LinkedList<WebNode>();
		depth = new HashMap<WebNode, Integer>();
		if(null != root) {
			depth.put(root, 1);
			q.add(root);
		}
	}
}
