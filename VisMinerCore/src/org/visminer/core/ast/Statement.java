package org.visminer.core.ast;

public class Statement {

	private NodeType nodeType = NodeType.NONE;

	public NodeType getNodeType() {
		return nodeType;
	}

	public void setNodeType(NodeType nodeType) {
		this.nodeType = nodeType;
	}

}
