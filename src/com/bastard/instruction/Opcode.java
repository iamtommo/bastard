package com.bastard.instruction;

import com.bastard.instruction.impl.BasicInstruction;
import com.bastard.instruction.impl.CheckCastInstruction;
import com.bastard.instruction.impl.FieldInstruction;
import com.bastard.instruction.impl.JumpInstruction;
import com.bastard.instruction.impl.LdcInstruction;
import com.bastard.instruction.impl.MethodInstruction;
import com.bastard.instruction.impl.NewObjectInstruction;
import com.bastard.instruction.impl.NopInstruction;
import com.bastard.instruction.impl.StackInstruction;
import com.bastard.instruction.impl.VariableInstruction;

public enum Opcode {
	GETSTATIC(0xB2, FieldInstruction.class),
	PUTSTATIC(0xB3, FieldInstruction.class),
	GETFIELD(0xB4, FieldInstruction.class),
	PUTFIELD(0xB5, FieldInstruction.class),
	
	ICONST_0(0x03, BasicInstruction.class),
	ICONST_1(0x04, BasicInstruction.class),
	ICONST_2(0x05, BasicInstruction.class),
	FCONST_0(0x0B, BasicInstruction.class),
	FCONST_1(0x0C, BasicInstruction.class),
	FCONST_2(0x0D, BasicInstruction.class),
	ARETURN(0xB0, BasicInstruction.class),
	RETURN(0xB1, BasicInstruction.class),
	IMUL(0x68, BasicInstruction.class),
	IADD(0x60, BasicInstruction.class),
	
	ILOAD_0(0x1A, BasicInstruction.class),
	ILOAD_1(0x1B, BasicInstruction.class),
	ILOAD_2(0x1C, BasicInstruction.class),
	ILOAD_3(0x1D, BasicInstruction.class),
	ALOAD_0(0x2A, BasicInstruction.class),
	ALOAD_1(0x2B, BasicInstruction.class),
	ALOAD_2(0x2C, BasicInstruction.class),
	ALOAD_3(0x2D, BasicInstruction.class),
	
	I2L(0x85, BasicInstruction.class),
	ASTORE_0(0x4B, BasicInstruction.class),
	ASTORE_1(0x4C, BasicInstruction.class),
	ASTORE_2(0x4D, BasicInstruction.class),
	ASTORE_3(0x4E, BasicInstruction.class),

	INVOKESPECIAL(0xB7, MethodInstruction.class),
	INVOKEVIRTUAL(0xB6, MethodInstruction.class),
	
	GOTO(0xA7, JumpInstruction.class),
	GOTO_W(0xC8, JumpInstruction.class),
	JSR(0xA8, BasicInstruction.class),
	JSR_W(0xC9, BasicInstruction.class),
	IF_ICMPNE(0xA0, JumpInstruction.class),
	IFNULL(0xC6, JumpInstruction.class),
	
	BIPUSH(0x10, VariableInstruction.class),
	SIPUSH(0x11, VariableInstruction.class),
	
	DUP(0x59, StackInstruction.class),
	DUP_X1(0x5A, StackInstruction.class),
	DUP_X2(0x5B, StackInstruction.class),
	DUP2(0x5C, StackInstruction.class),
	DUP2_X1(0x5D, StackInstruction.class),
	DUP2_X2(0x5E, StackInstruction.class),
	
	NOP(0x0, NopInstruction.class),
	
	LDC(0x12, LdcInstruction.class),
	LDC_W(0x13, LdcInstruction.class),
	LDC2_W(0x14, LdcInstruction.class),
	
	NEW(0xBB, NewObjectInstruction.class),
	
	CHECKCAST(0xC0, CheckCastInstruction.class);

	private int opcode;
	private Class<? extends Instruction> instructionClass;
	
	Opcode(int opcode, Class<? extends Instruction> instructionClass) {
		this.opcode = opcode;
		this.instructionClass = instructionClass;
	}
	
	public int getOpcode() {
		return opcode;
	}
	
	public Class<? extends Instruction> getInstructionClass() {
		return instructionClass;
	}
	
	@Override
	public String toString() {
		return this.name();
	}
	
	public static Opcode valueOf(int opcode) {
		for (Opcode o : Opcode.values()) {
			if (o.getOpcode() == opcode) {
				return o;
			}
		}
		return null;
	}
}
