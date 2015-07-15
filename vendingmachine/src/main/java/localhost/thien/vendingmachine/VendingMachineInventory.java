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

    public void forEach(BiConsumer<String, VendingMachineMerchandiseStock> action) {
        inventory.forEach(action);
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
                throw new IllegalArgumentException("Can't add different type of merchandise to same spot");
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

}
