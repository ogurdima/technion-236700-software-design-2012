package technion.gc.api;

public interface CrawlStrategy<NodeType extends CrawlNode> {
	NodeType nextNode();
	void startNewCrawl(NodeType nd);
}
