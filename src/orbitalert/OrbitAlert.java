/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package orbitalert;

import orbitalert.Areas.Area;
import orbitalert.Areas.Room;
import orbitalert.Areas.AreaLoader;

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
        }        
    }
    
    private void areaTest() {
       orbitalert.Areas.AreaTester areaTest = new orbitalert.Areas.AreaTester();
       areaTest.testAreas();
    }
    
    private void itemTest() {
        orbitalert.Objects.Items.ItemTester itemTest = new orbitalert.Objects.Items.ItemTester();
        itemTest.testItems();
    }
    
    private void worldTest() {
        System.out.println("Start worldTest");
        World newWorld = new World();
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
                    System.out.println(loc);
                    Cell checkCell = new Cell(i,j,k);
                    Room checkRoom = worldMap.getRoom(checkCell);
                    if (checkRoom != null){
                        String output = checkRoom.getSummary();
                        System.out.println(output);
                    } else {
                        System.out.println("Null Cell");
                    }
                }
            }
        }
    }
}
