package technion.gc.web;

import org.apache.log4j.Logger;

import technion.gc.api.GraphCrawler;

public class WebCrawler {

	private static Logger log = Logger.getLogger(WebNode.class);
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		log.debug("Started program execution");
		
		if (args.length < 2) {
			log.fatal("Not enough arguments");
			return;
		}
		WebNode wn = new WebNode(args[0]);
		WebStrategy strategy = null;
		WebAction action = new WebAction();
		GraphCrawler<WebNode> crawler = new GraphCrawler<WebNode>();
		
		if (args.length == 2) {
			try {
				int maxDepth = Integer.parseInt(args[1]);
				if (maxDepth < 1)
					throw new NumberFormatException();
				strategy = new WebStrategy(maxDepth);
			}
			catch (NumberFormatException e){
				log.fatal("Second argument must be a positive integer");
				return;
			}
			crawler.crawl(wn, action, strategy);
			return;
		}
		else if (args.length == 3) {
			try {
				int maxDepth = Integer.parseInt(args[1]);
				if (maxDepth < 1)
					throw new NumberFormatException();
				strategy = new WebDomainStrategy(maxDepth, args[2]);
			}
			catch (NumberFormatException e){
				log.fatal("Second argument must be a positive integer");
				return;
			}
			crawler.crawl(wn, action, strategy);
			return;
		}
		else {
			log.fatal("Too many arguments for Web Crawler");
			return;
		}
		
	}

}
