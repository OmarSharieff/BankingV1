public enum AccountType {
    GREEN(0),          // cannot go below 0
    BLACK(-4000),      // can go up to -4000
    GOLD(Double.NEGATIVE_INFINITY); // no limit

    private final double minBalance;

    AccountType(double minBalance) {
        this.minBalance = minBalance;
    }

    public double getMinBalance() {
        return minBalance;
    }
}
