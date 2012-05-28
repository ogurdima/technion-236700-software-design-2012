package technion.gc.web;


import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Connector {
	private static Logger log = Logger.getLogger(Connector.class);

	private static Document getDoc(String url) {
		int counter = 3;
		do {
			try {
				return Jsoup.connect(url).get();
			} catch (Exception e) {
				if (e.getMessage().startsWith("Unhandled content type")) {
					log.debug(url
							+ " is a link to a non-html file or char set of the given web page is not supported");
					break;
				}
				log.debug(e.getMessage() + ". Trying " + --counter
						+ " more times");
				if (counter <= 0) {
					log.error("Could not fetch " + url);
					break;
				}
			}
		} while (counter > 0);
		return null;
	}
	
	public static Elements getLinks(String url){
		Document doc = getDoc(url);
		if(null == doc )
			return null;
		return doc.select("a[href]");
	}
	
	public static boolean isLinkParsable(String url) {
		return null != getDoc(url);
	}
}
