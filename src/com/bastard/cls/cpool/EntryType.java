package com.bastard.cls.cpool;

import com.bastard.cls.cpool.entry.ClassRefEntry;
import com.bastard.cls.cpool.entry.DoubleEntry;
import com.bastard.cls.cpool.entry.EmptyEntry;
import com.bastard.cls.cpool.entry.FieldRefEntry;
import com.bastard.cls.cpool.entry.FloatEntry;
import com.bastard.cls.cpool.entry.IntegerEntry;
import com.bastard.cls.cpool.entry.InterfaceMethodRefEntry;
import com.bastard.cls.cpool.entry.LongEntry;
import com.bastard.cls.cpool.entry.MethodRefEntry;
import com.bastard.cls.cpool.entry.NameTypeRefEntry;
import com.bastard.cls.cpool.entry.StringRefEntry;
import com.bastard.cls.cpool.entry.UTF8StringEntry;

public enum EntryType {
	EMPTY(0, EmptyEntry.class),
	UTF8_STRING_TAG(1, UTF8StringEntry.class),
	CLASS_REF_TAG(7, ClassRefEntry.class),
	FIELD_REF_TAG(9, FieldRefEntry.class),
	METHOD_REF_TAG(10, MethodRefEntry.class),
	NAME_TYPE_REF_TAG(12, NameTypeRefEntry.class),
	STRING_REF_TAG(8, StringRefEntry.class),
	INTEGER(3, IntegerEntry.class),
	FLOAT(4, FloatEntry.class),
	LONG(5, LongEntry.class),
	DOUBLE(6, DoubleEntry.class),
	INTERFACE_METHOD_REF(11, InterfaceMethodRefEntry.class);
	
	private int tag;
	private Class<? extends ConstantPoolEntry> entryClass;
	
	EntryType(int tag, Class<? extends ConstantPoolEntry> entryClass) {
		this.tag = tag;
		this.entryClass = entryClass;
	}
	
	public int getTag() {
		return tag;
	}
	
	public Class<? extends ConstantPoolEntry> getEntryClass() {
		return entryClass;
	}

	public static EntryType valueOf(int tag) {
		for (EntryType et : EntryType.values()) {
			if (et.getTag() == tag) {
				return et;
			}
		}
		
		return null;
	}
	
	public static EntryType valueOf(Class<?> cls) {
		for (EntryType et : EntryType.values()) {
			if (et.getEntryClass() == cls) {
				return et;
			}
		}
		return null;
	}

}
