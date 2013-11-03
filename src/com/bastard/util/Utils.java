package com.bastard.util;


/**
 * Static utility methods.
 * @author Tommo
 *
 */
public class Utils {
	
	private static int variableCounter = 0;
	
	public static final String getVariableName() {
		return "var" + ++variableCounter;
	}
	
	public static String getVariableType(String s) {
		s = s.toLowerCase();
		if (s.equals("i")) {
			return "int";
		} else if (s.equals("l")) {
			return "long";
		} else if (s.equals("s")) {
			return "short";
		} else if (s.equals("b")) {
			return "byte";
		} else if (s.equals("c")) {
			return "char";
		} else if (s.equals("f")) {
			return "float";
		} else if (s.equals("d")) {
			return "double";
		} else if (s.equals("v")) {
			return "void";
		}
		//if its not one of the above, it's an object reference, so just return the original
		return s;
	}
	
	public static String getMethodArgs(String string) {
		int index = 0;
		boolean isNextArray = false;
		boolean firstArg = true;
		StringBuilder bldr = new StringBuilder();
		while (index < string.length()) {
			String c = String.valueOf(string.charAt(index));
			String arg = null;
			if (c.equals("[")) {
				isNextArray = true;
			} else if (c.equals("L")) {
				arg = string.substring(index, string.indexOf(";", index)).replace("L", "");
				index = string.indexOf(";", index);
			}
			index++;
			if (arg != null) {
				if (!firstArg)
					bldr.append(", ");
				bldr.append(arg).append(isNextArray ? "[] " : " ").append(getVariableName());
				firstArg = false;
			}
		}
		return bldr.toString();
	}
	
	public static String getReturnType(String s) {
		String[] split = s.split("\\)");
		return getVariableType(split[1].replace(";", ""));
	}

}
