package midterm;

public class Book {
    private String name;
    private Author[] authors;
    private double price;
    private int qty = 0;

    public Book(String name, Author[] authors, double price) {
        this.name = name;
        this.authors = authors;
        this.price = price;
    }

    public Book(String name, Author[] authors, double price, int qty) {
        this.name = name;
        this.authors = authors;
        this.price = price;
        this.qty = qty;
    }

    public String getName() {
        return name;
    }

    public Author[] getAuthors() {
        return authors;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    @Override
    public String toString() {
        return "midterm.Book[" +
            "name=" + name +
            ", authors=" + arrayToString(authors) +
            ", price=" + price +
            ", qty=" + qty +
            ']';
    }

    public String getAuthorsNames() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < authors.length; i++) {
            if (i != 0) sb.append(", ");
            Author author = authors[i];
            sb.append(author.getName());
        }
        return sb.toString();
    }

    public static String arrayToString(Object[] objects) {
        StringBuilder sb = new StringBuilder();
        sb.append('{');
        for (int i = 0; i < objects.length; i++) {
            if (i != 0) sb.append(", ");
            Object author = objects[i];
            sb.append(author);
        }
        sb.append('}');
        return sb.toString();
    }
}
