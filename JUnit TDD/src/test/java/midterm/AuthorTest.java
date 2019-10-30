package midterm;

import midterm.Author;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AuthorTest {
    private Author author;
    private String name;
    private String email;
    private char gender;
    private final String TO_STRING_PATTERN = "midterm.Author[name=%s, email=%s, gender=%c]";

    @Before
    public void setup() {
        name = "Nurbol";
        email = "nurbol.chorobaev@gmail.com";
        gender = 'm';
        author = new Author(name, email, gender);
    }

    @Test
    public void testConstructor() {
        Author author = new Author(name, email, gender);
    }

    @Test
    public void testToString() {
        String expected = String.format(TO_STRING_PATTERN, name, email, gender);
        assertEquals(expected, author.toString());
    }

    @Test
    public void testSetEmail() {
        author.setEmail("nurbol.chorobaev@iaau.edu.com");
    }

    @Test
    public void testGetName() {
        assertEquals(name, author.getName());
    }

    @Test
    public void testGetEmail() {
        assertEquals(email, author.getEmail());
    }

    @Test
    public void testGetGender() {
        assertEquals(gender, author.getGender());
    }

    @After
    public void cleanUp() {
        author = null;
    }
}
