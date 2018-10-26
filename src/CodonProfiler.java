import java.util.*;

public class CodonProfiler {
	
	/**
	 * Count how many times each codon in an array of codons occurs
	 * in a strand of DNA. Return int[] such that int[k] is number
	 * of occurrences of codons[k] in strand. Strand codons can start
	 * at all valid indexes that are multiples of 3: 0, 3, 6, 9, 12, ...
	 * @param strand is DNA to be analyzed for codon occurrences.
	 * @param codons is an array of strings, each has three characters
	 * @return int[] such that int[k] is number of occurrences of codons[k] in 
	 * strand. 
	 */
	public int[] getCodonProfile(IDnaStrand strand, String[] codons) {
		HashMap<String,Integer> map = new HashMap<>();
		int[] ret = new int[codons.length];
				
		for(int k=0; k < codons.length; k++) {
			Iterator<Character> iter = strand.iterator();
			while (iter.hasNext()) {
				char a = iter.next();
				char b = 'z';           // not part of any real codon
				char c = 'z';
				if (iter.hasNext()) {
					b = iter.next();
				}
				if (iter.hasNext()) {
					c = iter.next();
				}
				String cod = ""+a+b+c;
				//zero value at key cod
				if (!map.containsKey(cod)) {
					map.put(cod, 0);
				}
				//add value to map at key cod, adds 1
				map.put(cod, map.get(cod)+1);
			}
			//using a for each loop, but marks where in loop using z
			int z=0;
			for (String key: codons) {
				ret[z]=map.get(key);
				z++;
			}
		}
		return ret;
	}
}
