package technion.gc.web;

import static org.junit.Assert.*;

import org.junit.Test;

import technion.gc.api.GraphCrawler;

public class test {

	@Test
	public void simpleTest() {
		WebNode wn = new WebNode("http://webcourse.cs.technion.ac.il");
		WebStrategy strategy = new WebStrategy();
		WebAction action = new WebAction();
		GraphCrawler<WebNode> crawler = new GraphCrawler<WebNode>();
		crawler.crawl(wn, action, strategy);
	}

}
