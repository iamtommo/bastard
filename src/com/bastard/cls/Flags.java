package com.bastard.cls;

public class Flags {

	public static final int PUBLIC = 0x1;
	public static final int PRIVATE = 0x2;
	public static final int PROTECTED = 0x4;
	public static final int STATIC = 0x8;
	public static final int FINAL = 0x10;
	
	public static final int CLASS_SUPER = 0x20;
	public static final int CLASS_INTERFACE = 0x200;
	public static final int CLASS_ABSTRACT = 0x400;
	
	public static final int INNER_CLASS_INTERFACE = 0x20;
	public static final int INNER_CLASS_ABSTRACT = 0x40;
	public static final int INNER_CLASS_SYNTHETIC = 0x100;
	public static final int INNER_CLASS_ANNOTATION = 0x200;
	public static final int INNER_CLASS_ENUM = 0x400;
	
	public static final int FIELD_VOLATILE = 0x0040;
	public static final int FIELD_TRANSIENT = 0x0080;
}
