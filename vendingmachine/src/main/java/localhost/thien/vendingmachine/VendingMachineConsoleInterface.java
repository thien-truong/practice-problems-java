package localhost.thien.vendingmachine;

import java.util.List;
import java.util.Scanner;

public class VendingMachineConsoleInterface {

    private Scanner keyboard;

    public VendingMachineConsoleInterface(Scanner keyboard) {

        this.keyboard = keyboard;
    }

    public void purchaseMerchandise(VendingMachineInventory vendingMachineInventory) {
        System.out.print("Enter merchandise code: ");
        final String merchandiseCode = keyboard.nextLine();
        System.out.print("Enter cash: ");
        Double cash = keyboard.nextDouble();
        List availableMerchandiseCodes = vendingMachineInventory.generateAvailableMerchandiseCodes();
        if (availableMerchandiseCodes.contains(merchandiseCode)) {
            vendingMachineInventory.reduceMerchandise(merchandiseCode, 1);
        } else {
            System.out.println("No such code in the system.");
        }
        System.out.println(String.format("Your drink is %s. Your change is %.2f.", merchandiseCode, cash));
    }
}
