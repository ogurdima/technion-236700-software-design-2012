package technion.gc.api;

import java.util.List;

public interface CrawlNode {
	List<? extends CrawlNode> getNeighbors();
}
