import java.math.BigInteger;

public class Grains {

    public BigInteger computeNumberOfGrainsOnSquare(int s) {
        if (s < 1 || s > 64) throw new IllegalArgumentException("square must be between 1 and 64");
        return new BigInteger("2").pow(s-1);
    }

    public BigInteger computeTotalNumberOfGrainsOnBoard() {
        BigInteger bi = new BigInteger("0");
        for (int i = 0; i < 64; i++) {
            bi = bi.add(new BigInteger("2").pow(i));
        }
        return bi;
    }
}
