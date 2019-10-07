package flaterlab.main;

import flaterlab.Contact;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ContactItemCell extends ListCell<Contact> {
    private HBox hBox = new HBox(8.0);
    private ImageView ivAvatar =  new ImageView();
    private Label labelName = new Label();
    private Label labelPhoneNumber = new Label();

    public ContactItemCell() {
        hBox.getChildren().add(ivAvatar);
        VBox vBox = new VBox();
        vBox.getChildren().add(labelName);
        vBox.getChildren().add(labelPhoneNumber);
        hBox.getChildren().add(vBox);

        ivAvatar.setPreserveRatio(true);
        ivAvatar.setFitHeight(40.0);
        ivAvatar.setFitWidth(40.0);
    }

    @Override
    protected void updateItem(Contact contact, boolean empty) {
        super.updateItem(contact, empty);

        if (empty || contact == null) {
            setGraphic(null);
        } else {
            ivAvatar.setImage(contact.getImage());
            labelName.setText(contact.getFullName());
            labelPhoneNumber.setText(contact.getPhoneNumber());

            setGraphic(hBox);
        }
    }
}
