package de.kroesch.util;

import java.lang.reflect.Method;

/**
 * Logging helper for inspecting JavaBean conform classes.
 * 
 * This wraps around ordinary Java objects and print a list
 * of all member values which can be reached via "get*" or "is*" method
 * if the toString() method of the original class does not provide these informations.
 * 
 */
public class Inspecteur {
	
	private final Object toInspect;

	/**
	 * Wrapping constructor.
	 */
	public Inspecteur(final Object toInspect) {
		this.toInspect = toInspect;
	}

	/**
	 * Dumps the values of all getter methods.
	 */
  @Override
	public String toString() {
		String result = "Inspecteur examining instance [" + toInspect + "] of " + toInspect.getClass() + "\n";
		Method[] methods = toInspect.getClass().getMethods();
		for (int index = 0; index < methods.length; index++) {
			try {
				// Call only getter methods
				if (methods[index].getName().startsWith("get") || methods[index].getName().startsWith("is"))
					result += methods[index].getName() + "() = " + methods[index].invoke(toInspect, new Object[]{}) + "\n";
			} catch (Exception e) {
				result += "Cannot invoke " + methods[index].getName() + ": " + e + "\n";
			}
		}
		return result;
	}
}
