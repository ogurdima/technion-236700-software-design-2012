package technion.gc.web;

public class WebDomainStrategy extends WebStrategy {

	public final String domain;

	public WebDomainStrategy(int _maxDepth, String _domain) {
		super(_maxDepth);
		domain = _domain;
	}
	public WebDomainStrategy(int _maxDepth, String _domain, WebNode root) {
		super(_maxDepth);
		domain = _domain;
		startNewCrawl(root);
	}

	@Override
	public WebNode nextNode() {
		WebNode regular = null;
		while ((regular = super.nextNode()) != null && !isFromDomain(regular));
		return regular;
	}

	private boolean isFromDomain(WebNode nd) {
		if (null != nd && null != nd.url)
			return nd.url.startsWith(domain);
		return false;
	}
}
