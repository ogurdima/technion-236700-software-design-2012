package technion.gc.api;

public class GraphCrawler<T extends CrawlNode> {
	
	public void crawl(T root, CrawlAction<T> action, CrawlStrategy<T> strategy) {
		if (null == root || null == action || null == strategy)
			return;
		strategy.startNewCrawl(root);
		T current;
		while ((current = strategy.nextNode()) != null) {
			action.invoke(current);
		}
	}
}
