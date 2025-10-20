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

        // keep creating accounts till client stops
        while (true) {
            System.out.print("Do you want to add an account? (Y/n): ");
            char choice = scanner.next().charAt(0);
            if (choice == 'Y' || choice == 'y' || choice == ' ') {
                String randomAccountNumber = randomAccountNumberGenerator();

                System.out.println("Select account type: ");
                System.out.println("1. Green (Standard): No overdraft allowed");
                System.out.println("2. Black (Premium): Up to -4000");
                System.out.println("3. Gold (Unlimited): Unlimited overdraft");
                System.out.print("Enter choice (1/2/3): ");
                int typeChoice = scanner.nextInt();

                AccountType selectedType;

                switch (typeChoice) {
                    case 1:
                        selectedType = AccountType.GREEN;
                        break;
                    case 2:
                        selectedType = AccountType.BLACK;
                        break;
                    case 3:
                        selectedType = AccountType.GOLD;
                        break;
                    default:
                        selectedType = AccountType.GREEN;
                        break;
                }

                bank.addAccount(randomAccountNumber, selectedType);
                System.out.printf("Account %s of type %s has been successfully added for client.%n",
                        randomAccountNumber, selectedType);
            } else {
                break;
            }
        }

        List<Account> accounts = bank.getClientAccountMap().get(bank.getLoggedInClient());

        // if there are no accounts created then we simply exit the program
        if (accounts.isEmpty()) {
            System.out.println("There were no accounts created!");
            System.exit(1);
        }
        System.out.println("\nSelect your account:");
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

        //consume the newline leftover
        scanner.nextLine();


        // Show all accounts for the logged-in client
        bank.showAccounts();

        // Code to transfer amount
        System.out.print("Want to make a transfer? (Y/n): ");
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
                System.out.printf("Available balance after transfer: %.2f\n", chosenAccount.getBalance());
            } else {
                System.out.println("Amount could not be transferred!");
            };
        }

        bank.showAccounts();

        System.out.print("Do you want to remove an account: (Y/n): ");
        char removeAccUserInput = scanner.next().charAt(0);
        if(removeAccUserInput == 'Y' || removeAccUserInput == 'y' || removeAccUserInput == ' ') {
            System.out.println("\nSelect your account:");
            for (int i = 0; i < accounts.size(); i++) {
                System.out.println((i + 1) + ". " + accounts.get(i).getAccountNumber());
            }
            System.out.print("Enter the corresponding number of your account (Ex: 1, 2, 3...): ");
            int accountPosition1 = scanner.nextInt();
            Account chosenAccount1 = accounts.get(accountPosition - 1);
            bank.removeAccount(chosenAccount1.getAccountNumber());
            System.out.println("Account has been successfully removed");
            bank.showAccounts();
        } else {
            System.out.println("No account removed");
            System.exit(0);
        }

        //method for removing client
        bank.removeClient();



        // Logout
        bank.logout();

        scanner.close();
    }
}
