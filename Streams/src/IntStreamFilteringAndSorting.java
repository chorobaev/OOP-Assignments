import java.security.SecureRandom;

public class IntStreamFilteringAndSorting {
    public static void main(String[] args) {
        SecureRandom random = new SecureRandom();

        random.ints(50, 1, 999)
            .filter(num -> num % 2 == 1)
            .sorted().forEach(num -> System.out.print(num + " "));
    }
}
