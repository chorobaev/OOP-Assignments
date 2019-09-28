package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;

public class MainController {
    private ToggleGroup radioGroup;

    @FXML
    private TextField tfHeight;

    @FXML
    private TextField tfWeight;

    @FXML
    private TextField tfBMI;

    @FXML
    private TextField tfLevel;

    @FXML
    private ImageView imageView;

    @FXML
    private void initialize() {
        radioGroup = new ToggleGroup();

        onlyNumber(tfHeight);
        onlyNumber(tfWeight);
    }

    @FXML
    void onChanged(KeyEvent event) {

    }

    @FXML
    void onCalculate(ActionEvent event) {
        try {
            calculate();
        } catch (NumberFormatException ignored) {

        }
    }

    private static void onlyNumber(TextField textField) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                textField.setText(newValue.replaceAll("[^\\d]", ""));
            }
            if (newValue.length() > 3) {
                textField.setText(oldValue);
            }
        });
    }

    private void calculate() {
        int weight = Integer.valueOf(tfWeight.getText());
        int height = Integer.valueOf(tfHeight.getText());
        double bmi = weight / Math.pow(0.01*height, 2);
        tfBMI.setText(String.format("%.2f kg/m2", bmi));
        try {
            BMILevel bmiLevel = BMILevel.getBMILevelFor(bmi);
            tfLevel.setText(bmiLevel.getName());
            Image image = new Image(AppConstants.RES_PATH + bmiLevel.getImageName());
            imageView.setImage(image);
        } catch (IllegalArgumentException ex) {
            tfLevel.setText(ex.getMessage());
        }
    }
}
