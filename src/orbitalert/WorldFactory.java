/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package orbitalert;

import java.util.ArrayList;
import orbitalert.Areas.Room;
import orbitalert.Objects.Mobs.Player;
import orbitalert.Objects.Obj;

/**
 *
 * @author Aaron
 */
public class WorldFactory {
    public static World getWorld(
            Cell mapSize,
            int roomCount
            ){
        
        int startX = (int) (Math.random() * mapSize.getX());
        int startY = (int) (Math.random() * mapSize.getY());
        int startZ = (int) (Math.random() * mapSize.getZ());
        Cell startCell = new Cell(startX, startY, startZ);
        
        //Build the map
        MapFactory mapFactory = new MapFactory();
        Map map = mapFactory.getMap(mapSize,
                startCell,
                roomCount
                );
        
        //Build the player mob.
        Player player = new Player();
        map.getRoom(startCell).add(player);
        
        //Gather a list of Objs
        ArrayList<Obj> objList = new ArrayList<>();
        for(int x = 0; x < mapSize.getX(); x++){
            for(int y = 0; y < mapSize.getY(); y++){
                for(int z = 0; z < mapSize.getZ(); z++){
                    Room checkRoom = map.getRoom(new Cell(x,y,z));
                    if (checkRoom != null){
                        objList.addAll(checkRoom.getContents());
                    }
                }
            }
        }
        
        World world = new World(
                map,
                objList,
                new Cell(4, 4, 4),
                new Cell(startX, startY, startZ),
                32,
                player
                );
        
        return world;
    }
}
