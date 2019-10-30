public class RaindropConverter {
    private static final String three = "Pling";
    private static final String five = "Plang";
    private static final String seven = "Plong";

    public String convert(int n) {
        StringBuilder sb = new StringBuilder();
        if (n % 3 == 0) sb.append(three);
        if (n % 5 == 0) sb.append(five);
        if (n % 7 == 0) sb.append(seven);

        String result = sb.toString();
        return result.isEmpty() ? String.valueOf(n) : result;
    }
}
