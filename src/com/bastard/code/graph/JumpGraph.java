package com.bastard.code.graph;

import com.bastard.cls.attribute.CodeAttribute;
import com.bastard.code.Node;
import com.bastard.code.Stack;
import com.bastard.code.impl.IfNode;
import com.bastard.code.impl.JumpNode;
import com.bastard.code.impl.LabelNode;

public class JumpGraph extends Graph {

	private LabelNode[] labels;

	public JumpGraph(CodeAttribute code, Stack stack) {
		super(code, stack);
		this.labels = new LabelNode[stack.size()];
	}

	@Override
	public void construct() {

		labels[0] = new LabelNode(stack.getInstructions().getConstantPool(), null, 0, -1);
		labels[0].setRoot(true);

		for (int pc = stack.size() - 1; pc >= 0; pc--) {
			Node node = stack.get(pc);

			if (!(node instanceof JumpNode || node instanceof IfNode)) {
				continue;
			}

			int dst;

			if (node instanceof JumpNode) {
				dst = ((JumpNode) node).getDestination();
			} else {
				dst = ((IfNode) node).getDestination();
			}

			if (dst >= stack.size()) {
				dst = Math.abs(dst - (stack.size() * 2)) - 1;
			}
			if (dst >= stack.size() || dst < 0) {
				continue;
			}
			
			LabelNode label = labels[dst];
			if (label == null) {
				label = labels[dst] = new LabelNode(stack.getInstructions().getConstantPool(), null, dst, -1);
				label.setRoot(true);

				if (node instanceof JumpNode) {
					((JumpNode)node).setLabel(label);
					int next = label.getStartPc() + node.getChildren().size();
					LabelNode nextLabel = labels[next] = new LabelNode(stack.getInstructions().getConstantPool(), null, next, -1);
					nextLabel.setRoot(true);
				} else {
					((IfNode)node).setLabel(label);
				}
			}
		}

		for (LabelNode label : labels) {
			if (label != null) {
				System.out.println(label+", "+stack.get(label.getStartPc())+", "+getNextLabel(label));
			}
		}
	}

	public LabelNode getNextLabel(LabelNode node) {
		for (int i = node.getStartPc() + 1; i < labels.length; i++) {
			if (labels[i] != null) {
				return labels[i];
			}
		}
		return null;
	}

}
