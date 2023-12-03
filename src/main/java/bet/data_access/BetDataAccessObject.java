package bet.data_access;

import bet.entity.BetFactory;
import bet.entity.Bet;
import bet.use_case.BetTransactionDataAccessInterface;
import schedule.entity.Event;
import user.entity.User;

import java.io.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;


public class BetDataAccessObject implements BetDataAccessInterface, BetTransactionDataAccessInterface{

    private File csvFile;

    private BetFactory betFactory;
    private final Map<String, Integer> headers = new LinkedHashMap<>();

    private Map<String, Bet> bets = new HashMap<>();
    public BetDataAccessObject(String csvPath,BetFactory betFactory) throws IOException{
        this.betFactory = betFactory;
        csvFile = new File(csvPath);
        headers.put("Event", 0);
        headers.put("Wager", 1);
        headers.put("BetOnHome", 2);

        if (csvFile.length() == 0) {
            save();
        } else {

            try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
                String header = reader.readLine();


                assert header.equals("Event,Wager,BetOnHome");

                String row;
                while ((row = reader.readLine()) != null) {
                    String[] col = row.split(",");
                    String eventTitle = String.valueOf(col[headers.get("Event")]);
                    Float wager = Float.valueOf(col[headers.get("Wager")]);
                    Boolean betOnHome = Boolean.valueOf(col[headers.get("BetOnHome")]);
                    Bet bet = betFactory.create(eventTitle);
                    bet.setWager(wager);
                    bet.setBettingSide(betOnHome);
                    bets.put(eventTitle, bet);
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

            for (Bet bet : bets.values()) {
                String line = String.format("%s,%s,%s", bet.getEventTitle(), bet.getWager(), bet.getBettingSide());
                writer.write(line);
                writer.newLine();
            }

            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean existsByName(String identifier) {
        return bets.containsKey(identifier);
    }

    @Override
    public void save(Bet bet) {
        bets.put(bet.getEventTitle(), bet);
        this.save();
    }

    @Override
    public Bet get(String eventTitle) {
        return bets.get(eventTitle);
    }


}
