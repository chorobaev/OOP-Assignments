import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.math.BigInteger;

import static org.junit.Assert.assertEquals;

public class GrainsTest {

    private static String wrongSquareMessage = "square must be between 1 and 64";

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private Grains grains;

    @Before
    public void setup() {
        grains = new Grains();
    }


    @Test
    public void countAtSquare1() {
        BigInteger result = grains.computeNumberOfGrainsOnSquare(1);
        assertEquals(new BigInteger("1"), result);
    }

    @Test
    public void countAtSquare2() {
        BigInteger result = grains.computeNumberOfGrainsOnSquare(2);
        assertEquals(new BigInteger("2"), result);
    }

    @Test
    public void countAtSquare3() {
        BigInteger result = grains.computeNumberOfGrainsOnSquare(3);
        assertEquals(new BigInteger("4"), result);
    }

    @Test
    public void countAtSquare4() {
        BigInteger result = grains.computeNumberOfGrainsOnSquare(4);
        assertEquals(new BigInteger("8"), result);
    }

    @Test
    public void countAtSquare16() {
        BigInteger result = grains.computeNumberOfGrainsOnSquare(16);
        assertEquals(new BigInteger("32768"), result);
    }

    @Test
    public void countAtSquare32() {
        BigInteger result = grains.computeNumberOfGrainsOnSquare(32);
        assertEquals(new BigInteger("2147483648"), result);
    }

    @Test
    public void countAtSquare64() {
        BigInteger result = grains.computeNumberOfGrainsOnSquare(64);
        assertEquals(new BigInteger("9223372036854775808"), result);
    }

    @Test
    public void errorOnNullBoardSize() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(wrongSquareMessage);
        grains.computeNumberOfGrainsOnSquare(0);
    }

    @Test
    public void errorOnNegativeBoardSize() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(wrongSquareMessage);
        grains.computeNumberOfGrainsOnSquare(-1);
    }

    @Test
    public void errorOnExcessiveBoardSize() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(wrongSquareMessage);
        grains.computeNumberOfGrainsOnSquare(65);
    }

    @Test
    public void totalNumberOfGrainsOnABoard() {
        BigInteger total = grains.computeTotalNumberOfGrainsOnBoard();
        assertEquals(new BigInteger("18446744073709551615"), total);
    }

}