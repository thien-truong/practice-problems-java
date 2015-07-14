package localhost.thien.vendingmachine;

public class Main {
    public static void main(String[] args) {
        ObjectRegistry objectRegistry = new ObjectRegistry();
        VendingMachineConsoleInterface consoleInterface = objectRegistry.consoleInterface();
        VendingMachineInventory vendingMachineInventory = new VendingMachineInventory();
        consoleInterface.purchaseMerchandise(vendingMachineInventory);
    }
}
