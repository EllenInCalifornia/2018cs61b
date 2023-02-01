import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

public class TestOffByOne {

    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    // Your tests go here.
    @Test
    public void testOffByOne() {
        char a = 'a';
        char b = 'b';
        assertTrue(offByOne.equalChars(a, b));
        char x = 'x';
        char y = 'x';
        assertFalse(offByOne.equalChars(x, y));
        char c = ' ';
        char d = ' ';
        assertFalse(offByOne.equalChars(c, d));
    }

    /*Uncomment this class once you've created your CharacterComparator interface and OffByOne class.

     */
}
