package localhost.thien.vendingmachine;

public class Main {
    public static void main(String[] args) {
        ObjectRegistry objectRegistry = new ObjectRegistry();

        VendingMachineConsoleInterface consoleInterface = objectRegistry.consoleInterface();
        VendingMachineInventory vendingMachineInventory = objectRegistry.vendingMachineInventory();
        vendingMachineInventory.addMerchandise("a", 2).addMerchandise("B", 5).addMerchandise("C", 1);

        Boolean finishVending = false;

        while (!finishVending) {
            consoleInterface.displayVendingMachineInventory(vendingMachineInventory);
            try {
                consoleInterface.purchaseMerchandise(vendingMachineInventory);
            } catch(FinishVendingException ex){finishVending = true;}
        }

    }
}
