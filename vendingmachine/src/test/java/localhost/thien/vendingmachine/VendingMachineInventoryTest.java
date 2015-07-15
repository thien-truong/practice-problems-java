package localhost.thien.vendingmachine;

import mockit.Expectations;
import mockit.Mocked;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class VendingMachineInventoryTest {

    @Test
    public void testAddsMerchandiseOfCodeAToEmptyInventory() {
        VendingMachineMerchandise merchandise = new VendingMachineMerchandise("Pepsi", 2.75);
        VendingMachineInventory vendingMachineInventory = new VendingMachineInventory();
        vendingMachineInventory.addMerchandise("A", merchandise, 2);
        Map<String, VendingMachineMerchandiseStock> expectedInventory = new HashMap<>();
        expectedInventory.put("A", new VendingMachineMerchandiseStock(merchandise, 2));
        assertThat(vendingMachineInventory.getInventory(), equalTo(expectedInventory));
    }

    @Test
    public void testAddsMerchandiseOfCodeAToNoneEmptyInventory() {
        VendingMachineMerchandise pepsi = new VendingMachineMerchandise("Pepsi", 2.75);
        VendingMachineMerchandise coke = new VendingMachineMerchandise("Coke", .75);
        VendingMachineInventory vendingMachineInventory = new VendingMachineInventory();
        vendingMachineInventory.addMerchandise("A", pepsi, 2).addMerchandise("C", coke, 3).addMerchandise("A", pepsi, 5);
        Map<String, VendingMachineMerchandiseStock> expectedInventory = new HashMap<>();
        expectedInventory.put("A", new VendingMachineMerchandiseStock(pepsi, 7));
        expectedInventory.put("C", new VendingMachineMerchandiseStock(coke, 3));
        assertThat(vendingMachineInventory.getInventory(), equalTo(expectedInventory));
    }

    @Test
    public void testReducesMerchandiseOfCodeA() {
        VendingMachineMerchandise pepsi = new VendingMachineMerchandise("Pepsi", 2.75);
        VendingMachineMerchandise coke = new VendingMachineMerchandise("Coke", .75);
        VendingMachineInventory vendingMachineInventory = new VendingMachineInventory();
        vendingMachineInventory.addMerchandise("A", pepsi, 2).addMerchandise("C", coke, 3).reduceMerchandise("A", 1);
        Map<String, VendingMachineMerchandiseStock> expectedInventory = new HashMap<>();
        expectedInventory.put("A", new VendingMachineMerchandiseStock(pepsi, 1));
        expectedInventory.put("C", new VendingMachineMerchandiseStock(coke, 3));
        assertThat(vendingMachineInventory.getInventory(), equalTo(expectedInventory));
    }

    @Test
    public void testRemovesMerchandiseOfCodeA() {
        VendingMachineMerchandise pepsi = new VendingMachineMerchandise("Pepsi", 2.75);
        VendingMachineMerchandise coke = new VendingMachineMerchandise("Coke", .75);
        VendingMachineInventory vendingMachineInventory = new VendingMachineInventory();
        vendingMachineInventory.addMerchandise("A", pepsi, 2).addMerchandise("C", coke, 3).removeMerchandise("A");
        Map<String, VendingMachineMerchandiseStock> expectedInventory = new HashMap<>();
        expectedInventory.put("C", new VendingMachineMerchandiseStock(coke, 3));
        assertThat(vendingMachineInventory.getInventory(), equalTo(expectedInventory));
    }

}
