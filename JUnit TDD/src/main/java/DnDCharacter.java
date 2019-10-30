import java.util.Random;

public class DnDCharacter {
    private Random random = new Random(19247);
    private int strength = random.nextInt(16) + 2;
    private int dexterity = random.nextInt(16) + 2;
    private int constitution = random.nextInt(16) + 2;
    private int intelligence = random.nextInt(16) + 2;
    private int wisdom = random.nextInt(16) + 2;
    private int charisma = random.nextInt(16) + 2;

    public int getStrength() {
        return strength;
    }

    public int getDexterity() {
        return dexterity;
    }

    public int getConstitution() {
        return constitution;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public int getWisdom() {
        return wisdom;
    }

    public int getCharisma() {
        return charisma;
    }

    public int getHitpoints() {
        return 10 + modifier(constitution);
    }

    public int modifier(int constitution) {
        int m = constitution - 10;
        double r = m / 2.1;
        if (m < 0) r = m / 1.99999;
        return (int) Math.round(r);
    }

    public int ability() {
        return random.nextInt(16) + 2;
    }
}
