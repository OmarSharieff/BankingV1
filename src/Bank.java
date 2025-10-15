import java.util.HashMap;
import java.util.List;

class Bank {

    Client loggedInClient = new Client();
    Account loggedInAccount = new Account();
    // key-value paring to keep track of clientId and list of accounts that a client has
    HashMap<Integer,List<Account>> clientAccountMap = new HashMap<>();

    // Each Bank must have atleast one client and one account
    Bank(Client loggedInClient, Account loggedInAccount) {
        this.loggedInClient = loggedInClient;
        this.loggedInAccount = loggedInAccount;
    }

    //all methods of Bank class

    // checks credentials and logs them in
    boolean login(int clientID, String PIN) {
        if ((clientID == this.loggedInClient.getClientId()) && (PIN.equals(this.loggedInClient.getPIN()))){
            return true;
        } else {
            return false;
        }
    }

    // logout method logs out those users which are already logged in
    boolean logout() {
        if (this.login(this.loggedInClient.getClientId(),this.loggedInClient.getPIN())) {
            System.out.println("You have logged out");
            return true;
        } else {
            System.out.println("Invalid request");
            return false;
        }
    }

    //method to add account
    boolean addAccount(String acNumber) {
        // write code here
        return true;
    }



}
