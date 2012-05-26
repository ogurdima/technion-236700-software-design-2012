package technion.gc.api;

public interface CrawlStrategy {
	CrawlNode nextNode(CrawlNode nd);
}
