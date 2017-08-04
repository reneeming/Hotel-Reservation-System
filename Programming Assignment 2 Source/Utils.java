 /**
@author Ming Ni
@version 1.1

COP5007 Project #: 2
File Name: Utils.java
*/

public class Utils {
	/**
	Check if s is large than zero integer 
	*/
	public static boolean isPositiveInteger(String s) {
		if (s.isEmpty()) {
			return false;
		}
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) > '9' || s.charAt(i) < '0')
				return false;
		}
		if (Integer.parseInt(s) == 0) return false;
		return true;
	}
	
	/**
	Check s is zero 
	*/
	public static boolean isZero(String s) {
		if (s.isEmpty()) {
			return false;
		}
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) != '0')
				return false;
		}
		return true;
	}
}
