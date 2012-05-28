package technion.gc.simpleImplementation;

import technion.gc.api.CrawlAction;

public class TestCAction implements CrawlAction<TestCNode> {

	public StringBuilder buff = new StringBuilder();

	@Override
	public void invoke(TestCNode nd) {
		buff.append(nd.id);
	}

}