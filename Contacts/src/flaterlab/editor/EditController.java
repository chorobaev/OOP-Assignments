package flaterlab.editor;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import flaterlab.Contact;

import java.io.File;

public class EditController {
    @FXML private AnchorPane apDetail;
    @FXML private ImageView ivAvatar;
    @FXML private TextField tfName;
    @FXML private TextField tfSurname;
    @FXML private TextField tfPhoneNumber;
    @FXML private TextField tfEmail;

    private final FileChooser fileChooser = new FileChooser();
    private Contact contact;
    private OnContactChangedListener contactChangedListener;

    @FXML
    void initialize() {
        FileChooser.ExtensionFilter extensionFilter =
            new FileChooser.ExtensionFilter("Image File", "*.jpg", "*.png");
        fileChooser.getExtensionFilters().add(extensionFilter);
    }

    public void setContact(Contact contact) {
        if (contact == null) {
            ivAvatar.setImage(Contact.placeholderImage);
            return;
        }

        this.contact = contact;
        tfName.setText(contact.getName());
        tfSurname.setText(contact.getSurname());
        tfPhoneNumber.setText(contact.getPhoneNumber());
        tfEmail.setText(contact.getEmail());
        ivAvatar.setImage(contact.getImage());
    }

    public void setOnContactChangedListener(OnContactChangedListener listener) {
        contactChangedListener = listener;
    }

    @FXML
    void onChangeImage(ActionEvent event) {
        File file = fileChooser.showOpenDialog(apDetail.getScene().getWindow());
        if (file != null) {
            Image image = new Image(file.toURI().toString());
            ivAvatar.setImage(image);
        }
    }

    @FXML
    void onSaveClicked(ActionEvent event) {
        saveAndClose();
    }

    @FXML
    void onKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            saveAndClose();
        }
    }

    private void saveAndClose() {
        if (contactChangedListener != null) {
            if (contact == null) {
                contact = new Contact();
            }

            contact.setImage(ivAvatar.getImage());
            contact.setName(tfName.getText());
            contact.setSurname(tfSurname.getText());
            contact.setPhoneNumber(tfPhoneNumber.getText());
            contact.setEmail(tfEmail.getText());

            contactChangedListener.onChanged(contact);
        }
        closeWindow();
    }

    private void closeWindow() {
        Stage stage = (Stage) apDetail.getScene().getWindow();
        stage.close();
    }

    public interface OnContactChangedListener {
        void onChanged(Contact contact);
    }
}
