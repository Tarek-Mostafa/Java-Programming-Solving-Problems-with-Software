
/**
 * Write a description of Part2 here.
 * 
 * @author (Tarek Mostafa) 
 * @version (Nov 07 2017)
 */
public class Part2 {
    public String findSimpleGene(String dna, String startCodon, String stopCodon) {
        String result = "";
        if( Character.isUpperCase(dna.charAt(0)) ) {
            startCodon = startCodon.toUpperCase();
            stopCodon = stopCodon.toUpperCase();
        } else {
            startCodon = startCodon.toLowerCase();
            stopCodon = stopCodon.toLowerCase();
        }
        
        int start = dna.indexOf(startCodon);
        if(start == -1) {
            return "";
        }
        
        int stop = dna.indexOf(stopCodon, start);
        if(stop == -1) {
            return "";
        }
        
        // If the Gene is valid - divisible by 3
        if((stop - start) % 3 == 0) {
            return dna.substring(start, stop+3);
        }
        else {
            return "";
        }
    }
    
    public void testSimpleGene() {
        String a = "CCATCAATAACATGA";
        String ap = "CCAATGCAGCGATAC";
        String apa = "CTAATCCGGATCCGA";
        String app = "ccagcatgccagtcagctaacag";
        String appa = "CCAGCATGCCAGTAGCTAACAG";
        
        System.out.println("The string is: " + a + ". The Gene is: " + findSimpleGene(a, "ATG", "TAA"));
        System.out.println("The string is: " + ap + ". The Gene is: " + findSimpleGene(ap, "ATG", "TAA"));
        System.out.println("The string is: " + apa + ". The Gene is: " + findSimpleGene(apa, "ATG", "TAA"));
        System.out.println("The string is: " + app + ". The Gene is: " + findSimpleGene(app, "ATG", "TAA"));
        System.out.println("The string is: " + appa + ". The Gene is: " + findSimpleGene(appa, "ATG", "TAA"));
    }
    
    public static void main (String[] args) {
        Part2 gene = new Part2();
        gene.testSimpleGene();
    }
}
