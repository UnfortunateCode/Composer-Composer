package scales;

import java.util.Arrays;
import java.util.LinkedList;

import javax.sound.midi.Instrument;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;

public class Scales {

	
	
	private int[][] incr = { 
				{0,0,0,1,0,-1,0},
				{0,0,-1,0,0,-1,-1},
				{0,-1,-1,-1,-1,-1,-1},
				{0,0,-1,0,0,0,-1},
				{0,-1,0,0,0,-1,0},
				{0,-1,0,0,0,0,0},
				{0,-1,0,1,1,1,0},
				{0,-1,0,0,0,-1,0},
				{0,0,-1,1,0,-1,-1},
				{0,0,-1,0,-1,-1,-1},
				{0,0,0,0,0,-1,0},
				{0,0,-1,0,0,-1,0},
				{0,0,-1,1,0,-1,0},
				{0,0,-1,1,0,-1,0},
				{0,0,0,0,0,0,0},
				{0,-1,-1,0,-1,-1,-1},
				{0,0,0,1,1,0,0},
				{0,0,0,1,0,0,0},
				{0,0,0,0,-1,-1,-1},
				{0,0,-1,0,0,0,0},
				{0,0,0,0,0,0,-1},
				{0,-1,-1,0,0,0,0},
				{0,-1,-1,0,0,-1,0},
				{0,-1,0,0,-1,-1,0},
				{0,-1,0,0,0,-1,-1},
				{0,-1,-1,0,0,-1,-1},
				{0,0,-1,1,0,0,-1}
		};
	private int[] base = {0,2,4,5,7,9,11};
	private String[] notes = {"A", "A#", "B", "C", "C#", "D", "D#", "E", "F", "F#", "G", "G#"};
	private LinkedList<myScale> results = new LinkedList<myScale> ();
	private LinkedList<myScale> totals = new LinkedList<myScale> ();
	private LinkedList<myScale> unknown = new LinkedList<myScale> ();
	
	public static void main(String[] args) {
		String[] a = {"B", "A", "C", "D", "E", "F", "G"};
		String[] b = {"B", "A"};
		String[] c = {"B", "A", "C"};
		String[] d = {"B#", "A", "C"};
		
		//Arrays.sort(a);
		Arrays.sort(b);
		Arrays.sort(c);
		Arrays.sort(d);
		
		myScale as = new myScale(a);
		myScale bs = new myScale(b);
		myScale cs = new myScale(c);
		myScale ds = new myScale(d);
		
		LinkedList<myScale> res = new LinkedList<myScale>();
		res.add(cs);
		res.add(as);
		res.add(ds);
		res.add(bs);
		
		System.out.println("as.cT bs: " + (as.compareTo(bs)) + ": " + as.equals(bs));
		System.out.println("as.cT cs: " + (as.compareTo(cs)) + ": " + as.equals(cs));
		System.out.println("cs.cT bs: " + (cs.compareTo(bs)) + ": " + cs.equals(bs));
		System.out.println("cs.cT ds: " + (cs.compareTo(ds)) + ": " + cs.equals(ds));
		System.out.println("ds.cT ds: " + (ds.compareTo(ds)) + ": " + ds.equals(ds));
		
		for (myScale ms : res) {
			System.out.println(ms);
		}
		
		res.sort(new myScale.ScaleComparator());
		System.out.println();
		for (myScale ms : res) {
			System.out.println(ms);
		}

		System.out.println(as.equals(cs));
		
		Scales scales = new Scales();
		scales.makeResults();
		scales.makeTotals();
		scales.makeUnknown();
		System.out.println("--- " + scales.results.size());
		for (myScale ms : scales.results) {
			System.out.println(ms);
		}
	}
	
	public void makeResults() {
		String[] s = new String[7];
		myScale ms;
		for (int i = 0; i < incr.length; ++i) {
			for (int n = 0; n < notes.length; ++n) {
				for (int b = 0; b < s.length; ++b) {
					s[b] = notes[(n+base[b]+incr[i][b])%12];
				}
				//Arrays.sort(s);
				ms = new myScale(s);
				if (!results.contains(ms)) {
					results.add(ms);
				}
			}
		}	
		results.sort(new myScale.ScaleComparator());
	}
	
	public void makeTotals() {
		String[] s = new String[7];
		myScale ms;
		int a = 0;
		for (int b = 1; b < 3; ++b) {
			for (int c = 3; c < 5; ++c) {
				for (int d = 4; d < 7; ++d) {
					if (c == d) {
						continue;
					}
					for (int e = 6; e < 9; ++e) {
						if (d == e) {
							continue;
						}
						for (int f = 8; f < 10; ++f) {
							if (e == f) {
								continue;
							}
							for (int g = 10; g < 12; ++g) {
								for (int i = 0; i < notes.length; ++i) {
									s[0] = notes[(i+a)%12];
									s[1] = notes[(i+b)%12];
									s[2] = notes[(i+c)%12];
									s[3] = notes[(i+d)%12];
									s[4] = notes[(i+e)%12];
									s[5] = notes[(i+f)%12];
									s[6] = notes[(i+g)%12];
									
									ms = new myScale(s);
									if(!totals.contains(ms)) {
										totals.add(ms);
									}
								}
							}
						}
					}
				}
			}
		}
	}
	
	public void makeUnknown() {
		for (myScale ms : totals) {
			if (!results.contains(ms)) {
				unknown.add(ms);
			}
		}
	}
	
	

}
