import java.time.LocalDate;
import java.time.LocalDateTime;

public class Gigasecond {
    private static final long giga = 1000000000;
    private LocalDateTime afterGiga;

    public Gigasecond(LocalDate date) {
        afterGiga = date.atStartOfDay().plusSeconds(giga);
    }

    public Gigasecond(LocalDateTime date) {
        afterGiga = date.plusSeconds(giga);
    }

    public LocalDateTime getDateTime() {
        return afterGiga;
    }
}
