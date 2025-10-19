public class Client {
    private int ID;
    private int PIN;

    // constructor
    public Client(int ID, int PIN) {
        this.ID = ID;
        this.PIN = PIN;
    }

    // method to validate PIN
    public boolean pinMatch(int inputPIN) {
        return this.PIN == inputPIN;
    }

    // getter method for ID
    public int getID() {
        return ID;
    }
}
