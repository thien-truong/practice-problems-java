package localhost.thien.vendingmachine;

import java.util.HashMap;
import java.util.Map;

public class VendingMachineInventory {

    private Map<VendingMachineMerchandise, Integer> inventory = new HashMap<VendingMachineMerchandise, Integer>();
    private Double cashBalance = 30.0;

    public Map<VendingMachineMerchandise, Integer> getInventory() {
        return inventory;
    }

    public Double getCashBalance() {
        return cashBalance;
    }

    public void resetCashBalanceAfterPurchase(double priceOfMerchandiseSold) {
        this.cashBalance += priceOfMerchandiseSold;
    }

    public VendingMachineInventory addMerchandise(String merchandiseCode, int quantity) {

        try {
            VendingMachineMerchandise merchandise = new VendingMachineMerchandise(merchandiseCode.toUpperCase());

            if (inventory.containsKey(merchandise)) {
                int newQuantity = inventory.get(merchandise) + quantity;
                inventory.put(merchandise, newQuantity);
            } else {
                inventory.put(merchandise, quantity);
            }
        } catch(IllegalArgumentException ex) {
                System.out.println("Cannot add merchandise with code "
                                   + merchandiseCode + " to Inventory. " + ex);
            }

        return this;
    }

    public VendingMachineInventory reduceMerchandise(String merchandiseCode, int quantity) {

        VendingMachineMerchandise merchandise = new VendingMachineMerchandise(merchandiseCode.toUpperCase());

        if (inventory.containsKey(merchandise)) {
            int newQuantity = inventory.get(merchandise) - quantity;
            inventory.put(merchandise, newQuantity);
        }

        return this;
    }

    public VendingMachineInventory removeMerchandise(String merchandiseCode) {

        inventory.remove(new VendingMachineMerchandise(merchandiseCode.toUpperCase()));

        return this;
    }

}
