import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Scrabble {
    private String value;
    private List<Character> one = new ArrayList<>();
    private List<Character> two = new ArrayList<>();
    private List<Character> three = new ArrayList<>();
    private List<Character> four = new ArrayList<>();
    private List<Character> five = new ArrayList<>();
    private List<Character> eight = new ArrayList<>();
    private List<Character> ten = new ArrayList<>();

    public Scrabble(String value) {
        this.value = value;
        Collections.addAll(one, 'A', 'E', 'I', 'O', 'U', 'L', 'N', 'R', 'S', 'T');
        Collections.addAll(two, 'D', 'G');
        Collections.addAll(three, 'B', 'C', 'M', 'P');
        Collections.addAll(four, 'F', 'H', 'V', 'W', 'Y');
        Collections.addAll(five, 'K');
        Collections.addAll(eight, 'J', 'X' );
        Collections.addAll(ten, 'Q', 'Z');
    }

    public int getScore() {
        int sum = 0;
        for (int i = 0; i < value.length(); i++) {
            char ch = (char) (value.charAt(i) & 223);
            if (one.contains(ch)) sum += 1;
            if (two.contains(ch)) sum += 2;
            if (three.contains(ch)) sum += 3;
            if (four.contains(ch)) sum += 4;
            if (five.contains(ch)) sum += 5;
            if (eight.contains(ch)) sum += 8;
            if (ten.contains(ch)) sum += 10;
        }
        return sum;
    }
}
