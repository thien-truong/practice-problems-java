package localhost.thien.vendingmachine;

import java.util.Scanner;

// in Main method, call consoleInterface() to create a VendingMachineConsoleInterface object.

public class ObjectRegistry {
    // creating scanner outside because it doesn't do what i want for testing purposes.  Dependency injection.
    public Scanner scanner() {
        return new Scanner(System.in);
    }

    public VendingMachineConsoleInterface consoleInterface() {
        return new VendingMachineConsoleInterface(scanner());
    }
}
