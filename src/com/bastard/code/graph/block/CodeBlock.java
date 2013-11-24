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
	private String tag;
	private Stack stack;

	@Override
	public void print(int indentations) {
		System.out.println(Indent.$(indentations) + toString());
		if (!warnings.isEmpty()) {
			System.out.println();
			System.out.println(Indent.$(indentations + 1) + "Warnings (size="+warnings.size()+")");

			for (int i = 0; i < warnings.size(); i++) {
				String warning = warnings.get(i);
				System.out.println(Indent.$(indentations + 2) + (i + 1) +": "+warning);
			}
			System.out.println(Indent.$(indentations + 1) + "end");
		}
		if (scope != null && !scope.isEmpty()) {
			System.out.println();
			System.out.println(Indent.$(indentations + 1) + "Scope(size="+scope.size()+")");
			for (Instruction insn : scope) {
				insn.print(indentations + 2);
			}
			System.out.println(Indent.$(indentations + 1) + "end");
			System.out.println();
			stack.print(indentations + 1);
		}
		System.out.println(Indent.$(indentations) + "end");
		System.out.println();
	}

	@Override
	public String toString() {
		return "CodeBlock[tag="+tag+", label="+label+", degrees=[in="+predecessors.size()+", out="+successors.size()+"]]";
	}

	public InstructionList getScope() {
		return scope;
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
		this.stack = new Stack(scope);
	}

	@Override
	public LabelInstruction getKey() {
		return label;
	}

}
