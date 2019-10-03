package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

import java.util.ArrayList;
import java.util.List;

public class MainController {
    private static final int UNDO_SIZE = 10;
    private Double lastX = -1.0, lastY = -1.0;
    private ArrayList<Line> lines = new ArrayList<>();

    private final Color[] penColors = {
            Color.BLACK, Color.WHITE, Color.RED, Color.GREEN, Color.BLUE
    };
    private Color currentPenColor = penColors[0];

    private final int[] penSizes = {1, 3, 5};
    private int currentPenSize = penSizes[0];

    private boolean isMousePressed = false;

    @FXML private Pane mainPane;
    @FXML private ToggleGroup toggleGroupColor;
    @FXML private ToggleGroup toggleGroupPen;
    @FXML private RadioButton rbColorBlack;
    @FXML private RadioButton rbColorWhite;
    @FXML private RadioButton rbColorRed;
    @FXML private RadioButton rbColorGreen;
    @FXML private RadioButton rbColorBlue;
    @FXML private RadioButton rbSizeSmall;
    @FXML private RadioButton rbSizeMedium;
    @FXML private RadioButton rbSizeLarge;

    @FXML
    void onMouseDragged(MouseEvent event) {
        //System.out.println("OnMouseDragged: " + event.getX() + ", " + event.getY());
        drawLine(event.getX(), event.getY());
    }

    @FXML
    void onMousePressed(MouseEvent event) {
        isMousePressed = true;
        lastX = event.getX();
        lastY = event.getY();
    }

    @FXML
    void onMouseReleased(MouseEvent event) {
        System.out.println("OnMouseReleased");
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
            currentPenColor = penColors[(int) toggleGroupColor.getSelectedToggle().getUserData()];
        } catch (Exception ignored) {}
    }

    @FXML
    void onPenSelected(ActionEvent event) {
        System.out.println("OnPenSelected");
        try {
            currentPenSize = penSizes[(int) toggleGroupPen.getSelectedToggle().getUserData()];
        } catch (Exception ignored) {}
    }

    @FXML
    void onUndo(ActionEvent event) {
        System.out.println("OnUndoClicked");
        if (lines.size() != 0) {
            List<Line> undoLines = new ArrayList<>();
            for (int i = lines.size() - 1; i >= 0 && lines.size() - i < UNDO_SIZE; i--) {
                undoLines.add(lines.get(i));
            }
            mainPane.getChildren().removeAll(undoLines);
            lines.removeAll(undoLines);
        }
    }

    @FXML
    void onClear(ActionEvent event) {
        System.out.println("On clear");
        mainPane.getChildren().removeAll(lines);
    }

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

    private void drawLine(double x, double y) {
        if (x < 0) {
            return;
        }

        Line line = new Line(lastX, lastY, x, y);
        line.setStrokeWidth(currentPenSize);
        line.setStroke(currentPenColor);
        mainPane.getChildren().add(line);
        lines.add(line);

        lastX = x;
        lastY = y;
    }
}
