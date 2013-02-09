package orbitalert;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import orbitalert.Areas.Room;
import orbitalert.Objects.Obj;

/**
 *
 * @author Aaron
 */
public class OrbitAlert {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        for (String arg : args) {
            if(arg.equals("play")){
                World newWorld = WorldFactory.getWorld();
                GameHelper.startGame(newWorld);
            }
            if(arg.equals("worldtest")){
                OrbitAlert game = new OrbitAlert();
                game.worldTest();
            }
            if(arg.equals("areatest")){
                OrbitAlert game = new OrbitAlert();
                game.areaTest();
            }
            if(arg.equals("itemtest")){
                OrbitAlert game = new OrbitAlert();
                game.itemTest();
            }
            if(arg.equals("gamehelpertest")){
                gameHelperTest();
            }
        }        
    }
    
    private void areaTest() {
       orbitalert.Areas.AreaTester areaTest = new orbitalert.Areas.AreaTester();
       areaTest.testAreas();
    }
    
    private void itemTest() {
        World newWorld = WorldFactory.getWorld();
        for (Obj obj : newWorld.getObjs()){
            GameHelper.output(obj.getName());
        }
    }
    
    private void worldTest() {
        GameHelper.output("Start worldTest");
        World newWorld = WorldFactory.getWorld();
        Map worldMap = newWorld.getMap();
        Cell dimensions = worldMap.getDimensions();
        int maxX = dimensions.getX();
        int maxY = dimensions.getY();
        int maxZ = dimensions.getZ();
        for (int i = 0; i <= maxX; i++){
            for(int j = 0; j <= maxY; j++){
                for (int k = 0; k <= maxZ; k++){
                    String loc = "Cell: ";
                    loc += Integer.toString(i) + ", ";
                    loc += Integer.toString(j) + ", ";
                    loc += Integer.toString(k);
                    GameHelper.output(loc);
                    Cell checkCell = new Cell(i,j,k);
                    Room checkRoom = worldMap.getRoom(checkCell);
                    if (checkRoom != null){
                        String output = checkRoom.getSummary();
                        GameHelper.output(output);
                    } else {
                        GameHelper.output("Null Cell");
                    }
                }
            }
        }
    }
    
    private static void gameHelperTest() {
        GameHelper.output("GameHelper test");
        String input = GameHelper.input();
        GameHelper.output("Input: " + input);
        GameHelper.input("Next input: ");
    }
    
    public static String getOrbitAlertPath(){
        String path = Cell.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        String decodedPath = null;
        
        try {
            decodedPath = URLDecoder.decode(path, "UTF-8");
        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
        }
                
        File areasFile = new File(decodedPath);
        areasFile = new File(areasFile.getParentFile().getPath());
        return areasFile.getPath();
    }
}
