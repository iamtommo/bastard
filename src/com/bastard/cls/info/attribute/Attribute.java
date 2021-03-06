package com.bastard.cls.info.attribute;



public enum Attribute {
	NOT_IMPLEMENTED("", AbstractAttribute.class),
	CODE("Code", CodeAttribute.class),
	CONSTANT_VALUE("ConstantValue", ConstantValueAttribute.class),
	LINE_NUMBER_TABLE("LineNumberTable", LineNumberTableAttribute.class),
	INNER_CLASSES("InnerClasses", InnerClassesAttribute.class),
	ENCLOSING_METHOD("EnclosingMethod", EnclosingMethodAttribute.class),
	LOCAL_VARIABLE_TABLE("LocalVariableTable", LocalVariableTableAttribute.class),
	LOCAL_VARIABLE_TYPE_TABLE("LocalVariableTypeTable", LocalVariableTypeTableAttribute.class),
	SOURCE_FILE("SourceFile", SourceFileAttribute.class);
	//STACK_MAP_TABLE("StackMapTable", StackMapTableAttribute.class);
	
	Class<? extends AbstractAttribute> attributeClass;
	String tag;
	
	Attribute(String tag, Class<? extends AbstractAttribute> attributeClass) {
		this.tag = tag;
		this.attributeClass = attributeClass;
	}
	
	public Class<? extends AbstractAttribute> getAttributeClass() {
		return attributeClass;
	}
	
	public String getTag() {
		return tag;
	}
	
	public static Attribute getAttribute(String tag) {
		for (Attribute a : Attribute.values()) {
			if (a.getTag().equals(tag)) {
				return a;
			}
		}
		return NOT_IMPLEMENTED;
	}
	
}
