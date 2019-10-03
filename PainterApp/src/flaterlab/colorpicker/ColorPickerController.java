package flaterlab.colorpicker;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class ColorPickerController {
    private static final short MAX_VALUE = 255;
    private boolean shouldUpdateTextField = false;
    private OnColorSelectListener colorSelectListener;

    @FXML private Slider sliderRed;
    @FXML private Slider sliderGreen;
    @FXML private Slider sliderBlue;
    @FXML private Slider sliderAlpha;
    @FXML private TextField tfRed;
    @FXML private TextField tfGreen;
    @FXML private TextField tfBlue;
    @FXML private TextField tfAlpha;
    @FXML private Rectangle rgPreview;
    @FXML private Button btnChoose;

    @FXML void initialize() {
        initSlider(sliderRed, tfRed);
        initSlider(sliderGreen, tfGreen);
        initSlider(sliderBlue, tfBlue);
        initSlider(sliderAlpha, tfAlpha);

        sliderAlpha.setValue(MAX_VALUE);
        System.out.println("onPickerInit");
    }

    @FXML
    void onChoose(ActionEvent event) {
        Stage stage = (Stage) btnChoose.getScene().getWindow();
        stage.close();
    }

    public void initColor(Color color) {
        double red = color.getRed();
        double green = color.getGreen();
        double blue = color.getBlue();
        double alpha =color.getOpacity();

        System.out.println("Initialized ->> \n" +
            "Red: " + red + ";  " +
            "Green: " + green + ";  " +
            "Blue: " + blue + ";  " +
            "Alpha: " + alpha);

        sliderRed.setValue(red * MAX_VALUE);
        sliderGreen.setValue(green * MAX_VALUE);
        sliderBlue.setValue(blue * MAX_VALUE);
        sliderAlpha.setValue(alpha * MAX_VALUE);
    }

    public void setOnColorSelectListener(OnColorSelectListener colorSelectListener) {
        this.colorSelectListener = colorSelectListener;
    }

    private void initSlider(Slider slider, TextField textField) {
        onlyNumber(textField);
        slider.setMax(MAX_VALUE);
        slider.setMin(0);
        slider.valueProperty().addListener((observableValue, oldValue, newValue) -> {
            updateColor();
            if (shouldUpdateTextField) {
                textField.setText(String.valueOf(newValue.intValue()));
                System.out.println("Slide value  changed from " + oldValue + " to " + newValue);
            }
            shouldUpdateTextField = true;
        });

        textField.focusedProperty().addListener((observableValue, oldFocused, newFocused) -> {
            if (!newFocused) {
                updateSliderValue(slider, textField.getText());
            }
        });

        textField.setOnAction(event -> {
            updateSliderValue(slider, textField.getText());
        });
    }

    private void updateSliderValue(Slider slider, String value) {
        int intValue = value.isBlank() ? 0 : Integer.parseInt(value);
        shouldUpdateTextField = false;
        slider.setValue(intValue);
    }

    private static void onlyNumber(TextField textField) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                textField.setText(newValue.replaceAll("[^\\d]", ""));
            } else {
                if (!newValue.isBlank() && Integer.parseInt(newValue) > 255) {
                    textField.setText(oldValue);
                }
            }
        });
    }

    private void updateColor() {
        double red = getValueOfSlider(sliderRed);
        double green = getValueOfSlider(sliderGreen);
        double blue = getValueOfSlider(sliderBlue);
        double alpha = getValueOfSlider(sliderAlpha);

        System.out.println("Updated ->> \n" +
            "Red: " + red + ";  " +
            "Green: " + green + ";  " +
            "Blue: " + blue + ";  " +
            "Alpha: " + alpha);

        Color color = new Color(red, green, blue, alpha);
        rgPreview.setFill(color);

        if (colorSelectListener != null) {
            colorSelectListener.onColorSelected(color);
        }
    }

    private static double getValueOfSlider(Slider slider) {
        return slider.getValue() / MAX_VALUE;
    }

    public interface OnColorSelectListener {
        void onColorSelected(Color color);
    }
}
