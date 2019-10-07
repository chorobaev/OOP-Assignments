package flaterlab.main;

import flaterlab.Contact;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;


public class ContactCellFactory implements Callback<ListView<Contact>, ListCell<Contact>> {

    @Override
    public ListCell<Contact> call(ListView<Contact> contactListView) {
        return new ContactItemCell();
    }
}
