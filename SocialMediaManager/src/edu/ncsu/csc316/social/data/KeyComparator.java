package edu.ncsu.csc316.social.data;

import java.util.Comparator;

/**
 * Comparator for keys
 * 
 * @author taeyoonkim
 *
 */
public class KeyComparator implements Comparator<String> {

	@Override
	public int compare(String s1, String s2) {
		
		if (s1 != null && s2 != null) {
			return s1.compareTo(s2);
		}
		else if (s1 == null && s2 != null) {
			return 1;
		}
		else {
			return -1;
		}
		

		
		
	}

}