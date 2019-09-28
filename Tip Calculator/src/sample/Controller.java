package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;

public class Controller {
    private final String currency = NumberFormat.getCurrencyInstance().getCurrency().getSymbol();
    private int tipPercentage = 0;
    private BigDecimal currentTip = new BigDecimal(0);
    private BigDecimal currentPrice = new BigDecimal(0);

    @FXML
    private TextField tfPrice;

    @FXML
    private Slider sliderPercent;

    @FXML
    private Label labelPercentage;

    @FXML
    private TextField tfTip;

    @FXML
    private TextField tfTotal;

    @FXML
    private TextField tfPeople;

    @FXML
    private TextField tfPricePerPerson;

    @FXML
    void onCalculate(ActionEvent event) {
        calculate();
    }

    @FXML
    void initialize() {
        tfTip.setText(currency + "0");
        tfTotal.setText(currency + "0");
        tfPricePerPerson.setText(currency + "0");
        onlyNumber(tfPrice);
        onlyNumber(tfPeople);

        sliderPercent.setMax(31);
        sliderPercent.valueProperty().addListener((observableValue, oldValue, newValue) -> {
            int value = newValue.intValue();
            tipPercentage = value - value % 5;
            labelPercentage.setText(tipPercentage + "%");
            calculateTip();
        });
    }

    private void onlyNumber(TextField textField) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                textField.setText(newValue.replaceAll("[^\\d]", ""));
            }
            if (textField.equals(tfPrice)) {
                calculateTip();
            }
        });
    }

    private void calculateTip() {
        if (!tfPrice.getText().isBlank()) {
            currentPrice = new BigDecimal(Integer.valueOf(tfPrice.getText()));
            currentTip = ((currentPrice
                    .divide(new BigDecimal(100.0), RoundingMode.FLOOR)
                    .multiply(new BigDecimal(tipPercentage))));
            tfTip.setText(currency + currentTip);
        }
    }

    private void calculate() {
        BigDecimal total = currentPrice.add(currentTip);
        tfTotal.setText(currency + total);

        try {
            BigDecimal people = new BigDecimal(Integer.valueOf(tfPeople.getText()));
            BigDecimal perPerson = total.divide(people, RoundingMode.CEILING);

            tfPricePerPerson.setText(currency + perPerson);
        } catch (Exception ignored) {
            tfPricePerPerson.setText(currency + 0);
        }
    }
}
