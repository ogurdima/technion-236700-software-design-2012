package technion.gc.web;

import technion.gc.api.CrawlAction;
import static technion.gc.web.Connector.*;

public class WebAction implements CrawlAction<WebNode> {

	@Override
	public void invoke(WebNode node) {
		if (null != node && isLinkParsable(node.url)) {
			node.log();
		}
	}
}
