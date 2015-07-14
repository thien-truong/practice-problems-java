package localhost.thien.vendingmachine;

import java.util.Scanner;

public class ObjectRegistry {
    public Scanner scanner() {
        return new Scanner(System.in);
    }

    public VendingMachineInventory vendingMachineInventory() {return new VendingMachineInventory();}

    public VendingMachineConsoleInterface consoleInterface() {
        return new VendingMachineConsoleInterface(scanner());
    }
}
