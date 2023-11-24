package schedule.service.refresh;

import java.time.LocalDateTime;

public class RefreshInputData {
    final private LocalDateTime date;

    public RefreshInputData() {
        this.date = LocalDateTime.now();
    }

}
