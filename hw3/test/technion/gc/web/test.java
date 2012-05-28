package technion.gc.web;


import org.junit.Ignore;
import org.junit.Test;

import technion.gc.api.GraphCrawler;

public class test {
	
	@Ignore
	@Test
	public void simpleTest() {
		System.out.println("================================================================");
		WebNode wn = new WebNode("http://webcourse.cs.technion.ac.il/236700");
		WebStrategy strategy = new WebStrategy(0);
		WebAction action = new WebAction();
		GraphCrawler<WebNode> crawler = new GraphCrawler<WebNode>();
		crawler.crawl(wn, action, strategy);
	}
	
	@Test
	public void simpleDomainTest() {
		System.out.println("================================================================");
		WebNode wn = new WebNode("http://webcourse.cs.technion.ac.il/236700");
		WebStrategy strategy = new WebDomainStrategy(3, "http://webcourse.cs.technion.ac.il");
		WebAction action = new WebAction();
		GraphCrawler<WebNode> crawler = new GraphCrawler<WebNode>();
		crawler.crawl(wn, action, strategy);
	}

}
