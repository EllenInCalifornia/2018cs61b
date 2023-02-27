package synthesizer;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void testEnqueue() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer(10);
        arb.enqueue(1);
        arb.enqueue(3);
        arb.enqueue(4);
        int expectedFillcount = 3;
        int actualFc = arb.fillCount();
        assertEquals("expected: " + expectedFillcount + "; actual: " + actualFc, expectedFillcount, actualFc);
    }
    @Test
    public void testDequeue() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer(10);
        arb.enqueue(1);
        arb.enqueue(3);
        arb.enqueue(4);
        int act = arb.dequeue();
        int expected = 1;
        assertEquals("expected: " + expected + "; i" +
                "actual: "+ act,expected,act);
    }

    @Test
    public void testPeek() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer(10);
        arb.enqueue(1);
        arb.enqueue(3);
        arb.enqueue(4);
        int act = arb.peek();
        int expected = 1;
        assertEquals("expected: " + expected + "; i" +
                "actual: "+ act,expected,act);
        assertEquals(3,arb.fillCount());
    }
    @Test(expected = RuntimeException.class)
    public void testDequeue2() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer(10);
        arb.enqueue(1);
        arb.enqueue(3);
        arb.enqueue(4);
        arb.dequeue();
        arb.dequeue();
        arb.dequeue();
        arb.dequeue();
    }
    @Test(expected = RuntimeException.class)
    public void testEnqueueException() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer(10);
        arb.enqueue(1);
        arb.enqueue(2);
        arb.enqueue(3);
        arb.enqueue(4);
        arb.enqueue(5);
        arb.enqueue(6);
        arb.enqueue(7);
        arb.enqueue(8);
        arb.enqueue(9);
        arb.enqueue(10);
        arb.enqueue(10);

    }
}