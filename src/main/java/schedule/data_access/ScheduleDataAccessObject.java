package schedule.data_access;

import schedule.entity.Event;

import java.io.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class ScheduleDataAccessObject {

    private final File csvFile;
    private final Map<String, Integer> headers = new LinkedHashMap<>();

    private final Map<String, Event> events = new HashMap<>();

    public ScheduleDataAccessObject(String csvPath) throws IOException {

        csvFile = new File(csvPath);
        headers.put("id", 0);
        headers.put("home", 1);
        headers.put("away", 2);
        headers.put("date", 3);

        if (csvFile.length() == 0) {
            save();
        } else {

            try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
                String header = reader.readLine();

                // For later: clean this up by creating a new Exception subclass and handling it in the UI.
                assert header.equals("id, home , away, date");

                String row;
                while ((row = reader.readLine()) != null) {
                    String[] col = row.split(",");
                    String home = String.valueOf(col[headers.get("home")]);
                    String away = String.valueOf(col[headers.get("away")]);

                }
            }
        }
    }

    private void save() {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(csvFile));
            writer.write(String.join(",", headers.keySet()));
            writer.newLine();

            for (Event event: events.values()) {
                String line = String.format("%s,%s,%s",
                        event.getId(), event.getHome(), event.getAway(), event.getDate());
                writer.write(line);
                writer.newLine();
            }

            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

