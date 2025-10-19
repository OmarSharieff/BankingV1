public class Account {
    private double balance;
    private String accountNumber;

    // constructor
    public Account(String accountNumber, double initialBalance) {
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
    }

    // method to withdraw money
    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            return true;
        }
        System.out.println("Invalid withdraw amount!");
        return false;
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
        System.out.println("Account Number: " + accountNumber + ", Balance: " + balance);
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }
}
