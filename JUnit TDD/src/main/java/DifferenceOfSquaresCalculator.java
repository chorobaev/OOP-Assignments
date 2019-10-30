public class DifferenceOfSquaresCalculator {

    public int computeSquareOfSumTo(int n) {
        int sum = (n + 1) * n / 2;
        return (int) Math.pow(sum, 2);
    }

    public int computeSumOfSquaresTo(int n) {
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            sum += (int) Math.pow(i, 2);
        }
        return sum;
    }

    public int computeDifferenceOfSquares(int n) {
        return computeSquareOfSumTo(n) - computeSumOfSquaresTo(n);
    }
}
