package localhost.thien.vendingmachine;

public class Main {
    public static void main(String[] args) {
        ObjectRegistry objectRegistry = new ObjectRegistry();
        VendingMachineConsoleInterface consoleInterface = objectRegistry.consoleInterface();
        VendingMachineInventory vendingMachineInventory = new VendingMachineInventory();
        vendingMachineInventory.addMerchandise("A", 2).addMerchandise("B", 5).addMerchandise("C", 1);
        consoleInterface.purchaseMerchandise(vendingMachineInventory);
    }
}
