import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();
    static CharacterComparator offByOne = new OffByOne();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);

    }
    @Test
    public void testisPalindrome() {
        String a = "";
        assertTrue(palindrome.isPalindrome(a));
        String b = "beautiful";
        assertFalse(palindrome.isPalindrome(b));
        String c = "deedeed";
        assertTrue(palindrome.isPalindrome(c));
        String d = " ";
        assertTrue(palindrome.isPalindrome(d));
        String e = "a";
        assertTrue(palindrome.isPalindrome(e));

    }


    @Test
    public void testisOffbyOnePalindrome() {
        String a = "";
        CharacterComparator cc = new OffByOne();
        assertTrue(palindrome.isPalindrome(a, cc));
        String b = "beautiful";
        assertFalse(palindrome.isPalindrome(b,cc));
        String c = "benda";
        assertTrue(palindrome.isPalindrome(c,cc));
        String d = " ";
        assertTrue(palindrome.isPalindrome(d,cc));
        String e = "a";
        assertTrue(palindrome.isPalindrome(e,cc));

    }

    @Test
    public void testisOffbyNPalindrome() {
        String a = "";
        CharacterComparator cc = new OffByN(3);
        assertTrue(palindrome.isPalindrome(a, cc));
        String b = "abed";
        assertTrue(palindrome.isPalindrome(b, cc));
        String c = "bccb";
        assertFalse(palindrome.isPalindrome(c,cc));
        String d = " ";
        assertTrue(palindrome.isPalindrome(d, cc));
        String e = "a";
        assertTrue(palindrome.isPalindrome(e, cc));

    }



    /* Uncomment this class once you've created your Palindrome class. */
}
