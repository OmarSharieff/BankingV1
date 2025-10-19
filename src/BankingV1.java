public class BankingV1 {
    public static void main(String[] args) {
        Bank bank = new Bank();

        // Add clients
        bank.addClient(101, 1234);
        bank.addClient(102, 5678);

        // Login as a client
        bank.login(101, 1234);

        // Add accounts
        bank.addAccount("ACC1001");
        bank.addAccount("ACC1002");

        // Deposit money in client 101

        // Show accounts
        bank.showAccounts();

        // Logout
        bank.logout();
    }
}
