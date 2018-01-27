
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class Part3 {
    public int findStopCodon(String dna, int startIndex, String stopCodon) {
		int currIndex = dna.indexOf(stopCodon, startIndex + 3);
		while(currIndex != -1) {
			int diff = currIndex - startIndex;
			if(diff % 3 == 0) {
				return currIndex;
			} else {
				currIndex = dna.indexOf(stopCodon, currIndex + 1);
			}
		}

		return -1;
	}
	
	public String findGene(String dna, int where) {
		int startIndex = dna.indexOf("ATG", where);
		if(startIndex == -1 || where == -1) {
			return "";
		}

		int taaIndex = findStopCodon(dna, startIndex, "TAA");
		int tagIndex = findStopCodon(dna, startIndex, "TAG");
		int tgaIndex = findStopCodon(dna, startIndex, "TGA");

		int minIndex = 0;
		
		if(taaIndex == -1 || (tgaIndex != -1 && tgaIndex < taaIndex)) {
			minIndex = tgaIndex;
		} else {
			minIndex = taaIndex;
		}

		if(minIndex == -1 || (tagIndex != -1 && tagIndex < minIndex)) {
			minIndex = tagIndex;
		}

		if(minIndex == -1) {
			return "";
		}
		
		return dna.substring(startIndex, minIndex + 3);
	}

	public void howManyGenes() {
		int startIndex = 0;
		int count = 0;
	
		FileResource fr = new FileResource("GRch38dnapart.fa");
		String dna = fr.asString().toUpperCase();

		while (true) {
			String gene = findGene(dna, startIndex);
			
			if (gene == "") {
				break;
			}

			startIndex = dna.indexOf(gene, startIndex) + gene.length();

			if(gene.length() > 60) {
				count++;
			}
		}

		System.out.println("How many genes are: " + count);
	}
	
	public StorageResource getAllGenes(String dna) {
		StorageResource sr = new StorageResource();
		int startIndex = 0;
		while (true) { 
			String gene = findGene(dna, startIndex);
			
			if (gene == "") {
				break;
			}
			
			sr.add(gene);

			startIndex = dna.indexOf(gene, startIndex) + gene.length();

		}
		return sr;
	}
	
	public double cgRatio(String dna) {
		double charRatio = 0.0;
		double strLen = dna.length();

		for(int i = 0; i < strLen; i++) {
			if(dna.charAt(i) == 'C' || dna.charAt(i) == 'G') {
				charRatio++;
			}
		}

		double ratio = charRatio / strLen;
		return ratio;
	}

	public int countCTG(String dna) {
		int startIndex = 0;
		int count = 0;
		int index = dna.indexOf("CTG", startIndex);
		
		while(true) {
			if(index == -1 || count > dna.length()) {
				break;
			}
			
			count++;
			index = dna.indexOf("CTG", index+3);
		}
		return count;
	}

	public void processGenes() {
		String Longest = "";
		FileResource fr = new FileResource("GRch38dnapart.fa");
		String dna = fr.asString().toUpperCase();
		StorageResource sr = getAllGenes(dna);

		for(String s : sr.data()) {
			if(s.length() > Longest.length()) {
				Longest = s;
			}
		}
		System.out.println(Longest.length());
	}
}
