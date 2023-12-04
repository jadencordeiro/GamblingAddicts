package user.data_access;


import user.entity.User;
import user.entity.UserFactory;
import user.use_case.LoginUserDataAccessInterface;
import wallet.data_access.WalletDataAccessObject;
import wallet.entity.Wallet;
import wallet.use_case.WalletUpdaterInterface;
import wallet.use_case.user_transactions.UserTransactionDataAccessInterface;

import java.io.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class UserDataAccessObject implements UserDataAccessInterface, LoginUserDataAccessInterface {

    private File csvFile;

    private final Map<String, Integer> headers = new LinkedHashMap<>();

    private final Map<String, User> accounts = new HashMap<>();

    private UserFactory userFactory;
    private UserTransactionDataAccessInterface walletDataAccessObject;


    public UserDataAccessObject() {
    }

    public void FileUserDataAccessObject(String csvPath, UserFactory userFactory, UserTransactionDataAccessInterface walletDataAccessObject) throws IOException {
        this.userFactory = userFactory;
        this.walletDataAccessObject = walletDataAccessObject;

        csvFile = new File(csvPath);
        headers.put("username", 0);
        headers.put("password", 1);
        headers.put("creation_time", 2);

        if (csvFile.length() == 0) {
            save();
        } else {

            try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
                String header = reader.readLine();


                assert header.equals("username,password,creation_time");

                String row;
                while ((row = reader.readLine()) != null) {
                    String[] col = row.split(",");
                    String username = String.valueOf(col[headers.get("username")]);
                    String password = String.valueOf(col[headers.get("password")]);
                    String creationTimeText = String.valueOf(col[headers.get("creation_time")]);
                    LocalDateTime ldt = LocalDateTime.parse(creationTimeText);
                    User user = userFactory.create(username, password, ldt);
                    accounts.put(username, user);
                    Wallet wallet = walletDataAccessObject.getWallet(username);
                    user.setWallet(wallet);
                }
            }
        }
    }

//    }

    @Override
    public void save(User user) {
        accounts.put(user.getName(), user);
        this.save();
    }

    @Override
    public User get(String username) {
        return accounts.get(username);
    }

    private void save() {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(csvFile));
            writer.write(String.join(",", headers.keySet()));
            writer.newLine();

            for (User user : accounts.values()) {
                String line = String.format("%s,%s,%s", user.getName(), user.getPassword(), user.getCreationTime());
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
        return accounts.containsKey(identifier);
    }
}
