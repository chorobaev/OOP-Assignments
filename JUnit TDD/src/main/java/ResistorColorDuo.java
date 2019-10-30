public class ResistorColorDuo {
    private String[] colors = {"black", "brown", "red", "orange", "yellow", "green", "blue", "violet", "grey", "white"};

    public int colorCode(String color) {
        for (int i = 0; i < colors.length; i++) {
            if (colors[i].equals(color)) {
                return i;
            }
        }
        return -1;
    }

    public String[] colors() {
        return colors;
    }

    public int value(String... colors) {
        int res = 0;
        for (int i = 0; i < colors.length; i++) {
            res += colorCode(colors[i]) * Math.pow(10, colors.length - i - 1);
        }
        return res;
    }
}
