import java.util.Random;

public class Client {
    // nextId is shared by all the clients objects of class Client
    public String clientName;
    private static int nextId = 1;
    final int clientId;
    private String PIN;

    // If client object is instantiated without PIN and clientName argument then by default PIN is set to 0000
    Client() {
        // using post increment so that first client gets clientId = 1
        this.clientId = nextId++;
        this.PIN = "0000";
        this.clientName = "Client " + this.clientId;
    }

    // Overloading constructor if client object is instantiated with a PIN
    Client(String PIN, String clientName) {
        this.clientId = nextId++;
        this.PIN = PIN;
        this.clientName = clientName;
    }


    // getter for clientId
    int getClientId() {
        return clientId;
    }

    // getter for PIN
    String getPIN() {
        return PIN;
    }

    boolean pinMatch(String enteredPIN) {
        //logic that checks if the user entered pin is correct
        return this.PIN.equals(enteredPIN);
    }

}
