package il.ac.technion;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class Main {
	
	private static Logger log = Logger.getLogger(Main.class);

	
	/**
	 * Will print all the clean out-links (including files) from a
	 * given URL address. Note that the <code>log4j.properties</code> 
	 * file must be present somewhere within the classpath.
	 * @param args - Only one argument here, the start domain.
	 */
	public static void main(String[] args) {

		if (args.length < 1) {
			log.fatal("Missing domain argument");
			return;
		}
		
		String url = args[0]; // E.g. "http://www.cs.technion.ac.il"
		Document doc = null;

		int counter = 3;
		do{
			try {
				doc = Jsoup.connect(url).get();
				break;
			} catch (IOException e) {
				if (e.getMessage().startsWith("Unhandled content type")) { 
					log.debug(url + " is a link to a non-html file");
					return;
				}
				log.warn(e.getMessage() + ". Trying " + --counter + " more times");
				if (counter <= 0) {
					log.error("Could not fetch " + url);
					return;
				}
			}
		} while (counter > 0);
		
		Elements links = doc.select("a[href]");
		
		for (Element link : links) {
			log.info("Node address: " + cleanUrl(link.absUrl("href")));
		}
	}

	private static String cleanUrl(String absUrl) {
		String url = absUrl.split("#")[0]; // Remove anchors 
		if (url.endsWith("/")) // Remove last "/"
			return url.substring(0, url.length() - 1);
		return url;
	}
}
