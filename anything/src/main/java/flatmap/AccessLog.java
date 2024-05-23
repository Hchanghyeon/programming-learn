package flatmap;

import java.time.LocalDate;

public record AccessLog(Long id, String ip, LocalDate accessAt, long responseTime) {
}
