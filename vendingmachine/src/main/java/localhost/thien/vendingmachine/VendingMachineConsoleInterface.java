package localhost.thien.vendingmachine;

import java.util.Map;
import java.util.Scanner;

public class VendingMachineConsoleInterface {

    private Scanner keyboard;

    public VendingMachineConsoleInterface(Scanner keyboard) {

        this.keyboard = keyboard;
    }

    public void purchaseMerchandise(VendingMachineInventory vendingMachineInventory) {
        String merchandiseCode;
        double cash;
        final double maxCash = 5.0;

        System.out.print("Enter merchandise code: ");
        merchandiseCode = keyboard.nextLine();

        VendingMachineMerchandise selectedMerchandise = vendingMachineInventory.getInventory().entrySet().stream()
                                                        .filter(a -> a.getKey().getMerchandiseCode().equals(merchandiseCode))
                                                        .map(Map.Entry::getKey)
                                                        .findAny().orElse(null);

        if (selectedMerchandise != null) {
            double merchandisePrice = selectedMerchandise.getRetailPrice();
            String merchandiseName = selectedMerchandise.getMerchandiseName();

            System.out.print(String.format("This machine cannot accept more than $%.2f in cash.  Enter cash : ",
                                          maxCash));
            cash = keyboard.nextDouble();

            double additionalCash;

            while (cash < merchandisePrice) {
                additionalCash = merchandisePrice - cash;
                System.out.print(String.format("Your %s costs $%.2f.  Please enter at least $%.2f more: ",
                                              merchandiseName,
                                              merchandisePrice,
                                              additionalCash));
                cash += keyboard.nextDouble();
            }

            if (cash <= maxCash) {
                double change = cash - merchandisePrice;
                vendingMachineInventory.reduceMerchandise(merchandiseCode, 1);
                vendingMachineInventory.resetCashBalanceAfterPurchase(merchandisePrice);
                System.out.println(String.format("Enjoy your %s! And here is your change of $%.2f.",
                                                merchandiseName,
                                                change));
                System.out.println(vendingMachineInventory.getCashBalance());
            }
            else {
                System.out.println(String.format("This machine cannot accept more than $%.2f in cash.  Here is your $ %.2f back.",
                                                maxCash,
                                                cash));
            }

        } else {
            System.out.println("No such code in the system.");
        }
    }
}
