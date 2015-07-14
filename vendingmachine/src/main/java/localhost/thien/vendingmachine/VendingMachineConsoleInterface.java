package localhost.thien.vendingmachine;

import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

public class VendingMachineConsoleInterface {

    //create a new class, have it extends runtimeException, name it FinishVendingException.
    //

    private Scanner keyboard;

    public VendingMachineConsoleInterface(Scanner keyboard) {

        this.keyboard = keyboard;
    }

    public void purchaseMerchandise(VendingMachineInventory vendingMachineInventory) {

        String merchandiseCode;
        double cash;
        final double maxCash = 5.0;

        System.out.print("Enter the code corresponds to the drink you wish to purchase: ");
        merchandiseCode = keyboard.next().toUpperCase();

        Optional<VendingMachineMerchandise> selectedMerchandise = vendingMachineInventory.getInventory().entrySet().stream()
                                                        .filter(a -> a.getKey().getMerchandiseCode().equals(merchandiseCode))
                                                        .map(Map.Entry::getKey)
                                                        .findAny();

        if (selectedMerchandise.isPresent()) {

            double merchandisePrice = selectedMerchandise.get().getRetailPrice();
            String merchandiseName = selectedMerchandise.get().getMerchandiseName();
            double additionalCash;

            System.out.println(String.format("Please note that this machine cannot accept more than $%.2f in cash.",
                                            maxCash));
            System.out.print("Enter cash $: ");
            cash = keyboard.nextDouble();

            while (cash < merchandisePrice) {
                additionalCash = merchandisePrice - cash;
                System.out.print(String.format("Your %s costs $%.2f.  Please enter at least $%.2f more: ",
                                              merchandiseName, merchandisePrice, additionalCash));
                cash += keyboard.nextDouble();
            }

            if (cash <= maxCash) {
                double change = cash - merchandisePrice;

                vendingMachineInventory.reduceMerchandise(merchandiseCode, 1);

                if (vendingMachineInventory.getInventory().get(selectedMerchandise.get()) == 0) {
                    vendingMachineInventory.removeMerchandise(merchandiseCode);
                }

                vendingMachineInventory.resetCashBalanceAfterPurchase(merchandisePrice);
                System.out.println(String.format("Enjoy your %s! And here is your change of $%.2f.",
                                                merchandiseName, change));
            } else {
                System.out.println(String.format("This machine cannot accept more than $%.2f in cash.  " +
                                                 "Here is your $ %.2f back.",
                                                maxCash, cash));
            }

        } else {
            System.out.println("No such code in the system.");
        }

        System.out.println();

    }

    public void displayVendingMachineInventory(VendingMachineInventory vendingMachineInventory) {
        System.out.println("Code        Name                Price       Availability");
        vendingMachineInventory.getInventory()
        .forEach((merchandise, quantity) ->
                 System.out.printf("%-12s%-20s%-12.2f%-12d\n",
                                  merchandise.getMerchandiseCode(),
                                  merchandise.getMerchandiseName(),
                                  merchandise.getRetailPrice(),
                                  quantity));
    }
}
