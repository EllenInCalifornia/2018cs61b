public class OffByN implements CharacterComparator {
    private int n;

    public OffByN(int a) {
        n = a;
    }

    @Override
    public boolean equalChars(char x, char y) {
        if (x - y == n || y - x == n) {
            return true;
        }
        return false;
    }
}
