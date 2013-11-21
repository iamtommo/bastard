package com.bastard.code.graph.block;

import com.bastard.code.Stack;
import com.bastard.instruction.Instruction;
import com.bastard.instruction.InstructionList;
import com.bastard.instruction.impl.LabelInstruction;
import com.bastard.util.Indent;

/**
 * Represents a set of instructions in a code block.
 * @author Shawn Davies<sodxeh@gmail.com>
 */
public class CodeBlock extends Block<LabelInstruction> {

	private InstructionList scope;
	private LabelInstruction label;
	private String tag = "none";
	private Stack stack;
	
	@Override
	public void print(int indentations) {
		System.out.println(Indent.$(indentations) + toString());
		
		if (scope != null) {
			for (Instruction insn : scope) {
				insn.print(indentations + 1);
			}
//			stack.print(indentations);
		}
	}
	
	@Override
	public String toString() {
		return "CodeBlock[tag="+tag+", label="+label+", degrees=[in="+predecessors.size()+", out="+successors.size()+"]]";
	}

	public InstructionList getScope() {
		return scope;
	}
	
	public LabelInstruction getLabel() {
		return label;
	}

	public String getTag() {
		return tag;
	}

	public Stack getStack() {
		return stack;
	}

	public CodeBlock(LabelInstruction label) {
		this.label = label;
	}
	
	public void setTag(String tag) {
		this.tag = tag;
	}

	public void setScope(InstructionList scope) {
		this.scope = scope;
//		this.stack = new Stack(scope);
	}

	@Override
	public LabelInstruction getKey() {
		return label;
	}
	
}
