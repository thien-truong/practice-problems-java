package localhost.thien.vendingmachine;

import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

public class VendingMachineConsoleInterface {

    private Scanner keyboard;
    private VendingMachineInventory vendingMachineInventory;

    public VendingMachineConsoleInterface(Scanner keyboard, VendingMachineInventory vendingMachineInventory) {
        this.keyboard = keyboard;
        this.vendingMachineInventory = vendingMachineInventory;
    }

    public void purchaseMerchandise() {

        String merchandiseCode;

        System.out.print("Enter XX to quit.  " +
                         "Otherwise enter the code that corresponds to the drink you wish to purchase: ");
        merchandiseCode = keyboard.next().toUpperCase();

        if (merchandiseCode.equals("XX")) {
            throw new FinishVendingException();
        }

        Optional<VendingMachineMerchandise> selectedMerchandise =
            vendingMachineInventory.getVendingMachineMerchandise(merchandiseCode);

        if (selectedMerchandise.isPresent()) {
            purchaseSpecifiedMerchandise(merchandiseCode, selectedMerchandise.get());
        } else {
            System.out.println("No such code in the system.");
        }

        System.out.println();

    }

    private void purchaseSpecifiedMerchandise(
             String merchandiseCode,
             VendingMachineMerchandise selectedMerchandise) {

        String cashString;
        final double maxCash = 5.0;

        System.out.println(String.format("Please note that this machine cannot accept more than $%.2f in cash.",
                                        maxCash));
        System.out.print("Enter cash $: ");
        cashString = keyboard.next();
        try {
            double cash = Double.parseDouble(cashString);
            double merchandisePrice = selectedMerchandise.getRetailPrice();

            while (cash < merchandisePrice) {
                cash = getMoreCash(merchandisePrice, selectedMerchandise.getMerchandiseName(), cash);
            }

            if (cash <= maxCash) {
                dispenseMerchandise(merchandiseCode, selectedMerchandise, cash);
            } else {
                System.out.println(String.format("This machine cannot accept more than $%.2f in cash.  " +
                                                 "Here is your $ %.2f back.",
                                                maxCash, cash));
            }

        } catch (NumberFormatException ex) {
            System.out.println("Error: " + ex + ". Expecting a numeric value.");
        }
    }

    private void dispenseMerchandise(
            String merchandiseCode,
            VendingMachineMerchandise selectedMerchandise,
            double cash) {

        double merchandisePrice = selectedMerchandise.getRetailPrice();
        String merchandiseName = selectedMerchandise.getMerchandiseName();

        double change = cash - merchandisePrice;

        vendingMachineInventory.reduceMerchandise(merchandiseCode, 1);

        vendingMachineInventory.resetCashBalanceAfterPurchase(merchandisePrice);
        System.out.println(String.format("Enjoy your %s! And here is your change of $%.2f.",
                                         merchandiseName, change));
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

    public void displayVendingMachineInventory() {

        System.out.println("Code        Name                Price       Availability");

        vendingMachineInventory
            .forEach((merchandiseCode, merchandiseStock) ->
                     System.out.printf("%-12s%-20s%-12.2f%-12d\n",
                                      merchandiseCode,
                                      merchandiseStock.getMerchandise().getMerchandiseName(),
                                      merchandiseStock.getMerchandise().getRetailPrice(),
                                      merchandiseStock.getQuantity()));
    }

}
