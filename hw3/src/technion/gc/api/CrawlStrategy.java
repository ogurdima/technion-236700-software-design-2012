package technion.gc.api;

public interface CrawlStrategy<T extends CrawlNode> {
	T nextNode(T nd);
}
