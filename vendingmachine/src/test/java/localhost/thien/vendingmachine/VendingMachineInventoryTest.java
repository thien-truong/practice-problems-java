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
        VendingMachineInventory vendingMachineInventory = new VendingMachineInventory();
        vendingMachineInventory.addMerchandise("A", 2);
        Map<VendingMachineMerchandise, Integer> expectedInventory = new HashMap<>();
        expectedInventory.put(new VendingMachineMerchandise("A"), 2);
        assertThat(vendingMachineInventory.getInventory(), equalTo(expectedInventory));
    }

    @Test
    public void testAddsMerchandiseOfCodeAToNoneEmptyInventory() {
        VendingMachineInventory vendingMachineInventory = new VendingMachineInventory();
        vendingMachineInventory.addMerchandise("A", 2).addMerchandise("C", 3).addMerchandise("A", 5);
        Map<VendingMachineMerchandise, Integer> expectedInventory = new HashMap<>();
        expectedInventory.put(new VendingMachineMerchandise("A"), 7);
        expectedInventory.put(new VendingMachineMerchandise("C"), 3);
        assertThat(vendingMachineInventory.getInventory(), equalTo(expectedInventory));
    }

    @Test
    public void testReducesMerchandiseOfCodeA() {
        VendingMachineInventory vendingMachineInventory = new VendingMachineInventory();
        vendingMachineInventory.addMerchandise("A", 2);
        vendingMachineInventory.addMerchandise("C", 3);
        vendingMachineInventory.reduceMerchandise("A", 1);
        Map<VendingMachineMerchandise, Integer> expectedInventory = new HashMap<>();
        expectedInventory.put(new VendingMachineMerchandise("A"), 1);
        expectedInventory.put(new VendingMachineMerchandise("C"), 3);
        assertThat(vendingMachineInventory.getInventory(), equalTo(expectedInventory));
    }

}
