package technion.gc.web;

import java.util.LinkedList;
import java.util.List;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import technion.gc.api.CrawlNode;


public class WebNode implements CrawlNode {
	private static Logger log = Logger.getLogger(WebNode.class);
	public final String url;

	public WebNode(String _url) {
		url = _url;
	}

	@Override
	public List<WebNode> getNeighbors() {
		Document doc = null;
		List<WebNode> neighbors = new LinkedList<WebNode>();
		int counter = 3;
		do {
			try {
				doc = Jsoup.connect(url).get();
				break;
			} catch (Exception e) {
				if (e.getMessage().startsWith("Unhandled content type")) {
					log.debug(url + " is a link to a non-html file or char set of the given web page is not supported");
					return neighbors;
				}
				log.debug(e.getMessage() + ". Trying " + --counter
						+ " more times");
				if (counter <= 0) {
					log.error("Could not fetch " + url);
					return neighbors;
				}
			}
		} while (counter > 0);

		Elements links = doc.select("a[href]");
		for (Element link : links) {
			if (true) {
				log.debug("Node address: " + cleanUrl(link.absUrl("href")));
				neighbors.add(new WebNode(cleanUrl(link.absUrl("href"))));
			}
		}
		return neighbors;
	}

	private static String cleanUrl(String absUrl) {
		String url = absUrl.split("#")[0]; // Remove anchors
		if (url.endsWith("/")) // Remove last "/"
			return url.substring(0, url.length() - 1);
		return url;
	}
	
	public boolean linkIsParsable() {
		if (null == url)
			return false;
		try {
			Jsoup.connect(url).get();
			return true;
		} catch (Exception e) {
			log.error("URL " + url + " cannot be parsed, skipping");
			return false;
		}
	}

	@Override
	public String toString() {
		return cleanUrl(url);
	}
	
	public void log() {
		log.info("Node address: " + cleanUrl(url));
	}
	
	@Override
	public int hashCode() {
		if (null == url)
			return 0;
		return url.hashCode();
	}
	
	@Override
	public boolean equals(Object other) {
		if (null == other)
			return false;
		if (other.getClass() != this.getClass())
			return false;
		if ( url.equals(( (WebNode)other ).url ))
			return true;
		return false;
	}

}
