package byog.lab5;
import java.util.Random;
public class testR {
    public static void main(String[] args) {
        /*pseudo random generation and seeds
        只要seed一样，每次产生的sequence就一样
         */
        Random random = new Random(30);

        for (int i = 0; i < 5; i++) {
            // bound决定了每次的数都在0-bound之间
           System.out.println(random.nextInt(5));
        }

    }
}
