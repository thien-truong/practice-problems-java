package localhost.thien.vendingmachine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class VendingMachineInventory {
    private Map<VendingMachineMerchandise, Integer> inventory = new HashMap<VendingMachineMerchandise, Integer>();
    private Double cashBalance = 30.0;

    public Map<VendingMachineMerchandise, Integer> getInventory() {
        return inventory;
    }

    public Double getCashBalance() {
        return cashBalance;
    }

    public void setCashBalance(Double cashBalance) {
        this.cashBalance = cashBalance;
    }

    public VendingMachineInventory addMerchandise(String merchandiseCode, int quantity) {

        VendingMachineMerchandise merchandise = new VendingMachineMerchandise(merchandiseCode);

        if (inventory.containsKey(merchandise)) {
            int newQuantity = inventory.get(merchandise) + quantity;
            inventory.put(merchandise, newQuantity);
        } else {
            inventory.put(merchandise, quantity);
        }

        // fluent pattern (so you could do .addMerchandise().addMerchandise();
        return this;
    }

    public VendingMachineInventory reduceMerchandise(String merchandiseCode, int quantity) {

        VendingMachineMerchandise merchandise = new VendingMachineMerchandise(merchandiseCode);

        if (inventory.containsKey(merchandise)) {
            int newQuantity = inventory.get(merchandise) - quantity;
            inventory.put(merchandise, newQuantity);
        }

        return this;
    }

    public List<String> generateAvailableMerchandiseCodes() {
        List<String> availableMerchandiseCodes = new ArrayList<>();
        inventory.forEach( (item, quantity) -> availableMerchandiseCodes.add(item.getMerchandiseCode()));
        return availableMerchandiseCodes;
    }

}
