package com.bastard.cls.info;

import java.nio.ByteBuffer;

import com.bastard.cls.cpool.ConstantPool;
import com.bastard.cls.cpool.ConstantPoolEntry;
import com.bastard.cls.cpool.entry.UTF8StringEntry;
import com.bastard.cls.info.attribute.AbstractAttribute;
import com.bastard.cls.info.attribute.CodeAttribute;

/**
 * Field or method attribute info.
 * @author Tommo
 *
 */
public class AttributeInfo implements Info {
	
	/**
	 * The attribute name index in the constant pool.
	 */
	private int nameIndex;
	private int length;
	private AbstractAttribute attribute;
	
	public AttributeInfo() {
		
	}

	@Override
	public AttributeInfo read(ConstantPool pool, ByteBuffer data) {
		nameIndex = data.getShort();
		length = data.getInt();
		ConstantPoolEntry cpe = pool.getEntries()[nameIndex];
		if (cpe instanceof UTF8StringEntry) {
			UTF8StringEntry entry = (UTF8StringEntry) cpe;
			if (entry.getString().equals(CodeAttribute.ENTRY_STRING)) {
				attribute = new CodeAttribute(nameIndex, length).read(pool, data);
			} else {
				byte[] tmp = new byte[length];
				data.get(tmp);
			}
		} else {
			byte[] tmp = new byte[length];
			data.get(tmp);
		}
		return this;
	}
	
	public AbstractAttribute getAttribute() {
		return attribute;
	}
	
	@Override
	public String toString() {
		return "Attribute[nameIdx=" + nameIndex + ", length=" + length + "]";
	}

}
