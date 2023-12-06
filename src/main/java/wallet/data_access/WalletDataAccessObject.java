package wallet.data_access;

import bet.entity.Bet;
import wallet.entity.WalletFactory;
import wallet.entity.Wallet;
import wallet.use_case.user_transactions.UserTransactionDataAccessInterface;

import java.io.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static bet.entity.Bet.parseBet;

public class WalletDataAccessObject {
    private static final String CSV_FILE_PATH = "wallets.csv";
    private static final String CSV_SEPARATOR = ",";

    public void saveWallet(Wallet wallet) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(CSV_FILE_PATH, true))) {
            // Append the wallet information to the CSV file
            writer.println(formatWalletForCSV(wallet));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Map<String, Wallet> loadWallets() {
        Map<String, Wallet> wallets = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(CSV_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Parse each line from the CSV file to create a Wallet object
                Wallet wallet = parseWalletFromCSV(line);
                wallets.put(wallet.getWalletName(), wallet);
            }
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception based on your application's requirements
        }

        return wallets;
    }

    private String formatWalletForCSV(Wallet wallet) {
        // Format the wallet information as a CSV line
        StringBuilder sb = new StringBuilder();
        sb.append(wallet.getWalletName()).append(CSV_SEPARATOR);
        sb.append(wallet.getBalance()).append(CSV_SEPARATOR);
        sb.append(formatTransactionHistoryForCSV(wallet.getTransactionHistory())).append(CSV_SEPARATOR);
        //sb.append(formatBetHistoryForCSV(wallet.getBetHistory()));
        return sb.toString();
    }

    private Wallet parseWalletFromCSV(String line) {
        // Parse the CSV line to create a Wallet object
        String[] tokens = line.split(CSV_SEPARATOR);
        String name = tokens[0];
        float balance = Float.parseFloat(tokens[1]);
        Map<LocalDateTime, Float> transactionHistory = parseTransactionHistoryFromCSV(tokens[2]);
        Map<Bet, Float> betHistory = parseBetHistoryFromCSV(tokens[3]);
        WalletFactory walletFactory = new WalletFactory();
        Wallet wallet = walletFactory.create(name);
        wallet.setBalance(balance);
        for (Map.Entry<LocalDateTime, Float> trans: transactionHistory.entrySet()) {
            wallet.setTransactionHistory(trans.getKey(), trans.getValue());
        }

        for (Map.Entry<Bet, Float> bets: betHistory.entrySet()) {
            wallet.setBets(bets.getKey(), bets.getValue());
        }


        return wallet;
    }

    private String formatTransactionHistoryForCSV(Map<LocalDateTime, Float> transactionHistory) {
        // Format the transaction history as a CSV line
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<LocalDateTime, Float> entry : transactionHistory.entrySet()) {
            sb.append(entry.getKey()).append("=").append(entry.getValue()).append("|");
        }
        return sb.toString();
    }

    private Map<LocalDateTime, Float> parseTransactionHistoryFromCSV(String transactions) {
        // Parse the transaction history from the CSV line
        Map<LocalDateTime, Float> transactionHistory = new HashMap<>();
        String[] entries = transactions.split("\\|");
        for (String entry : entries) {
            String[] parts = entry.split("=");
            LocalDateTime dateTime = LocalDateTime.parse(parts[0]);
            float amount = Float.parseFloat(parts[1]);
            transactionHistory.put(dateTime, amount);
        }
        return transactionHistory;
    }

    private String formatBetHistoryForCSV(Map<Bet, Float> betHistory) {
        // Format the bet history as a CSV line
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Bet, Float> entry : betHistory.entrySet()) {
            sb.append(entry.getKey()).append("=").append(entry.getValue()).append("|");
        }
        return sb.toString();
    }

    private Map<Bet, Float> parseBetHistoryFromCSV(String bets) {
        // Parse the bet history from the CSV line
        Map<Bet, Float> betHistory = new HashMap<>();
        String[] entries = bets.split("\\|");
        for (String entry : entries) {
            String[] parts = entry.split("=");
            Bet bet = parseBet(parts[0]); // Implement parseBet method in your Bet class
            float amount = Float.parseFloat(parts[1]);
            betHistory.put(bet, amount);
        }
        return betHistory;
    }
}