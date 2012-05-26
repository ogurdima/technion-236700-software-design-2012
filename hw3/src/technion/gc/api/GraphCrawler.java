package technion.gc.api;

public class GraphCrawler {
	
	public void crawl(CrawlNode root, CrawlAction action, CrawlStrategy strategy) {
		if (null == root)
			return;
		action.invoke(root);
		CrawlNode current = root;
		while ((current = strategy.nextNode(current)) != null) {
			action.invoke(current);
		}
	}
}
