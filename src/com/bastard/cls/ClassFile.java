package com.bastard.cls;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;

import com.bastard.Application;
import com.bastard.cls.cpool.ConstantPool;
import com.bastard.cls.cpool.ConstantPoolEntry;
import com.bastard.cls.cpool.EntryType;
import com.bastard.cls.info.AttributeInfo;
import com.bastard.cls.info.FieldInfo;
import com.bastard.cls.info.MethodInfo;

public class ClassFile {
	
	public static final int MAGIC_NUMBER = 0xCAFEBABE;
	
	private ByteBuffer data;
	private File file;
	
	/**
	 * Class file properties
	 */
	private String name;
	private int magic;
	private int minorVersion;
	private int majorVersion;
	private ConstantPool constantPool;
	private int accessFlags;
	private boolean isPublic;
	private boolean isFinal;
	private boolean isSuper;
	private boolean isInterface;
	private boolean isAbstract;
	private int thisClassIndex;
	private int superClassIndex;
	private int[] interfaceTable;
	private FieldInfo[] fieldTable;
	private MethodInfo[] methodTable;
	private int attributesCount;
	private AttributeInfo[] attributes;
	
	public ClassFile(File file) {
		this.name = file.getName();
		this.file = file;
	}
	
	public ClassFile(String name, byte[] data) {
		this.name = name;
		this.data = ByteBuffer.wrap(data);
	}

	public void read() throws Exception {
		System.out.println(name+"\n{");
		if (data == null && file != null) {
			readFileData();
		}
		
		magic |= ((byte) data.get() & 0xCA) << 24;
		magic |= ((byte) data.get() & 0xFE) << 16;
		magic |= ((byte) data.get() & 0xBA) << 8;
		magic |= ((byte) data.get() & 0xBE);
		if (magic == MAGIC_NUMBER) {
			Application.log("\tMagic number: " + Integer.toHexString(magic));
		} else {
			Application.log("\tIncorrect magic number: " + Integer.toHexString(magic));
			throw new Exception();
		}
		
		minorVersion = data.getShort();
		Application.log("\tClass minor version: " + minorVersion);
		
		majorVersion |= data.get() << 8;
		majorVersion |= data.get();
		Application.log("\tClass major version: " + majorVersion);
		
		//Entries start at index 1 therefore size is always greater than actual entries
		int cSize = data.getShort();
		constantPool = new ConstantPool(cSize);
		Application.log("\tConstant pool size: " + cSize);
		
		for (int ind = 0; ind < constantPool.getSize() - 1;) {
			byte tag = data.get();	
			System.out.print("\t\tEntry index " + ind + ", tag " + tag);
			EntryType type = EntryType.valueOf(tag);
			ConstantPoolEntry entry = type.getEntryClass().newInstance();
			entry.read(data);
			System.out.print(" > " + entry.toString() + "\n");
			constantPool.addEntry(entry);
			ind += entry.getIndexIncrement();
		}
		
		accessFlags = data.getShort();
		isPublic = (accessFlags & Flags.PUBLIC) > 0;
		isFinal = (accessFlags & Flags.FINAL) > 0;
		isSuper = (accessFlags & Flags.CLASS_SUPER) > 0;
		isInterface = (accessFlags & Flags.CLASS_INTERFACE) > 0;
		isAbstract = (accessFlags & Flags.CLASS_ABSTRACT) > 0;
		System.out.print("\tAccess flags: " + Integer.toHexString(accessFlags) + " - ");
		System.out.println("public=" + isPublic + ", final=" + isFinal + ", super=" + isSuper +
				", interface=" + isInterface + ", abstract=" + isAbstract);
		
		thisClassIndex = data.getShort();
		superClassIndex = data.getShort();
		System.out.println("\tClass pool indexes: this=" + thisClassIndex + " super=" + superClassIndex);
		
		int iTableSize = data.getShort();
		interfaceTable = new int[iTableSize];
		System.out.println("\tInterface table size: " + iTableSize);
		for (int i = 0; i < interfaceTable.length; i++) {
			int iPoolIndex = data.getShort();
			interfaceTable[i] = iPoolIndex;
			System.out.println("\t\tInterface pool index: " + iPoolIndex);
		}
		
		int fieldTableSize = data.getShort();
		fieldTable = new FieldInfo[fieldTableSize];
		System.out.println("\tField table size: " + fieldTableSize);
		for (int i = 0; i < fieldTable.length; i++) {
			FieldInfo fi = new FieldInfo().read(constantPool, data);
			fi.print(2);
			fieldTable[i] = fi;
		}
		
		int methodTableSize = data.getShort();
		methodTable = new MethodInfo[methodTableSize];
		System.out.println("\tMethod table size: " + methodTableSize);
		for (int i = 0; i < methodTable.length; i++) {
			MethodInfo mi = new MethodInfo().read(constantPool, data);
			mi.print(2);
			methodTable[i] = mi;
		}
		
		attributesCount = data.getShort();
		attributes = new AttributeInfo[attributesCount];
		System.out.println("\tClass attributes count: " + attributesCount);
		for (int i = 0; i < attributes.length; i++) {
			AttributeInfo ai = new AttributeInfo().read(constantPool, data);
			ai.print(2);
			attributes[i] = ai;
		}
		System.out.println("}\n");
	}
	
	private void readFileData() {
		try {
			DataInputStream in = new DataInputStream(new FileInputStream(file));
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			while (in.available() > 0) {
				out.write(in.readByte());
			}
			in.close();
			data = ByteBuffer.wrap(out.toByteArray());
			Application.log("Read " + data.capacity() + " bytes from file.");
		} catch (FileNotFoundException e) {
			Application.logerr("File not found!");
			e.printStackTrace();
		} catch (IOException e) {
			Application.logerr("Error reading file!");
			e.printStackTrace();
		}	
	}

	public ConstantPool getConstantPool() {
		return constantPool;
	}

	public boolean isPublic() {
		return isPublic;
	}

	public boolean isFinal() {
		return isFinal;
	}

	public boolean isSuper() {
		return isSuper;
	}

	public boolean isInterface() {
		return isInterface;
	}

	public boolean isAbstract() {
		return isAbstract;
	}

	public int getThisClassIndex() {
		return thisClassIndex;
	}

	public int getSuperClassIndex() {
		return superClassIndex;
	}

	public int[] getInterfaceTable() {
		return interfaceTable;
	}

	public FieldInfo[] getFieldTable() {
		return fieldTable;
	}

	public MethodInfo[] getMethodTable() {
		return methodTable;
	}

	public int getAttributesCount() {
		return attributesCount;
	}

	public AttributeInfo[] getAttributes() {
		return attributes;
	}
	
}
