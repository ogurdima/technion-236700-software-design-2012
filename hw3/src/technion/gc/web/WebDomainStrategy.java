package technion.gc.web;

public class WebDomainStrategy extends WebStrategy {
	
	public final String domain;
	
	public WebDomainStrategy(int _maxDepth, String _domain) {
		super(_maxDepth);
		domain = _domain;
	}
	
	@Override
	public WebNode nextNode(WebNode nd) {
		WebNode regular = null;
		while ( (regular = super.nextNode(nd)) != null && ! isFromDomain(regular) );
		return regular;
	}
	
	private boolean isFromDomain(WebNode nd) {
		return nd.url.startsWith(domain);
	}

}
