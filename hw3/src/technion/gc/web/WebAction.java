package technion.gc.web;

import technion.gc.api.CrawlAction;

public class WebAction implements CrawlAction<WebNode> {

	@Override
	public void invoke(WebNode node) {
		System.out.println(node.toString());

	}

}
