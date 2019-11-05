import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class DuplicateWordRemoval {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String sentence = scanner.nextLine();
        Arrays.stream(sentence.split("\\s+"))
            .map(String::toLowerCase)
            .collect(Collectors.groupingBy(item -> item)).keySet()
            .stream()
            .sorted(String::compareTo)
            .forEach(System.out::println);
    }
}
