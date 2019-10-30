public class ArmstrongNumbers {

    public boolean isArmstrongNumber(int n) {
        String s = String.valueOf(n);
        int l = s.length();
        int sum = 0;
        for (int i = 0; i < l; i++) {
            sum += Math.pow(s.charAt(i) - 48, l);
        }
        return sum == n;
    }
}
