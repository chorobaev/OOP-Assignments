public class SpaceAge {
    private long value;

    public SpaceAge(long value) {
        this.value = value;
    }

    public double onEarth() {
        return value / 60.0 / 60.0 / 24.0 / 365.25;
    }

    public double onMercury() {
        return onEarth() / 0.2408467;
    }

    public double onVenus() {
        return onEarth() / 0.61519726;
    }

    public double onMars() {
        return onEarth() / 1.8808158;
    }

    public double onJupiter() {
        return onEarth() / 11.862615;
    }

    public double onSaturn() {
        return onEarth() / 29.447498;
    }

    public double onUranus() {
        return onEarth() / 84.016846;
    }

    public double onNeptune() {
        return onEarth() / 164.79132;
    }
}
