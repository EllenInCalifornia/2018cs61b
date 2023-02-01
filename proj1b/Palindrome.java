public class Palindrome {
    /*Given a String, wordToDeque should return a Deque
     where the characters appear in the same order as in the String. */
    public Deque<Character> wordToDeque(String word) {
        Deque ans = new LinkedListDeque();
        for (int i = 0; i < word.length(); i++) {
            ans.addLast(word.charAt(i));
        }
        return ans;
    }
    /* use deque and recursion

     */
    private boolean isPalindrome(Deque<Character> wordDq) {
        if (wordDq.size() == 1 || wordDq.size() == 0) {
            return true;
        }
        return wordDq.removeFirst() == wordDq.removeLast()
                && isPalindrome(wordDq);
    }

    private boolean isPalindromeHelper(Deque<Character> wd, CharacterComparator cc) {
        if (wd.size() == 0 || wd.size() == 1) {
            return true;
        }
        char f = wd.removeFirst();
        char l = wd.removeLast();
        return cc.equalChars(f, l) && isPalindromeHelper(wd, cc);
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> wd = wordToDeque(word);
        return isPalindromeHelper(wd, cc);
    }



    public boolean isPalindrome(String word) {
        Deque<Character> wordDq = wordToDeque(word);
        return isPalindrome(wordDq);
    }
    /*
    uses string.charAt()
    public boolean isPalindrome(String word) {
        int p1 = 0;
        int p2 = word.length() - 1;
        while (p1 <= p2) {
            if (word.charAt(p1) != word.charAt(p2)) {
                return false;
            }
            p1++;
            p2--;
        }
        return true;
    }
     */

}
