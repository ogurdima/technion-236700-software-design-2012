package technion.gc.web;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import technion.gc.api.CrawlStrategy;

public class WebStrategy implements CrawlStrategy<WebNode> {
	
	public final int maxDepth;
	
	public WebStrategy(int _maxDepth) {
		if (_maxDepth < 0)
			maxDepth = 1;
		else
			maxDepth = _maxDepth;
	}
	
	Queue<WebNode> q = new LinkedList<WebNode>();
	Map<WebNode, Integer> depth = new HashMap<WebNode, Integer>();

	@Override
	public WebNode nextNode(WebNode nd) {
		if (! depth.containsKey(nd)) //root
			depth.put(nd, 1);
		if (depth.get(nd) >= maxDepth)
			return q.poll();
		if (null != nd.getNeighbors()) {
			for (WebNode n : nd.getNeighbors()) {
				if (! depth.containsKey(n))
				{
					depth.put(n, depth.get(nd)+1);
					q.add(n);
				}
			}
		}
		
		return q.poll();
	}
}
