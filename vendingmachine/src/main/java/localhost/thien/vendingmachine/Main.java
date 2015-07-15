package localhost.thien.vendingmachine;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);

        VendingMachineInventory vendingMachineInventory = new VendingMachineInventory();
        VendingMachineConsoleInterface consoleInterface = new VendingMachineConsoleInterface(keyboard, vendingMachineInventory);
        vendingMachineInventory
            .addMerchandise("a", new VendingMachineMerchandise("Pepsi", 2.75), 2)
            .addMerchandise("B", new VendingMachineMerchandise("Coca Cola", 0.75), 5)
            .addMerchandise("C", new VendingMachineMerchandise("Snicker", 2.00), 1);

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
