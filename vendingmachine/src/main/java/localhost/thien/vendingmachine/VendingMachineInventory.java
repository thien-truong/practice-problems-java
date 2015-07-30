package localhost.thien.vendingmachine;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiConsumer;

public class VendingMachineInventory {

    private Map<String, VendingMachineMerchandiseStock> inventory = new HashMap<>();
    private Double cashBalance = 30.0;

    public Map<String, VendingMachineMerchandiseStock> getInventory() {
        return inventory;
    }

    public Double getCashBalance() {
        return cashBalance;
    }

    public void resetCashBalanceAfterPurchase(double priceOfMerchandiseSold) {
        this.cashBalance += priceOfMerchandiseSold;
    }

    public Optional<VendingMachineMerchandise> getVendingMachineMerchandise(String merchandiseCode) {

        if (inventory.containsKey(merchandiseCode)) {
            VendingMachineMerchandiseStock merchandiseStock = inventory.get(merchandiseCode);
            return Optional.of(merchandiseStock.getMerchandise());
        }

        return Optional.empty();
    }

    public VendingMachineInventory addMerchandise(String merchandiseCode, VendingMachineMerchandise merchandise, int quantity) {

        String upperCasedMerchandiseCode = merchandiseCode.toUpperCase();
        if (inventory.containsKey(upperCasedMerchandiseCode)) {
            VendingMachineMerchandiseStock merchandiseStock = inventory.get(upperCasedMerchandiseCode);

            if (merchandiseStock.getMerchandise() != merchandise) {
                throw new IllegalArgumentException("Either merchandise name or retail price doesn't match what's " +
                                                   "already in Inventory. Cannot add.");
            }

            merchandiseStock.addQuantity(quantity);
        } else {
            VendingMachineMerchandiseStock merchandiseStock = new VendingMachineMerchandiseStock(merchandise, quantity);
            inventory.put(upperCasedMerchandiseCode, merchandiseStock);
        }

        return this;
    }

    public VendingMachineInventory reduceMerchandise(String merchandiseCode, int quantity) {

        String upperCasedMerchandiseCode = merchandiseCode.toUpperCase();

        if (inventory.containsKey(upperCasedMerchandiseCode)) {
            VendingMachineMerchandiseStock merchandiseStock = inventory.get(upperCasedMerchandiseCode);

            if (merchandiseStock.getQuantity() < quantity) {
                throw new NegativeQuantityException(String.format("There are only %d items of merchandise code %s available. " +
                                                                  "Reducing %d will result in negative inventory.",
                                                                 merchandiseStock.getQuantity(),
                                                                 upperCasedMerchandiseCode, quantity));
            }

            merchandiseStock.reduceQuantity(quantity);

            if (merchandiseStock.getQuantity() == 0) {
                removeMerchandise(upperCasedMerchandiseCode);
            }

        }

        return this;
    }

    public VendingMachineInventory removeMerchandise(String merchandiseCode) {

        inventory.remove(merchandiseCode.toUpperCase());

        return this;
    }

    public void displayVendingMachineInventory() {
        System.out.println("Code        Name                Price       Availability");

        inventory
        .forEach((merchandiseCode, merchandiseStock) ->
                 System.out.printf("%-12s%-20s%-12.2f%-12d\n",
                                  merchandiseCode,
                                  merchandiseStock.getMerchandise().getMerchandiseName(),
                                  merchandiseStock.getMerchandise().getRetailPrice(),
                                  merchandiseStock.getQuantity()));
    }

}
