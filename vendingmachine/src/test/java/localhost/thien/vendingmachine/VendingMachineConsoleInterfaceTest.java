package localhost.thien.vendingmachine;

import mockit.Expectations;
import mockit.Mocked;
import mockit.Verifications;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class VendingMachineConsoleInterfaceTest {

    @Mocked
    Scanner fakeKeyboard;

    @Mocked
    PrintStream fakePrintStream;

    PrintStream originalPrintStream;

    @Before
    public void setUp() {
        originalPrintStream = System.out;
        System.setOut(fakePrintStream);
    }

    @After
    public void tearDown() {
        System.setOut(originalPrintStream);
    }

    @Test
    public void testPurchasesMerchandise() {

        VendingMachineConsoleInterface consoleInterface = new VendingMachineConsoleInterface(fakeKeyboard);

        new Expectations() {{
            fakeKeyboard.nextLine();
            result = "A";
            fakeKeyboard.nextDouble();
            result = 1.00;
        }};

        VendingMachineInventory vendingMachineInventory = new VendingMachineInventory();
        vendingMachineInventory.addMerchandise("A", 3);
        vendingMachineInventory.addMerchandise("B", 5);
        Map<VendingMachineMerchandise, Integer> expectedInventory = new HashMap<>();
        expectedInventory.put(new VendingMachineMerchandise("A"), 2);
        expectedInventory.put(new VendingMachineMerchandise("B"), 5);
        consoleInterface.purchaseMerchandise(vendingMachineInventory);
        assertThat(vendingMachineInventory.getInventory(), equalTo(expectedInventory));
        assertThat(vendingMachineInventory.getCashBalance(), equalTo(30.75));

        new Verifications() {{
            fakePrintStream.print("Enter merchandise code: ");
            fakePrintStream.println("Enjoy your Pepsi! And here is your change of $0.25.");
        }};
    }

}
