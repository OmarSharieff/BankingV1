public class Account {
    private double balance;
    private final String accountNumber;
    private final AccountType accountType;
    // constructor
    public Account(String accountNumber, AccountType accountType) {
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.balance = 0.0;
    }

    // method to withdraw money
    public boolean withdraw(double amount) {
        // calculate new balance for respective accounts
        double newBalance = balance - amount;

        if (newBalance < accountType.getMinBalance()) {
            System.out.println("Withdrawal denied. Minimum balance for " + accountType + " account reached!");
            return false;
        }
        balance = newBalance; //update balance
        return true;
    }

    // method ot deposit money
    public boolean deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            return true;
        }
        System.out.println("Invalid deposit amount!");
        return false;
    }

    public void getOverview() {
        System.out.println("Account Number: " + accountNumber + ", Balance: " + balance + ", Type: " + accountType);
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public AccountType getAccountType() {return accountType;}
}
