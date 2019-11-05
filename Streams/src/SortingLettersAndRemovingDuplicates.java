import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class SortingLettersAndRemovingDuplicates {
    public static void main(String[] args) {
        List<Character> letters = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 30; i++) {
            letters.add((char) (random.nextInt(26) + 65));
        }

        System.out.println("Sort the List in ascending order:");
        letters.stream().sorted((i1, i2) -> i2 - i1)
            .map(item -> item + " ")
            .forEach(System.out::print);

        System.out.println("\n\nSort the List in descending order:");
        letters.stream().sorted()
            .forEach(item -> System.out.print(item + " "));

        System.out.println("\n\nDisplay the List in ascending order with duplicates removed:");
        letters.stream().collect(Collectors.groupingBy(item -> item)).keySet()
            .stream().sorted().map(i -> i + " ").forEach(System.out::print);
    }
}
