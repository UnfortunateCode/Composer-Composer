package scales;

import java.util.Comparator;

public class myScale implements Comparable<myScale> {
	String[] data;
	
	public myScale() {
		data = new String [0];
	}
	
	public myScale(String[] d) {
		data = d.clone();
	}
	
	@Override
	public int compareTo(myScale comp) {
		for (int i = 0; i < data.length && i < comp.data.length; ++i) {
			if (data[i].compareTo(comp.data[i]) != 0) {
				return data[i].compareTo(comp.data[i]);
			}
		}
		
		return data.length-comp.data.length;
	}
	
	public String toString() {
		String s = "[";
		for (int i = 0; i < data.length-1; ++i) {
			s += data[i] + ", ";
		}
		s += data[data.length-1] + "]";
		return s;
	}
	
	public static class ScaleComparator implements Comparator<myScale> {

		@Override
		public int compare(myScale arg0, myScale arg1) {
			// TODO Auto-generated method stub
			return arg0.compareTo(arg1);
		}
		
	}
	
	@Override
	public boolean equals(Object ms) {
		if (ms.getClass() == (new myScale()).getClass()) {
			return (this.compareTo((myScale)ms) == 0);
		}
		return false;
	}

}
