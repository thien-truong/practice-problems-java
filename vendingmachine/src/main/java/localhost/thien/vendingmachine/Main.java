package localhost.thien.vendingmachine;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);

        VendingMachineInventory vendingMachineInventory = new VendingMachineInventory();
        VendingMachineConsoleInterface consoleInterface = new VendingMachineConsoleInterface(keyboard, vendingMachineInventory);
        vendingMachineInventory.addMerchandise("a", 2).addMerchandise("B", 5).addMerchandise("C", 1);

        Boolean finishedVending = false;

        while (!finishedVending) {
            consoleInterface.displayVendingMachineInventory();

            try {
                consoleInterface.purchaseMerchandise();
            } catch (FinishVendingException ex) {
                finishedVending = true;
            }

        }

    }
}
