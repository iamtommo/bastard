package com.bastard.code.graph;

import java.util.HashMap;
import java.util.Map;

import com.bastard.cls.attribute.CodeAttribute;
import com.bastard.cls.attribute.LineNumberTableAttribute;
import com.bastard.cls.info.AttributeInfo;
import com.bastard.cls.info.LineNumberInfo;
import com.bastard.code.Node;
import com.bastard.code.Stack;
import com.bastard.code.impl.IfNode;
import com.bastard.code.impl.JumpNode;
import com.bastard.code.impl.LabelNode;

public class JumpGraph extends Graph {

	private LabelNode[] labels;
	private Map<Integer, LabelNode> positions = new HashMap<>();
	private LineNumberTableAttribute attribute;

	public JumpGraph(CodeAttribute code, Stack stack) {
		super(code, stack);

		this.labels = new LabelNode[code.getCode().limit()];
		for (AttributeInfo info : code.getAttributes()) {
			if (info.getAttribute() instanceof LineNumberTableAttribute) {
				this.attribute = (LineNumberTableAttribute) info.getAttribute();
				break;
			}
		}
	}

	@Override
	public void construct() {

		for (LineNumberInfo table : attribute.getTable()) {
			LabelNode label = labels[table.getStartPc()];
			if (label == null) {
				label = labels[table.getStartPc()] = new LabelNode(stack.getInstructions().getConstantPool(), null, table.getStartPc(), table.getLineNumber());
			}
		}

		for (int pc = 0; pc < stack.size(); pc++) {
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

			if (dst >= labels.length) {
				continue;
			}

			LabelNode label = labels[dst];
			if (label == null) {
				label = labels[dst] = new LabelNode(stack.getInstructions().getConstantPool(), null, dst, -1);
				label.setRoot(true);
				
				if (node instanceof JumpNode) {
					((JumpNode)node).setLabel(label);
				} else {
					((IfNode)node).setLabel(label);
				}
				
				positions.put(pc, label);
			}
		}
		
	}

}
