package technion.gc.simpleImplementation;

import java.util.List;

import technion.gc.api.CrawlNode;

public class TestCNode implements CrawlNode {
	public int id;
	protected List<TestCNode> neighbors = null;

	public TestCNode(int _id, List<TestCNode> _neighbors) {
		id = _id;
		neighbors = _neighbors;
	}

	@Override
	public List<TestCNode> getNeighbors() {
		return neighbors;
	}

	public void setNeighbors(List<TestCNode> nn) {
		neighbors = nn;
	}

	@Override
	public boolean equals(Object other) {
		if (other.getClass() != this.getClass())
			return false;
		if (id == ((TestCNode) other).id)
			return true;
		return false;
	}

	@Override
	public int hashCode() {
		return id;
	}

}