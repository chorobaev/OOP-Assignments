package midterm;

import midterm.Author;
import midterm.Book;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class BookTest {
    private Book book;
    private String bName;
    private Author[] authors;
    private double price;
    private int qty;
    private final String TO_STRING_PATTERN = "midterm.Book[name=%s, authors=%s, price=%f, qty=%d]";

    @Before
    public void setup() {
        bName = "Harry Potter";
        authors = new Author[]{
            new Author("Nurbol", "chorobaev@gmail.com", 'm'),
            new Author("Somebody", "anyone@gmail.com", 'f')
        };
        price = 200.583534;
        qty = 15;
        book = new Book(bName, authors, price, qty);
    }

    @Test
    public void testConstructor1() {
        Book book = new Book("Name", new Author[]{}, 100.0);
    }

    @Test
    public void testConstructor2() {
        Book book = new Book("Name", new Author[]{}, 100.0, 14);
    }

    @Test
    public void testToString() {
        // There is a dependency from midterm.Book class's arrayToString static method.
        // Ideally it must be moved to another utility class and tested separately.
        String expected = String.format(TO_STRING_PATTERN, bName, Book.arrayToString(authors), price, qty);
        assertEquals(expected, book.toString());
    }

    @Test
    public void testSetPrice() {
        book.setPrice(1234.3243);
    }

    @Test
    public void testSetQty() {
        book.setQty(234);
    }

    @Test
    public void testGetName() {
        assertEquals(bName, book.getName());
    }

    @Test
    public void testGetAuthors() {
        assertArrayEquals(authors, book.getAuthors());
    }

    @Test
    public void testGetPrice() {
        assertEquals(price, book.getPrice(), 0.001);
    }

    @Test
    public void testGetQty() {
        assertEquals(qty, book.getQty());
    }

    @Test
    public void testGetAuthorsName() {
        String expectedNames = authors[0].getName() + ", " + authors[1].getName();
        assertEquals(expectedNames, book.getAuthorsNames());
    }

    @After
    public void cleanUp() {
        book = null;
        authors = null;
    }
}
