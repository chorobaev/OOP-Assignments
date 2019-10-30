public class NaturalNumber {
    private int value;

    public NaturalNumber(int value) {
        if (value <= 0) {
            throw new IllegalArgumentException("You must supply a natural number (positive integer)");
        }
        this.value = value;
    }

    public Classification getClassification() {
        if (sumOfFactors() == value) return Classification.PERFECT;
        else if (sumOfFactors() > value) return Classification.ABUNDANT;
        else return Classification.DEFICIENT;
    }

    private int sumOfFactors() {
        int sum = 0;
        for (int i = 1; i < value; i++) {
            if (value % i == 0) {
                sum += i;
            }
        }
        return sum;
    }
}
