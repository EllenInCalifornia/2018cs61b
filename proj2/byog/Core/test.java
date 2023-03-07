package byog.Core;
import java.util.Random;
public class test {
    public static void main(String[] args) {
        Random r = new Random(2);
        int n = RandomUtils.uniform(r, 9);
        System.out.println(RandomUtils.uniform(r, 9));
        System.out.println(RandomUtils.uniform(r, 9));
        System.out.println(RandomUtils.uniform(r, 9));
        System.out.println(RandomUtils.uniform(r, 9));
    }
}
