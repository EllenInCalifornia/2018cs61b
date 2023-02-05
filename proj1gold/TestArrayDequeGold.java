import static org.junit.Assert.*;
import org.junit.Test;
public class TestArrayDequeGold {
    @Test
    public void testStudentArrayDeque() {
        StudentArrayDeque<Integer> testArray = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> stdArray = new ArrayDequeSolution<>();
        String log = "";
        for (int i = 0; i < 1000; i++) {
            if (testArray.size() == 0) {
                int firstOrLast = StdRandom.uniform(2);
                Integer numberToAdd = StdRandom.uniform(1000);
                if (firstOrLast == 0) {
                    testArray.addFirst(numberToAdd);
                    stdArray.addFirst(numberToAdd);
                    log = log + "addFirst(" + numberToAdd + ")\n";
                } else {
                    testArray.addLast(numberToAdd);
                    stdArray.addLast(numberToAdd);
                    log = log + "addLast(" + numberToAdd + ")\n";
                }
            } else {
                int oneOfFour = StdRandom.uniform(4);
                Integer numberToAdd = StdRandom.uniform(1000);
                Integer removeofTest = 0;
                Integer removeofStd = 0;
                switch (oneOfFour) {
                    case 0:
                        testArray.addFirst(numberToAdd);
                        stdArray.addFirst(numberToAdd);
                        log = log + "addFirst(" + numberToAdd + ")\n";
                        break;
                    case 1:
                        testArray.addLast(numberToAdd);
                        stdArray.addLast(numberToAdd);
                        log = log + "addLast(" + numberToAdd + ")\n";
                        break;
                    case 2:
                        removeofTest = testArray.removeFirst();
                        removeofStd = stdArray.removeFirst();
                        log += "removeFirst()\n";
                        break;
                    case 3:
                        removeofTest = testArray.removeLast();
                        removeofStd = stdArray.removeLast();
                        log += "removeLast()\n";
                        break;

                }
                assertEquals(log, removeofStd, removeofTest);
            }

        }
    }
}
/*
Expected :999, 1999
Actual   :510, 510
removeLast()
 */