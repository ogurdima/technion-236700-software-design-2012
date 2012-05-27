package technion.gc.web;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import technion.gc.api.CrawlStrategy;

public class WebStrategy implements CrawlStrategy<WebNode> {
	Queue<WebNode> q = new LinkedList<WebNode>();
	Set<WebNode> visited = new HashSet<WebNode>();

	@Override
	public WebNode nextNode(WebNode nd) {
		visited.add( nd);
		if (null != nd.getNeighbors()) {
			for (WebNode n : nd.getNeighbors()) {
				q.add(n);
			}
		}
		WebNode next = null;
		while ((next = q.poll()) != null && visited.contains(next))
			;
		return next;
	}
}
