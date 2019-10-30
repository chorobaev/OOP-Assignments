public class Hamming {
    private String first, second;

    public Hamming(String first, String second) {
        if (first.length() != second.length()) {
            throw new IllegalArgumentException("leftStrand and rightStrand must be of equal length.");
        }
        this.first = first;
        this.second = second;
    }

    public int getHammingDistance() {
        int distance = 0;
        for (int i = 0; i < first.length(); i++) {
            if (first.charAt(i) != second.charAt(i)) distance++;
        }
        return distance;
    }
}
