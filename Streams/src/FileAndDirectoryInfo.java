import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class FileAndDirectoryInfo {
    private static final String pathOfDirectory = "/home/nurbol/Downloads/OOP Example Dir";

    public static void main(String[] args) throws IOException {
        Path path = Paths.get(pathOfDirectory);
        if (Files.exists(path)) {
            DirectoryStream<Path> directoryStream =
                Files.newDirectoryStream(path);
            Stream<Path> stream = StreamSupport.stream(directoryStream.spliterator(), false);
            stream
                .map(item -> item.getFileName().toString())
                .filter(item -> Pattern.matches(".*(\\..+)$", item))
                .map(item -> item.substring(item.lastIndexOf('.') + 1))
                .collect(Collectors.groupingBy(item -> item))
                .forEach((item, typesOfFiles) -> System.out.println(item + ": " + typesOfFiles.size()));
        } else {
            System.out.printf("%s does not exist%n", path);
        }
    }
}