package technion.gc.web;

import java.util.LinkedList;
import java.util.List;
import org.apache.log4j.Logger;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import technion.gc.api.CrawlNode;
import static technion.gc.web.Connector.*;

public class WebNode implements CrawlNode {
	private static Logger log = Logger.getLogger(WebNode.class);
	public final String url;

	public WebNode(String _url) {
		url = _url;
	}

	@Override
	public List<WebNode> getNeighbors() {
		List<WebNode> neighbors = new LinkedList<WebNode>();
		Elements links = getLinks(url);
		if (null == links)
			return neighbors;
		for (Element link : links) {
			log.debug("Node address: " + cleanUrl(link.absUrl("href")));
			neighbors.add(new WebNode(cleanUrl(link.absUrl("href"))));
		}
		return neighbors;
	}

	private static String cleanUrl(String absUrl) {
		String url = absUrl.split("#")[0]; // Remove anchors
		if (url.endsWith("/")) // Remove last "/"
			return url.substring(0, url.length() - 1);
		return url;
	}

	@Override
	public String toString() {
		if (null == url)
			return null;
		return cleanUrl(url);
	}

	public void log() {
		if (null == url) {
			log.info("Node address: url contains null ponter !!!");
			return;
		}
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
		if (null == url && null == ((WebNode) other).url)
			return true;
		if (null != url && url.equals(((WebNode) other).url))
			return true;
		return false;
	}

}
