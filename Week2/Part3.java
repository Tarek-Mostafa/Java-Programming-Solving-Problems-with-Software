
/**
 * Write a description of Part3 here.
 * 
 * @author (Tarek Mostafa) 
 * @version (Nov 07 2017)
 */
public class Part3 {
    public boolean twoOccurrences(String stringa, String stringb) {
        int index1 = stringb.indexOf(stringa);
        if(index1 != -1) {
            int index2 = stringb.indexOf(stringa, index1+1);
            if( index2 != -1) {
                return true;
            }
        } else {
            return false;
        }
        return false;
    }
    
    public void testing() {
        System.out.println(twoOccurrences("by", "A story by Abby Long"));
        System.out.println(twoOccurrences("a", "banana"));
        System.out.println(twoOccurrences("atg", "ctgtatgta"));
        
        System.out.println(lastPart("an", "banana"));
        System.out.println(lastPart("zoo", "forest"));
        System.out.println(lastPart("zoo", "farzookeeper"));
    }
    
    public String lastPart(String stringa, String stringb) {
        if(stringb.indexOf(stringa) != -1) {
            int index = stringb.indexOf(stringa);
            return stringb.substring(index+stringa.length(), stringb.length());
        } else {
            return stringb;
        }
    }
    
    public static void main() {
        Part3 howMany = new Part3();
        howMany.testing();
    }
}
