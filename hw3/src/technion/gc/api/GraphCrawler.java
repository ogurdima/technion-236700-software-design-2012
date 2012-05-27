package technion.gc.api;

public class GraphCrawler<T extends CrawlNode> {
	
	public void crawl(T root, CrawlAction<T> action, CrawlStrategy<T> strategy) {
		if (null == root)
			return;
		action.invoke(root);
		T current = root;
		while ((current = strategy.nextNode(current)) != null) {
			action.invoke(current);
		}
	}
}
