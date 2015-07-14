package localhost.thien.vendingmachine;

import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

public class VendingMachineConsoleInterface {

    private Scanner keyboard;

    public VendingMachineConsoleInterface(Scanner keyboard) {

        this.keyboard = keyboard;
    }

    public void purchaseMerchandise(VendingMachineInventory vendingMachineInventory) {

        String merchandiseCode;
        String cashString;
        final double maxCash = 5.0;

        System.out.print("Enter XX to quit.  " +
                         "Else enter the code corresponds to the drink you wish to purchase: ");
        merchandiseCode = keyboard.next().toUpperCase();

        switch (merchandiseCode) {
             case "XX":
                 throw new FinishVendingException();
             default:
                 Optional<VendingMachineMerchandise> selectedMerchandise =
                    getVendingMachineMerchandise(vendingMachineInventory, merchandiseCode);

                 if (selectedMerchandise.isPresent()) {

                     double merchandisePrice = selectedMerchandise.get().getRetailPrice();
                     String merchandiseName = selectedMerchandise.get().getMerchandiseName();

                     System.out.println(String.format("Please note that this machine cannot accept more than $%.2f in cash.",
                                                     maxCash));
                     System.out.print("Enter cash $: ");
                     cashString = keyboard.next();
                     try {
                         double cash = Double.parseDouble(cashString);

                         while (cash < merchandisePrice) {
                             cash = getMoreCash(merchandisePrice, merchandiseName, cash);
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

                     } catch (NumberFormatException ex) {
                         System.out.println("Error: " + ex + ". Expecting a numeric value.");
                     }

                 } else {
                     System.out.println("No such code in the system.");
                 }

                 System.out.println();
             }
        }

    private double getMoreCash(double merchandisePrice, String merchandiseName, double cash) {
        double additionalCashNeeded;
        String cashString;

        additionalCashNeeded = merchandisePrice - cash;
        System.out.print(String.format("Your %s costs $%.2f.  Please enter at least $%.2f more: ",
                                      merchandiseName, merchandisePrice, additionalCashNeeded));
        cashString = keyboard.next();

        try {
            double additionalCashEntered = Double.parseDouble(cashString);
            cash += additionalCashEntered;
        } catch (NumberFormatException ex){
            System.out.println("Error: " + ex + ". Expecting a numeric value.");
        }

        return cash;
    }

    private Optional<VendingMachineMerchandise> getVendingMachineMerchandise(
        VendingMachineInventory vendingMachineInventory, String merchandiseCode) {

        return vendingMachineInventory.getInventory().entrySet().stream()
        .filter(a -> a.getKey().getMerchandiseCode().equals(merchandiseCode))
        .map(Map.Entry::getKey)
        .findAny();

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
