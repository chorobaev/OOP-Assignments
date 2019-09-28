package sample;

public class BMILevel {
    public static final BMILevel[] bmis = {
            new BMILevel("Underweight", 0, 18.5, "underweight.png"),
            new BMILevel("Normal", 18.5, 25, "normal.png"),
            new BMILevel("Overweight", 25, 30, "overweight.png"),
            new BMILevel("Obese", 30, 35, "obese.png"),
            new BMILevel("Extremely obese", 35, 100, "extremely_obese.png"),
    };

    public static BMILevel getBMILevelFor(double bmi) {
        for (BMILevel bmiLevel : bmis) {
            if (bmiLevel.includes(bmi)) {
                return bmiLevel;
            }
        }
        throw new IllegalArgumentException("No such BMI!");
    }

    private String name;
    private double lowestBmi;
    private double highestBmi;
    private String imageName;

    public BMILevel(String name, double lowestBmi, double highestBmi, String imageName) {
        this.name = name;
        this.lowestBmi = lowestBmi;
        this.highestBmi = highestBmi;
        this.imageName = imageName;
    }

    public String getName() {
        return name;
    }

    public boolean includes(double bmi) {
        return bmi >= lowestBmi && bmi < highestBmi;
    }

    public String getImageName() {
        return imageName;
    }
}
