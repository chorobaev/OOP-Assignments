import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProteinTranslator {
    private Map<String, String> codons = new HashMap<>();

    public ProteinTranslator() {
        codons.put("AUG", "Methionine");
        codons.put("UUU", "Phenylalanine");
        codons.put("UUC", "Phenylalanine");
        codons.put("UUA", "Leucine");
        codons.put("UUG", "Leucine");
        codons.put("UCU", "Serine");
        codons.put("UCC", "Serine");
        codons.put("UCA", "Serine");
        codons.put("UCG", "Serine");
        codons.put("UAU", "Tyrosine");
        codons.put("UAC", "Tyrosine");
        codons.put("UGU", "Cysteine");
        codons.put("UGC", "Cysteine");
        codons.put("UGG", "Tryptophan");
        codons.put("UAA", "STOP");
        codons.put("UAG", "STOP");
        codons.put("UGA", "STOP");
    }

    public List<String> translate(String s) {
        List<String> proteins = new ArrayList<>();
        for (int i = 0; i < s.length() - 2; i += 3) {
            String codon = s.substring(i, i+3);
            String protein = codons.get(codon);
            if (protein.equals("STOP")) break; else proteins.add(protein);
        }
        return proteins;
    }
}
