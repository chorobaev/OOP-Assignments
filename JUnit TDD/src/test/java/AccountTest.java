import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class AccountTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;
    private Account account;
    private int accountNumber = 1;


    @Before
    public void setup() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));

        double balance = 0;
        account = new Account(accountNumber, balance);
    }

    @Test
    public void testAccountNumber() {
        assertEquals(accountNumber, account.getAccountNumber());
    }

    @Test
    public void testCredit() {
        double amount = 10;
        account.setBalance(0);
        account.credit(amount);
        assertEquals(10, account.getBalance(), 0.01);
    }

    @Test
    public void testDebit() {
        double amount = 10;
        account.setBalance(20);
        account.debit(amount);
        assertEquals(10, account.getBalance(), 0.01);
    }

    @Test
    public void testDebitNonSufficient() {
        double amount = 20;
        account.setBalance(10);
        account.debit(amount);
        assertEquals("Amount withdrawn exceeds the current balance!\n", outContent.toString());
    }

    @Test
    public void testPrint() {
        double amount = 88.8888;
        double actualAmount = ((int) (amount * 100)) / 100.0;
        String actual = "A/C no: 1 Balance=$" + actualAmount;
        account.setBalance(amount);
        account.print();
        assertEquals(actual, outContent.toString());
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }
}
