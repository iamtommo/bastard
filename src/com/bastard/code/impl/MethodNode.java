package com.bastard.code.impl;

import java.util.ArrayList;
import java.util.List;

import com.bastard.cls.cpool.ConstantPool;
import com.bastard.cls.cpool.ConstantPoolEntry;
import com.bastard.cls.cpool.entry.ClassRefEntry;
import com.bastard.cls.cpool.entry.InterfaceMethodRefEntry;
import com.bastard.cls.cpool.entry.MethodRefEntry;
import com.bastard.cls.cpool.entry.NameTypeRefEntry;
import com.bastard.cls.cpool.entry.UTF8StringEntry;
import com.bastard.code.Node;
import com.bastard.instruction.impl.MethodInstruction;

/**
 * Represents a {@link MethodInstruction} as a node.
 * @author Shawn Davies<sodxeh@gmail.com>
 */
public class MethodNode extends Node {

	/**
	 * The name of the method.
	 */
	private String name;
	/**
	 * The signature of the method.
	 */
	private String signature;
	/**
	 * The owner of this method.
	 */
	private String owner;

	public MethodNode(ConstantPool pool, MethodInstruction instruction) {
		super(pool, instruction);

		ConstantPoolEntry entry = pool.getEntries()[instruction.getMethodIndex()];

		NameTypeRefEntry nameType = null;
		UTF8StringEntry nameStrRef = null;
		UTF8StringEntry typeStrRef = null;
		UTF8StringEntry clsStrRef = null;
		if (entry instanceof MethodRefEntry) {

			MethodRefEntry ref = (MethodRefEntry) entry;

			ClassRefEntry cls = (ClassRefEntry) pool.getEntries()[ref.getClassRefIndex()];
			nameType = (NameTypeRefEntry) pool.getEntries()[ref.getNameTypeRefIndex()];
			nameStrRef = (UTF8StringEntry) pool.getEntries()[nameType.getNameIndex()];
			typeStrRef = (UTF8StringEntry) pool.getEntries()[nameType.getDescriptorIndex()];
			clsStrRef = (UTF8StringEntry) pool.getEntries()[cls.getStringIndex()];
		} else {
			InterfaceMethodRefEntry ref = (InterfaceMethodRefEntry) entry;

			ClassRefEntry cls = (ClassRefEntry) pool.getEntries()[ref.getClassRefIndex()];
			nameType = (NameTypeRefEntry) pool.getEntries()[ref.getNameTypeRefIndex()];
			nameStrRef = (UTF8StringEntry) pool.getEntries()[nameType.getNameIndex()];
			typeStrRef = (UTF8StringEntry) pool.getEntries()[nameType.getDescriptorIndex()];
			clsStrRef = (UTF8StringEntry) pool.getEntries()[cls.getStringIndex()];
		}

		this.name = nameStrRef.getString();
		this.signature = typeStrRef.getString();
		this.owner = clsStrRef.getString();
	}

	/**
	 * Gets all types related to this method.
	 * Credits to the developers of BLOAT.
	 * @return The types of this method.
	 */
	public List<String> getTypes() {
		List<String> types = new ArrayList<>();
		String t = "";
		int state = 0;
		for (int i = 0; i < signature.length(); i++) {
			switch(state) {
			case 0:
				switch(signature.charAt(i)) {
				case 'B':
				case 'C':
				case 'D':
				case 'F':
				case 'I':
				case 'J':
				case 'S':
				case 'V':
				case 'Z':
					types.add(t + signature.charAt(i));
					t = "";
					break;
				case 'L':
					t += 'L';
					state = 1;
					break;
				case '[':
					t += '[';
					break;
				}
				break;
			case 1:
				t += signature.charAt(i);
				if (signature.charAt(i) == ';') {
					types.add(t);
					t = "";
					state = 0;
				}
				break;
				default:
					throw new IllegalStateException("Invalid character in method: "+signature);
			}
		}
		return types;
	}

	@Override
	public String code() {
		return "MethodNode[owner="+owner+", name="+name+", signature="+signature+"]";
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getName() {
		return name;
	}

	public String getSignature() {
		return signature;
	}

	public String getOwner() {
		return owner;
	}

}
