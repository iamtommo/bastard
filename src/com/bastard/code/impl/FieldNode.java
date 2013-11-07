package com.bastard.code.impl;

import java.util.Arrays;

import com.bastard.cls.cpool.ConstantPool;
import com.bastard.cls.cpool.entry.ClassRefEntry;
import com.bastard.cls.cpool.entry.FieldRefEntry;
import com.bastard.cls.cpool.entry.NameTypeRefEntry;
import com.bastard.cls.cpool.entry.UTF8StringEntry;
import com.bastard.code.Node;
import com.bastard.instruction.Opcode;
import com.bastard.instruction.impl.FieldInstruction;

/**
 * Represents a {@link FieldInstruction} as a node.
 * @author Shawn Davies<sodxeh@gmail.com>
 */
public class FieldNode extends Node {

	/**
	 * The name of the field.
	 */
	protected String name;
	/**
	 * The signature of the field.
	 */
	protected String signature;
	/**
	 * The owner of this field.
	 */
	protected String owner;
	
	public FieldNode(ConstantPool pool, FieldInstruction instruction) {
		super(pool, instruction);
		
		FieldRefEntry ref = (FieldRefEntry) pool.getEntries()[instruction.getFieldIndex()];
		
		ClassRefEntry cls = (ClassRefEntry) pool.getEntries()[ref.getClassIndex()];
		NameTypeRefEntry nameType = (NameTypeRefEntry) pool.getEntries()[ref.getNameTypeIndex()];
		UTF8StringEntry nameStrRef = (UTF8StringEntry) pool.getEntries()[nameType.getNameIndex()];
		UTF8StringEntry typeStrRef = (UTF8StringEntry) pool.getEntries()[nameType.getDescriptorIndex()];
		UTF8StringEntry clsStrRef = (UTF8StringEntry) pool.getEntries()[cls.getStringIndex()];
		
		this.name = nameStrRef.getString();
		this.signature = typeStrRef.getString();
		this.owner = clsStrRef.getString();
	}

	@Override
	public String code() {
		return "FieldNode[owner="+owner+", name="+name+", signature="+signature+", type="+Opcode.valueOf(instruction.getOpcode() & 0xFF).toString()+", children="+Arrays.toString(children.toArray())+"]";
	}
}
