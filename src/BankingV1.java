import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class BankingV1 {

    private static String randomAccountNumberGenerator() {
        Random random = new Random();
        return "ACC" + Math.abs(random.nextInt(10000)); // Avoid negative numbers
    }

    public static void main(String[] args) {
        Bank bank = new Bank();
        Scanner scanner = new Scanner(System.in);

        // Add clients
        bank.addClient(101, 1234);
        bank.addClient(102, 5678);

        // Login as a client
        System.out.print("Please enter your ID: ");
        int loginID = scanner.nextInt();
        System.out.print("Please enter your PIN to login: ");
        int loginPIN = scanner.nextInt();
        scanner.nextLine(); // consume leftover newline

        if (!bank.login(loginID, loginPIN)) {
            System.out.println("Login failed. Exiting...");
            return;
        }

        while (true) {
            System.out.print("Do you want to add an account? (Y/n): ");
            String choice = scanner.nextLine();
            if (choice.equalsIgnoreCase("y")) {
                String randomAccountNumber = randomAccountNumberGenerator();
                bank.addAccount(randomAccountNumber);
                System.out.printf("Account %s has been successfully added for client.%n", randomAccountNumber);
            } else {
                break;
            }
        }

        System.out.println("\nSelect your account:");
        List<Account> accounts = bank.getClientAccountMap().get(bank.getLoggedInClient());
        for (int i = 0; i < accounts.size(); i++) {
            System.out.println((i + 1) + ". " + accounts.get(i).getAccountNumber());
        }

        System.out.print("Enter the corresponding number of your account (Ex: 1, 2, 3...): ");
        int accountPosition = scanner.nextInt();
        Account chosenAccount = accounts.get(accountPosition - 1);

        System.out.print("Enter amount to deposit: ");
        double amount = scanner.nextDouble();
        chosenAccount.deposit(amount);
        System.out.printf("Deposit of %.2f is successful. Current balance: %.2f%n",
                amount, chosenAccount.getBalance());



        // Show all accounts for the logged-in client
        bank.showAccounts();

        // Code to transfer amount
        System.out.println("Want to make a transfer? (Y/n)");
        String transferPrompt = scanner.nextLine();
        if (transferPrompt.equalsIgnoreCase("y")) {
            System.out.println("Please enter the account number of recipient: ");
            String recipientAccNum = scanner.nextLine();

            System.out.println("How much would you like to transfer? (Enter Amount):");
            double transferAmount = scanner.nextDouble();

            // Getting recipient account by utilizing the helper method in bank class
            Account recepientAccount = bank.findAccountByNumber(bank.getLoggedInClient(),recipientAccNum);
            if (bank.transfer(chosenAccount,recepientAccount,transferAmount)) {
                System.out.println("Amount has been transferred successfully!");
                System.out.printf("Available balance after transfer: %f", chosenAccount.getBalance());
            } else {
                System.out.println("Amount could not be transferred!");
            };
        }

        // Logout
        bank.logout();

        scanner.close();
    }
}
