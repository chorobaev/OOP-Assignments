public class PangramChecker {
    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";

    public boolean isPangram(String s) {
        String source = s.toLowerCase();
        for (int i = 0; i < ALPHABET.length(); i++) {
            if (source.indexOf(ALPHABET.charAt(i)) == -1) return false;
        }

        return true;
    }
}
