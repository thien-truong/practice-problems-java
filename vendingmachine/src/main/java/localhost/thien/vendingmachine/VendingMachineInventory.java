package localhost.thien.vendingmachine;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiConsumer;

public class VendingMachineInventory {

    private Map<VendingMachineMerchandise, Integer> inventory = new HashMap<VendingMachineMerchandise, Integer>();
    private Double cashBalance = 30.0;

    public Map<VendingMachineMerchandise, Integer> getInventory() {
        return inventory;
    }

    public Double getCashBalance() {
        return cashBalance;
    }

    public void forEach(BiConsumer<VendingMachineMerchandise, Integer> action) {
        inventory.forEach(action);
    }

    public void resetCashBalanceAfterPurchase(double priceOfMerchandiseSold) {
        this.cashBalance += priceOfMerchandiseSold;
    }

    public Optional<VendingMachineMerchandise> getVendingMachineMerchandise(String merchandiseCode) {

        return inventory.entrySet().stream()
               .filter(a -> a.getKey().getMerchandiseCode().equals(merchandiseCode))
               .map(Map.Entry::getKey)
               .findAny();

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
