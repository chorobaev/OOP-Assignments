public class Acronym {
    private String value;

    public Acronym(String value) {
        this.value = value;
    }

    public String get() {
        StringBuilder sb = new StringBuilder();
        String[] spd = value.split("[ \\-]");
        for (String word : spd) {
            char l = getLetter(word);
            if (l != 32) sb.append(l);
        }
        return sb.toString();
    }

    private char getLetter(String w) {
        for (int i = 0; i < w.length(); i++) {
            char ch = w.charAt(i);
            if (isLetter(ch)) return (char) (ch & 223);
        }
        return 32;
    }

    private boolean isLetter(char ch) {
        char t = (char) (ch & 223);
        return t >= 65 && t <= 92;
    }
}
