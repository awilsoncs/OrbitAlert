package orbitalert;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import orbitalert.Areas.Room;
import orbitalert.Areas.RoomFactory;
import orbitalert.Objects.Items.Item;
import orbitalert.Objects.Items.ItemFactory;
import orbitalert.Objects.Obj;

/**
 *
 * @author Aaron
 */
public class OrbitAlert {
    
    static {
        try {
            Class.forName("orbitalert.Actions.DropAction");
            Class.forName("orbitalert.Actions.GetAction");
            Class.forName("orbitalert.Actions.InfoAction");
            Class.forName("orbitalert.Actions.WalkAction");
            Class.forName("orbitalert.Actions.QuitAction");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(OrbitAlert.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        World newWorld = WorldFactory.getWorld(
                new Cell(4,4,4));
        GameHelper.startGame(newWorld);
    }
//        for (String arg : args) {
//            if(arg.equals("play")){
//                World newWorld = WorldFactory.getWorld(
//                    new Cell(4,4,4));
//                GameHelper.startGame(newWorld);
//            }
//            if(arg.equals("worldtest")){
//                OrbitAlert game = new OrbitAlert();
//                game.worldTest();
//            }
//            if(arg.equals("areatest")){
//                OrbitAlert game = new OrbitAlert();
//                game.areaTest();
//            }
//            if(arg.equals("itemtest")){
//                OrbitAlert game = new OrbitAlert();
//                game.itemTest();
//            }
//            if(arg.equals("gamehelpertest")){
//                gameHelperTest();
//            }
//            if(arg.equals("maptest")){
//                mapTest();
//            }
//            if(arg.equals("containeritemtest")){
//                containerItemTest();
//            }
//        }        
//    }
    private static void mapTest() {
        Cell cell1 = new Cell(1,1,1);
        Cell cell2 = new Cell(1,1,1);
        GameHelper.output(String.valueOf(cell1.hashCode()));
        GameHelper.output(String.valueOf(cell2.hashCode()));
        GameHelper.output(String.valueOf(cell1.equals(cell2)));
        
        Map map = new Map(new Cell(1,1,1));
        
        RoomFactory roomFactory = RoomFactory.getRoomFactory();
        Room room1 = roomFactory.getRoom("medical", "surgery");
        Room room2 = roomFactory.getRoom("medical", "hallway1");
        
        map.addRoom(cell1, room1);
        GameHelper.output(map.getRoom(cell1).getName());
        map.addRoom(cell2, room2);
        GameHelper.output(map.getRoom(cell1).getName());
        GameHelper.output(map.getRoom(cell2).getName());
    }
    
    private void areaTest() {
       orbitalert.Areas.AreaTester areaTest = new orbitalert.Areas.AreaTester();
       areaTest.testAreas();
    }
    
    private void itemTest() {
        World newWorld = WorldFactory.getWorld(
                new Cell(4,4,4));
        for (Obj obj : newWorld.getObjs()){
            GameHelper.output(obj.getName());
        }
    }
    
    private static void containerItemTest(){
        ItemFactory itemFactory = ItemFactory.getItemFactory();
        Item item = itemFactory.getItem("medical", "dufflebag");
        ArrayList<Obj> contents = item.getContents();
        for(Obj obj:contents){
            System.out.println(obj.getName());
        }
    }
    
    private void worldTest() {
        GameHelper.output("Start worldTest");
        World newWorld = WorldFactory.getWorld(
                new Cell(4,4,4));
        Map worldMap = newWorld.getMap();
        Cell dimensions = worldMap.getDimensions();
        int maxX = dimensions.getX();
        int maxY = dimensions.getY();
        int maxZ = dimensions.getZ();
        for (int i = 0; i <= maxX; i++){
            for(int j = 0; j <= maxY; j++){
                for (int k = 0; k <= maxZ; k++){

                    Cell checkCell = new Cell(i,j,k);
                    Room checkRoom = worldMap.getRoom(checkCell);
                    String loc = "Cell: ";
                    loc += Integer.toString(i) + ", ";
                    loc += Integer.toString(j) + ", ";
                    loc += Integer.toString(k) + ": ";
                    if (checkRoom != null){
                        GameHelper.output(loc + checkRoom.getName());
                        
                    } else {
                        GameHelper.output(loc + "Null Cell");
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
        }
                
        File areasFile = new File(decodedPath);
        areasFile = new File(areasFile.getParentFile().getPath());
        return areasFile.getPath();
    }
}
