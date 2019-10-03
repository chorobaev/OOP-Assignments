package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class MainController {

    @FXML private TextField tfAge;
    @FXML private TextField tfMaxRate;
    @FXML private TextField tfTargetRange;

    @FXML
    void initialize() {
        onlyNumber(tfAge);
    }

    @FXML
    void onCalculate(ActionEvent event) {
        int maxRate = 220 - Integer.parseInt(tfAge.getText());
        tfMaxRate.setText(String.valueOf(maxRate));

        int rateMin = (int) (maxRate * 0.5);
        int rateMax = (int) (maxRate * 0.85);
        tfTargetRange.setText(String.format("%d - %d", rateMin, rateMax));
    }

    private void onlyNumber(TextField textField) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                textField.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    }
}
