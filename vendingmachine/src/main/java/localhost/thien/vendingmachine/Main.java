package localhost.thien.vendingmachine;

public class Main {
    public static void main(String[] args) {
        ObjectRegistry objectRegistry = new ObjectRegistry();

        VendingMachineConsoleInterface consoleInterface = objectRegistry.consoleInterface();
        VendingMachineInventory vendingMachineInventory = objectRegistry.vendingMachineInventory();
        vendingMachineInventory.addMerchandise("a", 2).addMerchandise("B", 5).addMerchandise("C", 1);

        while (true) {
            consoleInterface.displayVendingMachineInventory(vendingMachineInventory);
            consoleInterface.purchaseMerchandise(vendingMachineInventory);
        }

    }
}
