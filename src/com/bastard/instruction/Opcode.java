package com.bastard.instruction;

import com.bastard.instruction.impl.ANewArrayInstruction;
import com.bastard.instruction.impl.ArithmeticInstruction;
import com.bastard.instruction.impl.BasicInstruction;
import com.bastard.instruction.impl.CastInstruction;
import com.bastard.instruction.impl.CheckCastInstruction;
import com.bastard.instruction.impl.FieldInstruction;
import com.bastard.instruction.impl.IncrementInstruction;
import com.bastard.instruction.impl.JumpInstruction;
import com.bastard.instruction.impl.LdcInstruction;
import com.bastard.instruction.impl.LocalVariableInstruction;
import com.bastard.instruction.impl.MethodInstruction;
import com.bastard.instruction.impl.NewObjectInstruction;
import com.bastard.instruction.impl.PushInstruction;
import com.bastard.instruction.impl.StackInstruction;
import com.bastard.instruction.impl.ThrowInstruction;
import com.bastard.instruction.impl.WideInstruction;


public enum Opcode {
	AASTORE(0x53, BasicInstruction.class),
	ACONST_NULL(0x01, BasicInstruction.class),
	ALOAD(0x19, LocalVariableInstruction.class),
	ALOAD_0(0x2A, BasicInstruction.class),
	ALOAD_1(0x2B, BasicInstruction.class),
	ALOAD_2(0x2C, BasicInstruction.class),
	ALOAD_3(0x2D, BasicInstruction.class),
	ANEWARRAY(0xBD, ANewArrayInstruction.class),
	NEWARRAY(0xBC, NewObjectInstruction.class),
	ARETURN(0xB0, BasicInstruction.class),
	ARRAYLENGTH(0xBE, BasicInstruction.class),
	ASTORE(0x3A, LocalVariableInstruction.class),
	ASTORE_0(0x4B, BasicInstruction.class),
	ASTORE_1(0x4C, BasicInstruction.class),
	ASTORE_2(0x4D, BasicInstruction.class),
	ASTORE_3(0x4E, BasicInstruction.class),
	ATHROW(0xBF, ThrowInstruction.class),
	BALOAD(0x33, BasicInstruction.class),
	BASTORE(0x54, BasicInstruction.class),
	BIPUSH(0x10, PushInstruction.class),
//	BREAKPOINT(0xCA, BasicInstruction.class),
	CALOAD(0x34, BasicInstruction.class),
	CASTORE(0x55, BasicInstruction.class),
	CHECKCAST(0xC0, CheckCastInstruction.class),
//	D2F(0x90, StackInstruction.class),
//	D2I(0x8E, StackInstruction.class),
//	D2L(0x8F, StackInstruction.class),
	DADD(0x63, ArithmeticInstruction.class),
//	DALOAD(0x31, BasicInstruction.class),
	AALOAD(0x32, BasicInstruction.class),
//	DASTORE(0x52, LocalVariableInstruction.class),
//	DCMPG(0x98, BasicInstruction.class),
//	DCMPL(0x97, BasicInstruction.class),
//	DCONST_0(0x0E, BasicInstruction.class),
//	DCONST_1(0x0F, BasicInstruction.class),
	DDIV(0x6F, ArithmeticInstruction.class),
//	DLOAD(0x18, LocalVariableInstruction.class),
//	DLOAD_0(0x26, BasicInstruction.class),
//	DLOAD_1(0x27, BasicInstruction.class),
//	DLOAD_2(0x28, BasicInstruction.class),
//	DLOAD_3(0x29, BasicInstruction.class),
	DMUL(0x6B, ArithmeticInstruction.class),
	DNEG(0x77, ArithmeticInstruction.class),
//	DREM(0x73, BasicInstruction.class),
//	DRETURN(0xAF, BasicInstruction.class),
//	DSTORE(0x39, LocalVariableInstruction.class),
//	DSTORE_0(0x47, BasicInstruction.class),
//	DSTORE_1(0x48, BasicInstruction.class),
//	DSTORE_2(0x49, BasicInstruction.class),
//	DSTORE_3(0x4A, BasicInstruction.class),
	DSUB(0x67, ArithmeticInstruction.class),
	DUP(0x59, StackInstruction.class),
	DUP_X1(0x5A, StackInstruction.class),
	DUP_X2(0x5B, StackInstruction.class),
//	DUP2(0x5C, StackInstruction.class),
//	DUP2_X1(0x5D, StackInstruction.class),
//	DUP2_X2(0x5E, StackInstruction.class),
//	F2D(0x8D, BasicInstruction.class),
//	F2I(0x8B, BasicInstruction.class),
//	F2L(0x8C, BasicInstruction.class),
	FADD(0x62, ArithmeticInstruction.class),
//	FALOAD(0x30, BasicInstruction.class),
//	FASTORE(0x51, BasicInstruction.class),
//	FCMPG(0x96, BasicInstruction.class),
//	FCMPL(0x95, BasicInstruction.class),
	//FCONST_0(0x0B, VariableInstruction.class),
	//FCONST_1(0x0C, VariableInstruction.class),
	//FCONST_2(0x0D, VariableInstruction.class),
	FDIV(0x6E, ArithmeticInstruction.class),
//	FLOAD(0x17, LocalVariableInstruction.class),
//	FLOAD_0(0x22, BasicInstruction.class),
//	FLOAD_1(0x23, BasicInstruction.class),
//	FLOAD_2(0x24, BasicInstruction.class),
//	FLOAD_3(0x25, BasicInstruction.class),
	FMUL(0x6A, ArithmeticInstruction.class),
	FNEG(0x76, ArithmeticInstruction.class),
//	FREM(0x72, BasicInstruction.class),
//	FRETURN(0xAE, BasicInstruction.class),
//	FSTORE(0x38, LocalVariableInstruction.class),
//	FSTORE_0(0x43, BasicInstruction.class),
//	FSTORE_1(0x44, BasicInstruction.class),
//	FSTORE_2(0x45, BasicInstruction.class),
//	FSTORE_3(0x46, BasicInstruction.class),
	FSUB(0x66, ArithmeticInstruction.class),
	GETFIELD(0xB4, FieldInstruction.class),
	GETSTATIC(0xB2, FieldInstruction.class),
	GOTO(0xA7, JumpInstruction.class),
	GOTO_W(0xC8, JumpInstruction.class),
	I2B(0x91, CastInstruction.class),
	I2C(0x92, CastInstruction.class),
	I2D(0x87, CastInstruction.class),
	I2F(0x86, CastInstruction.class),
	I2L(0x85, CastInstruction.class),
	I2S(0x93, CastInstruction.class),
	IADD(0x60, ArithmeticInstruction.class),
	IALOAD(0x2E, BasicInstruction.class),
	IAND(0x7E, ArithmeticInstruction.class),
	IASTORE(0x4F, BasicInstruction.class),
	ICONST_M1(0x02, BasicInstruction.class),
	ICONST_0(0x03, BasicInstruction.class),
	ICONST_1(0x04, BasicInstruction.class),
	ICONST_2(0x05, BasicInstruction.class),
	ICONST_3(0x06, BasicInstruction.class),
	ICONST_4(0x07, BasicInstruction.class),
	ICONST_5(0x08, BasicInstruction.class),
	IDIV(0x6C, BasicInstruction.class),
	IF_ACMPEQ(0xA5, JumpInstruction.class),
	IF_ACMPNE(0xA6, JumpInstruction.class),
	IF_ICMPEQ(0x9F, JumpInstruction.class),
	IF_ICMPGE(0xA2, JumpInstruction.class),
	IF_ICMPGT(0xA3, JumpInstruction.class),
	IF_ICMPLE(0xA4, JumpInstruction.class),
	IF_ICMPLT(0xA1, JumpInstruction.class),
	IF_ICMPNE(0xA0, JumpInstruction.class),
	IFEQ(0x99, JumpInstruction.class),
	IFGE(0x9C, JumpInstruction.class),
	IFGT(0x9D, JumpInstruction.class),
	IFLE(0x9E, JumpInstruction.class),
	IFLT(0x9B, JumpInstruction.class),
	IFNE(0x9A, JumpInstruction.class),
	IFNONNULL(0xC7, JumpInstruction.class),
	IFNULL(0xC6, JumpInstruction.class),
	IINC(0x84, IncrementInstruction.class),
	ILOAD(0x15, LocalVariableInstruction.class),
	ILOAD_0(0x1A, BasicInstruction.class),
	ILOAD_1(0x1B, BasicInstruction.class),
	ILOAD_2(0x1C, BasicInstruction.class),
	ILOAD_3(0x1D, BasicInstruction.class),
//	IMPDEP1(0xFE, BasicInstruction.class),
//	IMPDEP2(0xFF, BasicInstruction.class),
	IMUL(0x68, ArithmeticInstruction.class),
	INEG(0x74, ArithmeticInstruction.class),
//	INSTANCEOF(0xC1, Instruction.class),
	INVOKEINTERFACE(0xB9, MethodInstruction.class),
	INVOKESPECIAL(0xB7, MethodInstruction.class),
	INVOKESTATIC(0xB8, MethodInstruction.class),
	INVOKEVIRTUAL(0xB6, MethodInstruction.class),
//	IOR(0x80, BasicInstruction.class),
//	IREM(0x70, BasicInstruction.class),
	IRETURN(0xAC, BasicInstruction.class),
	ISHL(0x78, ArithmeticInstruction.class),
	ISHR(0x7A, ArithmeticInstruction.class),
	ISTORE(0x36, LocalVariableInstruction.class),
	ISTORE_0(0x3B, BasicInstruction.class),
	ISTORE_1(0x3C, BasicInstruction.class),
	ISTORE_2(0x3D, BasicInstruction.class),
	ISTORE_3(0x3E, BasicInstruction.class),
	ISUB(0x64, ArithmeticInstruction.class),
	IUSHR(0x7C, ArithmeticInstruction.class),
	IXOR(0x82, ArithmeticInstruction.class),
	JSR(0xA8, JumpInstruction.class),
	JSR_W(0xC9, JumpInstruction.class),
//	L2D(0x8A, BasicInstruction.class),
//	L2F(0x89, BasicInstruction.class),
	L2I(0x88, CastInstruction.class),
	LADD(0x61, ArithmeticInstruction.class),
//	LALOAD(0x2F, BasicInstruction.class),
	LAND(0x7F, ArithmeticInstruction.class),
//	LASTORE(0x50, BasicInstruction.class),
	LCMP(0x94, BasicInstruction.class),
	LCONST_0(0x09, BasicInstruction.class),
	LCONST_1(0x0A, BasicInstruction.class),
	LDC(0x12, LdcInstruction.class),
	LDC_W(0x13, LdcInstruction.class),
	LDC2_W(0x14, LdcInstruction.class),
	LDIV(0x6D, BasicInstruction.class),
	LLOAD(0x16, LocalVariableInstruction.class),
	LLOAD_0(0x1E, BasicInstruction.class),
	LLOAD_1(0x1F, BasicInstruction.class),
	LLOAD_2(0x20, BasicInstruction.class),
	LLOAD_3(0x21, BasicInstruction.class),
	LMUL(0x69, ArithmeticInstruction.class),
	LNEG(0x75, ArithmeticInstruction.class),
//	//LOOKUPSWITCH(0xAB, Instruction.class),
//	LOR(0x81, BasicInstruction.class),
	LREM(0x71, BasicInstruction.class),
//	LRETURN(0xAD, BasicInstruction.class),
	LSHL(0x79, ArithmeticInstruction.class),
	LSHR(0x7B, ArithmeticInstruction.class),
	LSTORE(0x37, LocalVariableInstruction.class),
	LSTORE_0(0x3F, BasicInstruction.class),
	LSTORE_1(0x40, BasicInstruction.class),
	LSTORE_2(0x41, BasicInstruction.class),
	LSTORE_3(0x42, BasicInstruction.class),
	LSUB(0x65, ArithmeticInstruction.class),
	LUSHR(0x7D,ArithmeticInstruction.class),
	LXOR(0x83, ArithmeticInstruction.class),
	MONITORENTER(0xC2, BasicInstruction.class),
	MONITOREXIT(0xC3, BasicInstruction.class),
//	MULTIANEWARRAY(0xC5, NewObjectInstruction.class),
	NEW(0xBB, NewObjectInstruction.class),
//	NEWARRAY(0xBC, NewObjectInstruction.class),
	NOP(0x00, BasicInstruction.class),
	POP(0x57, StackInstruction.class),
//	POP2(0x58, StackInstruction.class),
	PUTFIELD(0xB5, FieldInstruction.class),
	PUTSTATIC(0xB3, FieldInstruction.class),
//	RET(0xA9, BasicInstruction.class),
	RETURN(0xB1, BasicInstruction.class),
//	SALOAD(0x35, BasicInstruction.class),
	SASTORE(0x56, BasicInstruction.class),
	SIPUSH(0x11, PushInstruction.class),
	SWAP(0x5F, StackInstruction.class),
	//TABLESWITCH(0xAA, Instruction.class),
	WIDE(0xC4, WideInstruction.class);

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
