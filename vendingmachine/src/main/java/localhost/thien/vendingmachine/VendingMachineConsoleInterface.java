package localhost.thien.vendingmachine;

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
        final double maxCashAcceptedByMachine = 5.0;

        System.out.println(String.format("Please note that this machine cannot accept more than $%.2f in cash.",
                                        maxCashAcceptedByMachine));

        System.out.print("Enter XX to quit.  " +
                         "Otherwise enter the code that corresponds to the drink you wish to purchase: ");
        merchandiseCode = keyboard.next().toUpperCase();

        if (merchandiseCode.equals("XX")) {
            throw new FinishVendingException();
        }

        Optional<VendingMachineMerchandise> selectedMerchandise =
            vendingMachineInventory.getVendingMachineMerchandise(merchandiseCode);

        if (selectedMerchandise.isPresent()) {
            purchaseSpecifiedMerchandise(merchandiseCode, selectedMerchandise.get(), maxCashAcceptedByMachine);
        } else {
            System.out.println("No such code in the system.");
        }

        System.out.println();

    }

    private void purchaseSpecifiedMerchandise(
             String merchandiseCode,
             VendingMachineMerchandise selectedMerchandise,
             double maxCashAcceptedByMachine) {

        double merchandisePrice = selectedMerchandise.getRetailPrice();
        String merchandiseName = selectedMerchandise.getMerchandiseName();
        String cashPaidSoFarString;
        double cashPaidSoFar = 0.0;

        System.out.print(String.format("You selected a %s, which costs $%.2f. Enter cash $: ",
                                      merchandiseName,
                                      merchandisePrice));
        cashPaidSoFarString = keyboard.next();

        try {
            cashPaidSoFar = Double.parseDouble(cashPaidSoFarString);
        } catch (NumberFormatException ex) {
            System.out.println("Error: " + ex + ". Expecting a numeric value.");
        }

        while (cashPaidSoFar < merchandisePrice) {
            cashPaidSoFar = getMoreCash(merchandisePrice, selectedMerchandise.getMerchandiseName(), cashPaidSoFar);
        }

        if (cashPaidSoFar <= maxCashAcceptedByMachine) {
            dispenseMerchandise(merchandiseCode, selectedMerchandise, cashPaidSoFar);
        } else {
            System.out.println(String.format("This machine cannot accept more than $%.2f in cash.  " +
                                             "Here is your $ %.2f back.",
                                            maxCashAcceptedByMachine, cashPaidSoFar));
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

    private double getMoreCash(double merchandisePrice, String merchandiseName, double cashPaidSoFar) {
        double additionalCashNeeded = merchandisePrice - cashPaidSoFar;
        double additionalCashEntered = 0.0;

        System.out.print(String.format("Your %s costs $%.2f.  Please enter at least $%.2f more: ",
                                      merchandiseName, merchandisePrice, additionalCashNeeded));
        String additionalCashPaidString = keyboard.next();

        try {
            additionalCashEntered = Double.parseDouble(additionalCashPaidString);
        } catch (NumberFormatException ex){
            System.out.println("Error: " + ex + ". Expecting a numeric value.");
        }

        return cashPaidSoFar + additionalCashEntered;
    }

    public void displayVendingMachineInventory() {
        vendingMachineInventory.displayVendingMachineInventory();
    }

}
