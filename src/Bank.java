import java.util.*;

public class Bank {
    private Client loggedInClient;
    private Map<Client, List<Account>> clientAccountMap;

    // getter method for loggedInClient
    public Client getLoggedInClient() {
        return loggedInClient;
    }

    // getter method for clientAccountMap
    public Map<Client, List<Account>> getClientAccountMap() {
        return clientAccountMap;
    }

    // constructor
    public Bank() {
        // instantiate an empty HashMap when bank object is created so that its not null and is ready to use
        this.clientAccountMap = new HashMap<>();
        // since no client is logged in when the bank is created it is set to null
        this.loggedInClient = null;
    }

    /* login method algorithm
    * step1: iterates through the keys (Clients) in HashMap
    * step2: validate the client id and pin with the arguments passed
    * step3: if true, then set loggedInClient to client (return true)
    * step4: otherwise, return false
    * */
    public boolean login(int id, int pin) {
        for (Client client : clientAccountMap.keySet()) {
            if (client.getID() == id && client.pinMatch(pin)) {
                loggedInClient = client;
                System.out.println("Login successful!");
                return true;
            }
        }
        System.out.println("Invalid ID or PIN.");
        return false;
    }

    /* logout method algorithm
    * step1: check if client is already logged in i.e, (loggedInClient != null)
    * step2: if true, then set loggedInClient to null
    * step3: return true (logged out successfully)
    * step4: otherwise return false
    * */
    public boolean logout() {
        if (loggedInClient != null) {
            loggedInClient = null;
            System.out.println("Logged out successfully.");
            return true;
        }
        return false;
    }

    /* addClient method algorithm
    * step1: create a new client object with a given clientID and pin
    * step2: check if that client does not exist in clientAccountMap
    * step3: if true, then put that new client object in the clientAccountMap with an empty list for client's accounts
    * step4: if false, return false
    * */
    public boolean addClient(int clientID, int pin) {
        Client newClient = new Client(clientID, pin);
        if (!clientAccountMap.containsKey(newClient)) {
            clientAccountMap.put(newClient, new ArrayList<>()); //(since the client is just created, newClient won't be having any account to begin with. Thus, an empty ArrayList)
            return true;
        }
        System.out.println("Client already exists!");
        return false;
    }

    /* removeClient method algorithm
    * step1: if client exists, only then remove the loggedInClient from the clientAccountMap
    * step2: set loggedInClient to null (return true)
    * step3: otherwise return false
    * */
    public boolean removeClient() {
        if (loggedInClient != null) {
            clientAccountMap.remove(loggedInClient);
            loggedInClient = null;
            return true;
        }
        return false;
    }

    /* addAccount method algorithm
    * step1: check if the loggedInClient exists
    * step2: get all accounts of the loggedInClient
    * step3: add the newly created account to the accounts list
    * */
    public boolean addAccount(String accountNumber, AccountType accountType) {
        if (loggedInClient == null) {
            System.out.println("No client is logged in. Can't create the account!");
            return false;
        }
        Account newAccount = new Account(accountNumber, accountType);
        clientAccountMap.get(loggedInClient).add(newAccount); // adding newAccount to the Accounts list
        return true;
    }

    /* removeAccount method algorithm
    * step1: check if loggedInClient exists
    * step2: get all the accounts of the loggedInClient
    * step3: remove the account which matches 'accountNumber' (return true)
    * step4: otherwise, return false
    * */
    public boolean removeAccount(String accountNumber) {
        if (loggedInClient != null) {
            List<Account> accounts = clientAccountMap.get(loggedInClient);
            return accounts.removeIf(account -> account.getAccountNumber().equals(accountNumber));
        }
        System.out.println("Account does not exist!");
        return false;
    }

    /* showAccounts method algorithm
    * step1: check if loggedInClient exists
    * step2: get all the accounts of the loggedInClient and print them all
    * step3: print "No client is logged in"
    * */
    public void showAccounts() {
        if (loggedInClient != null) {
            List<Account> accounts = clientAccountMap.get(loggedInClient);
            System.out.println("Accounts for Client ID: " + loggedInClient.getID());
            for (Account acc : accounts) {
                acc.getOverview();
            }
        } else {
            System.out.println("No client is logged in!");
        }
    }

    public boolean transfer(Account sourceAccount, Account destinationAccount, double amount) {
        if (sourceAccount.withdraw(amount)) {
            destinationAccount.deposit(amount);
            return true;
        }
        return false;
    }

    // helper method to find an account by number
    public Account findAccountByNumber(Client client, String accountNumber) {
        List<Account> accounts = clientAccountMap.get(client);
        if (accounts != null) {
            for(Account account: accounts ) {
                if(account.getAccountNumber().equals(accountNumber)) {
                    return account;
                }
            }
        }
        return null;
    }

    // helper method to deposit to a particular account
    public boolean depositToAccount(String accountNumber, double amount) {
        if (loggedInClient != null) {
            Account account = findAccountByNumber(loggedInClient, accountNumber);
            if (account != null) {
                return account.deposit(amount);
            }
            System.out.println("Account does not exists!");
            return false;
        }
        System.out.println("Unauthorized client!");
        return false;
    }
}
