package schedule.data_access;

import schedule.entity.Event;
import schedule.entity.EventFactory;
import schedule.service.refresh.RefreshScheduleDataAccessInterface;

import java.io.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class ScheduleDataAccessObject implements RefreshScheduleDataAccessInterface {

    private final File csvFile;
    private final Map<String, Integer> headers = new LinkedHashMap<>();

    private final Map<String, Event> events = new HashMap<>();

    private EventFactory eventFactory;

    public ScheduleDataAccessObject(String csvPath, EventFactory eventFactory) throws IOException {
        this.eventFactory = eventFactory;

        csvFile = new File(csvPath);
        headers.put("id", 0);
        headers.put("home", 1);
        headers.put("away", 2);
        headers.put("date", 3);
        headers.put("week", 4);

        if (csvFile.length() == 0) {
            save();
        } else {

            try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
                String header = reader.readLine();

                // For later: clean this up by creating a new Exception subclass and handling it in the UI.
                assert header.equals("id, home, away, date, week");

                String row;
                while ((row = reader.readLine()) != null) {
                    String[] col = row.split(",");
                    String idText = String.valueOf(col[headers.get("id")]);
                    int id = Integer.parseInt(idText);
                    String home = String.valueOf(col[headers.get("home")]);
                    String away = String.valueOf(col[headers.get("away")]);
                    String dateText = String.valueOf(col[headers.get("date")]);
                    LocalDateTime date = LocalDateTime.parse(dateText);
                    String week = String.valueOf(col[headers.get("week")]);
                    Event event = eventFactory.create(id, home, away, date, week);
                    events.put(event.getTitle(), event);
                }
            }
        }
    }

    @Override
    public void save(Event event){
        events.put(event.getTitle(), event);
        this.save();
    }

    private void save() {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(csvFile));
            writer.write(String.join(",", headers.keySet()));
            writer.newLine();

            for (Event event : events.values()) {
                String line = String.format("%s,%s,%s,%s",
                        event.getId(), event.getHome(), event.getAway(), event.getDate());
                writer.write(line);
                writer.newLine();
            }

            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Map<String, Event> getEvents() {
        return events;
    }

    @Override
    public void delete(Event event) {
        events.remove(event.getTitle());
    }

    @Override
    public boolean existsByName(String identifier) {
        return events.containsKey(identifier);
    }
}

