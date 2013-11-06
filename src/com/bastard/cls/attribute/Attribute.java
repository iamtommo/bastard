package com.bastard.cls.attribute;

public enum Attribute {
	NOT_IMPLEMENTED("", AbstractAttribute.class),
	CODE("Code", CodeAttribute.class),
	CONSTANT_VALUE("ConstantValue", ConstantValueAttribute.class);
	
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
