import java.util.HashMap;
import java.util.Map;

public class RnaTranscription {
    private Map<Character, Character> dnaToRna = new HashMap<>();

    public RnaTranscription() {
        dnaToRna.put('G', 'C');
        dnaToRna.put('C', 'G');
        dnaToRna.put('T', 'A');
        dnaToRna.put('A', 'U');
    }
    public String transcribe(String dna) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < dna.length(); i++)
            sb.append(dnaToRna.get(dna.charAt(i)));
        return sb.toString();
    }
}
