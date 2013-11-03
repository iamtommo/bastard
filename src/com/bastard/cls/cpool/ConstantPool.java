package com.bastard.cls.cpool;

public class ConstantPool {
	
	private int size;
	private ConstantPoolEntry[] entries;
	private int entryIndex = 1;//Entries start at index 1
	
	public ConstantPool(int size) {
		this.size = size;
		this.entries = new ConstantPoolEntry[size];
	}
	
	public ConstantPoolEntry[] getEntries() {
		return entries;
	}

	public int getSize() {
		return size;
	}
	
	public void addEntry(ConstantPoolEntry entry) {
		entries[entryIndex] = entry;
		entryIndex += entry.getIndexIncrement();
	}
	
	public int getEntryIndex() {
		return entryIndex;
	}

}
