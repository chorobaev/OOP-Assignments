package flaterlab.main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import flaterlab.colorpicker.ColorPickerController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

public class MainController {
    private Double lastX = -1.0, lastY = -1.0;
    private Stack<ArrayList<Line>> lines = new Stack<>();
    private ArrayList<Line> tempLines = new ArrayList<>();

    private final Color[] penColors = {
        Color.BLACK, Color.WHITE, Color.RED, Color.GREEN, Color.BLUE
    };
    private Color currentPenColor = penColors[0];

    private final int[] penSizes = {1, 3, 5};
    private int currentPenSize = penSizes[0];

    private boolean isMousePressed = false;
    private boolean shortcutsReady = false;

    @FXML
    private Pane mainPane;
    @FXML
    private ToggleGroup toggleGroupColor;
    @FXML
    private ToggleGroup toggleGroupPen;
    @FXML
    private RadioButton rbColorBlack;
    @FXML
    private RadioButton rbColorWhite;
    @FXML
    private RadioButton rbColorRed;
    @FXML
    private RadioButton rbColorGreen;
    @FXML
    private RadioButton rbColorBlue;
    @FXML
    private RadioButton rbSizeSmall;
    @FXML
    private RadioButton rbSizeMedium;
    @FXML
    private RadioButton rbSizeLarge;
    @FXML
    private Button btnPenColor;
    @FXML
    private Button btnUndo;
    @FXML
    private Button btnClear;

    @FXML
    void initialize() {
        rbColorBlack.setUserData(0);
        rbColorWhite.setUserData(1);
        rbColorRed.setUserData(2);
        rbColorGreen.setUserData(3);
        rbColorBlue.setUserData(4);

        rbSizeSmall.setUserData(0);
        rbSizeMedium.setUserData(1);
        rbSizeLarge.setUserData(2);
    }

    @FXML
    void onMouseDragged(MouseEvent event) {
        //System.out.println("OnMouseDragged: " + event.getX() + ", " + event.getY());
        drawLine(event.getX(), event.getY());
    }

    @FXML
    void onMousePressed(MouseEvent event) {
        if (!shortcutsReady) {
            initShortcuts();
            shortcutsReady = true;
        }
        isMousePressed = true;
        lastX = event.getX();
        lastY = event.getY();
    }

    @FXML
    void onMouseReleased(MouseEvent event) {
        System.out.println("OnMouseReleased");
        lines.add(tempLines);
        tempLines = new ArrayList<>();
        isMousePressed = false;
    }

    @FXML
    void onMouseExited(MouseEvent event) {
        System.out.println("onMouseExited");
    }

    @FXML
    void onMouseEntered(MouseEvent event) {
        System.out.println("OnMouseEntered");
        if (isMousePressed) {
            lastX = event.getX();
            lastY = event.getY();
        }
    }

    @FXML
    void onColorSelected(ActionEvent event) {
        System.out.println("OnColorSelected");
        try {
            updatePenColor(penColors[(int) toggleGroupColor.getSelectedToggle().getUserData()]);
        } catch (Exception ignored) {
        }
    }

    @FXML
    void onPenSelected(ActionEvent event) {
        System.out.println("OnPenSelected");
        try {
            currentPenSize = penSizes[(int) toggleGroupPen.getSelectedToggle().getUserData()];
        } catch (Exception ignored) {
        }
    }

    @FXML
    void onColorPickerClicked(ActionEvent event) {
        System.out.println("OnColorPickerClicked");
        startColorPicker();
    }

    private void startColorPicker() {
        FXMLLoader loader;
        try {
            loader = new FXMLLoader(getClass().getResource("../colorpicker/color_picker.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Color Picker");
            stage.setScene(new Scene(loader.load(), 470, 180));
            stage.setResizable(false);

            ColorPickerController controller = loader.getController();
            controller.initColor(currentPenColor);
            controller.setOnColorSelectListener(this::updatePenColor);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onUndo(ActionEvent event) {
        System.out.println("OnUndoClicked");
        if (lines.size() != 0) {
            mainPane.getChildren().removeAll(lines.pop());
        }
    }

    @FXML
    void onClear(ActionEvent event) {
        System.out.println("On clear");
        while (!lines.empty()) {
            mainPane.getChildren().removeAll(lines.pop());
        }
    }

    private void drawLine(double x, double y) {
        if (x < 0) {
            return;
        }

        Line line = new Line(lastX, lastY, x, y);
        line.setStrokeWidth(currentPenSize);
        line.setStroke(currentPenColor);
        mainPane.getChildren().add(line);
        tempLines.add(line);

        lastX = x;
        lastY = y;
    }

    private void updatePenColor(Color color) {
        currentPenColor = color;
        String hexColor = currentPenColor.toString().substring(2);
        btnPenColor.setStyle("-fx-background-color: #" + hexColor);
    }

    private void initShortcuts() {
        btnUndo.setMnemonicParsing(true);
        KeyCombination kc = new KeyCodeCombination(KeyCode.Z, KeyCombination.CONTROL_DOWN);
        mainPane.getScene().getAccelerators().put(kc, () -> {
            onUndo(null);
        });
    }
}
