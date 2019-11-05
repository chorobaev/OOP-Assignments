// Fig. 17.22: StreamOfLines.java
// Counting word occurrences in a text file.
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class StreamOfLines {
    public static void main(String[] args) throws IOException {
        // Regex that matches one or more consecutive whitespace characters
        Pattern pattern = Pattern.compile("");

        // count occurrences of each word in a Stream<String> sorted by word
        Map<String, Long> wordCounts =
            Files.lines(Paths.get("Chapter2Paragraph.txt"))
                .flatMap(pattern::splitAsStream)
                .filter(ch -> Pattern.matches("[A-Za-z]", ch))
                .collect(Collectors.groupingBy(String::toLowerCase,
                    TreeMap::new, Collectors.counting()));

        // display the words grouped by starting letter
        wordCounts.entrySet()
            .stream()
            .collect(
                Collectors.groupingBy(entry -> entry.getKey().charAt(0),
                    TreeMap::new, Collectors.toList()))
            .forEach((letter, wordList) -> {
                wordList.forEach(word -> System.out.printf(
                    "%13s: %d%n", word.getKey(), word.getValue()));
            });
    }
}