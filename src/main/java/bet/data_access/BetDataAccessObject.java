package bet.data_access;

import bet.entity.BetFactory;
import bet.entity.Bet;
import bet.use_case.BetTransactionDataAccessInterface;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;


public class BetDataAccessObject implements BetDataAccessInterface, BetTransactionDataAccessInterface{

    private File csvFile;

    private BetFactory betFactory;
    private final Map<String, Integer> headers = new LinkedHashMap<>();

    private Map<String, ArrayList<Bet>> bets = new HashMap<>();
    public BetDataAccessObject(String csvPath,BetFactory betFactory) throws IOException{
        this.betFactory = betFactory;
        csvFile = new File(csvPath);
        headers.put("Event", 0);
        headers.put("Wager", 1);
        headers.put("BetOnHome", 2);
        headers.put("User", 3);

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
                    String user = String.valueOf(col[headers.get("User")]);
                    Bet bet = betFactory.create(eventTitle);
                    bet.setWager(wager);
                    bet.setBettingSide(betOnHome);

                    if(!bets.containsKey(user)){
                        bets.put(user, new ArrayList<>());
                    }
                    bets.get(user).add(bet);
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

            for (String user : bets.keySet()) {
                for (Bet bet: bets.get(user)){
                    String line = String.format("%s,%s,%s,%s", bet.getEventTitle(), bet.getWager(), bet.getBettingSide(), user);
                    writer.write(line);
                    writer.newLine();
                }
            }

            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean existsByName(String user, String identifier) {
        if(bets.containsKey(user)){
            for (Bet bet: bets.get(user)){
                if (bet.getEventTitle().equals(identifier)){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void save(String user, Bet bet) {
        bets.get(user).add(bet);
        this.save();
    }

    @Override
    public ArrayList<Bet> getBets(String user) {
        return bets.get(user);
    }

    @Override
    public Bet get(String user, String eventTitle) {
        for (Bet bet : bets.get(user)) {
            if (bet.getEventTitle().equals(eventTitle)) {
                return bet;
            }
        }
        return null;
    }
}
