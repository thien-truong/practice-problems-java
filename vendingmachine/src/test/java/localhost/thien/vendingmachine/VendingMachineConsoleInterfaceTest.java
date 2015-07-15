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

        VendingMachineInventory vendingMachineInventory = new VendingMachineInventory();
        VendingMachineConsoleInterface consoleInterface = new VendingMachineConsoleInterface(fakeKeyboard, vendingMachineInventory);

        new Expectations() {{
            fakeKeyboard.next();
            returns("A", "1.00");
        }};

        vendingMachineInventory.addMerchandise("A", 3);
        vendingMachineInventory.addMerchandise("B", 5);
        Map<VendingMachineMerchandise, Integer> expectedInventory = new HashMap<>();
        expectedInventory.put(new VendingMachineMerchandise("A"), 2);
        expectedInventory.put(new VendingMachineMerchandise("B"), 5);
        consoleInterface.purchaseMerchandise();
        assertThat(vendingMachineInventory.getInventory(), equalTo(expectedInventory));
        assertThat(vendingMachineInventory.getCashBalance(), equalTo(30.75));

        new Verifications() {{
            fakePrintStream.print("Enter XX to quit.  Otherwise enter the code that corresponds to the drink you wish to purchase: ");
            fakePrintStream.println("Enjoy your Pepsi! And here is your change of $0.25.");
        }};
    }

}
