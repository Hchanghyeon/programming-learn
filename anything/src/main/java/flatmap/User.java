package flatmap;

import java.util.List;

public record User(Long id, String name, List<AccessLog> accessLogs) {
}
