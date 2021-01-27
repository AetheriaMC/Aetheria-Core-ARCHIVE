package net.badbird5907.aetheriacore.utils;

import static java.lang.Integer.*;

public class IsInt {
	public static boolean Check(String s) {
		try {
			// checking valid integer using parseInt() method
			parseInt(s);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
}
