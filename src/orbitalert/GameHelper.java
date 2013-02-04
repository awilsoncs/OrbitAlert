package orbitalert;

import java.io.*;
import org.apache.commons.lang3.text.WordUtils;

/**
 *
 * @author Aaron
 */
public class GameHelper {

    /**
     *
     * @param output
     */
    public static void output(String output) {
        System.out.println(WordUtils.wrap(output, 80));
    }

    /**
     *
     * @return
     */
    public static String input() {
        BufferedReader br = new BufferedReader(
                new InputStreamReader(System.in));
        String input = null;

        try {
            input = br.readLine();
            return input;
        } catch (IOException ioe) {
            System.out.println("IO Ex trying to read input.");
            System.exit(1);
        }
        return input;
    }

    public static String input(String string) {
        System.out.print(string);
        return input();
    }
}
