package orbitalert;

import java.io.*;
import orbitalert.Objects.Obj;
import org.apache.commons.lang3.text.WordUtils;

/**
 *
 * @author Aaron
 */
public class GameHelper {
    static int DEFAULT_WORD_WRAP = 80;
    /**
     *
     * @param world
     */
    public static void startGame(World world){
        boolean playGame = true;
        while(playGame){
            for (Obj obj:world.getObjs()){
                obj.tick();
            }
        }
    }
    /**
     *
     * @param output
     */
    public static void output(String output) {
        System.out.println(WordUtils.wrap(output, DEFAULT_WORD_WRAP));
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

    /**
     *
     * @param string
     * @return
     */
    public static String input(String string) {
        System.out.print(string);
        return input();
    }
}
