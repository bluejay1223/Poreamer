package edu.ncsu.csc316.social.data;

import java.util.Comparator;

/**
 * Comparator for connections
 * 
 * @author taeyoonkim
 *
 */
public class ConnectionComparator implements Comparator<Connection> {
	
	private int dateCompare(Connection o1, Connection o2) {
		if (o1.getDate().getTime() < o2.getDate().getTime()) {
			return -1;
		}
		else {
			return 1;
		}
	}

	@Override
	public int compare(Connection o1, Connection o2) {
		
		String p1 = o1.getPeople()[0];
		String p2 = o1.getPeople()[1];
		
		String p3 = o2.getPeople()[0];
		String p4 = o2.getPeople()[1];
		
		if (p1.equals(p3)) {
			if (p2.compareTo(p4) == 0) {
				
				return dateCompare(o1, o2);
			}
			return p2.compareTo(p4);
		}
		else if (p1.equals(p4)){
			if (p2.compareTo(p3) == 0) {
				return dateCompare(o1, o2);
			}
			return p2.compareTo(p3);
		}
		else if (p2.equals(p3)) {
			if (p1.compareTo(p4) == 0) {
				return dateCompare(o1, o2);
			}
			return p1.compareTo(p4);
		}
		else {
			if (p1.compareTo(p3) == 0) {
				return dateCompare(o1, o2);
			}
			return p1.compareTo(p3);
		}
		
	}

}

