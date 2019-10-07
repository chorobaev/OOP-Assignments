package flaterlab;

import javafx.scene.image.Image;

public class Contact {
    private static long contactsId = 0;
    public static Image placeholderImage = new Image("images/contact.png");

    private long id;
    private String name;
    private String surname;
    private String phoneNumber;
    private String email;
    private Image image;

    public Contact() {
        this.id = contactsId++;
    }

    public Contact(String name, String surname, String phoneNumber, String email) {
        this();
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Image getImage() {
        if (image == null) {
            return placeholderImage;
        }

        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getFullName() {
        return name + " " + surname;
    }



    @Override
    public String toString() {
        return name + " " + surname;
    }
}
