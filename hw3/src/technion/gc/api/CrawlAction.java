package technion.gc.api;

public interface CrawlAction<T extends CrawlNode> {
	void invoke(T node);
}
