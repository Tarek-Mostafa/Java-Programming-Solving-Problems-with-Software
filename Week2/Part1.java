
/**
 * Write a description of Part1 here.
 * 
 * @author (Tarek Mostafa) 
 * @version (Nov 07 2017)
 */
 
public class Part1 {
    public String findSimpleGene(String dna) {
        String result = "";
        int start = dna.indexOf("ATG");
        if(start == -1) {
            return "";
        }
        int stop = dna.indexOf("TAA", start);
        if(stop == -1) {
            return "";
        }
        
        if((stop - start) % 3 == 0) {
            return dna.substring(start, stop+3);
        }
        else {
            return "";
        }
    }
    
    public void testSimpleGene() {
        String a = "AAATGCCCTAACTAGATTAAGAAACC";
		String ap = "CCAATGCAGCGATAC";
		String apa = "CTAATCCGGATCCGA";
		String app = "CCAGCATGCCAGTCAGCTAACAG";
		String appa = "CCAGCATGCCAGTAGCTAACAG";
		
		System.out.println("The string is: " + a + ". The Gene is: " + findSimpleGene(a));
		System.out.println("The string is: " + ap + ". The Gene is: " + findSimpleGene(ap));
		System.out.println("The string is: " + apa + ". The Gene is: " + findSimpleGene(apa));
		System.out.println("The string is: " + app + ". The Gene is: " + findSimpleGene(app));
		System.out.println("The string is: " + appa + ". The Gene is: " + findSimpleGene(appa));
    }
    
    public static void main (String[] args) {
        Part1 gene = new Part1();
        gene.testSimpleGene();
    }
}
