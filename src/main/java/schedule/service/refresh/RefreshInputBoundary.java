package schedule.service.refresh;

import java.time.LocalDateTime;

public interface RefreshInputBoundary {
    void execute(RefreshInputData refreshInputData);
}
