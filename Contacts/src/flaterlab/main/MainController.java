package flaterlab.main;

import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import flaterlab.Contact;
import flaterlab.editor.EditController;

import java.io.IOException;
import java.util.Comparator;

public class MainController {
    @FXML private ListView<Contact> lvContacts;
    @FXML private VBox vbDetail;
    @FXML private Label labelNotSelected;
    @FXML private ImageView ivAvatar;
    @FXML private TextField tfName;
    @FXML private TextField tfSurname;
    @FXML private TextField tfPhoneNumber;
    @FXML private TextField tfEmail;

    private final ObservableList<Contact> contacts = FXCollections.observableArrayList();
    private final ChangeListener<Number> contactChangeListener = (observableValue, oldValue, newValue) -> {
        onContactSelected(true);
        if (contacts.size() != 0 && newValue.intValue() != -1) {
            updateDetailView(contacts.get(newValue.intValue()));
        } else {
            onContactSelected(false);
        }
    };

    @FXML
    void initialize() {
        contacts.add(new Contact("Nurbol",
            "Chorobaev",
            "0778168066",
            "chorobaev.nurbol@gmail.com"));
        contacts.add(new Contact("Tilek",
            "Sydykov",
            "0778168066",
            "himikfromkg@gmail.com"));
        contacts.add(new Contact("Balancha",
            "Tukunchoev",
            "0778168066",
            "tuckunchoev.balancha@gmail.com"));

        lvContacts.setCellFactory(new ContactCellFactory());
        lvContacts.setItems(contacts);
        sortContacts();
        addContactChangeListener();
    }

    private void addContactChangeListener() {
        lvContacts.getSelectionModel().selectedIndexProperty()
            .addListener(contactChangeListener);
    }

    @FXML
    void onDeleteClicked(ActionEvent event) {
        contacts.remove(lvContacts.getSelectionModel().getSelectedItem());
        if (contacts.size() > 0) {
            lvContacts.getSelectionModel().select(0);
        }
    }

    @FXML
    void onEditClicked(ActionEvent event) {
        Contact contact = lvContacts.getSelectionModel().getSelectedItem();
        startEditWindow(contact);
    }

    @FXML
    void onCreateNewSelected(ActionEvent event) {
        startEditWindow(null);
    }

    private void startEditWindow(Contact contact) {
        FXMLLoader loader;
        try {
            loader = new FXMLLoader(getClass().getResource("../editor/editor.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Contact");
            stage.setScene(new Scene(loader.load(), 400, 310));
            stage.setResizable(false);

            EditController controller = loader.getController();
            controller.setContact(contact);
            controller.setOnContactChangedListener(this::onContactChanged);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void onContactChanged(Contact contact) {
        updateContact(contact);
    }

    private void updateContact(Contact contact) {
        contacts.remove(contact);
        contacts.add(contact);
        lvContacts.getSelectionModel().select(contact);
        sortContacts();
    }

    private void sortContacts() {
        contacts.sort(Comparator.comparing(contact -> contact.getName() + contact.getSurname()));
    }

    private void updateDetailView(Contact contact) {
        tfName.setText(contact.getName());
        tfSurname.setText(contact.getSurname());
        tfPhoneNumber.setText(contact.getPhoneNumber());
        tfEmail.setText(contact.getEmail());
        ivAvatar.setImage(contact.getImage());
    }

    private void onContactSelected(boolean selected) {
        labelNotSelected.setVisible(!selected);
        vbDetail.setVisible(selected);
    }
}
