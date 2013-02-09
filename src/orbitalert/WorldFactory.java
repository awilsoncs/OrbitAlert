/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package orbitalert;

import java.util.ArrayList;
import orbitalert.Areas.Area;
import orbitalert.Objects.Mobs.Player;
import orbitalert.Objects.Obj;

/**
 *
 * @author Aaron
 */
public class WorldFactory {
    public static World getWorld(){
        
        int startX = (int) (Math.random() * 4);
        int startY = (int) (Math.random() * 4);
        int startZ = (int) (Math.random() * 4);
        
        World world = new World(
                new ArrayList<Area>(),
                new ArrayList<Obj>(),
                new Cell(startX, startY, startZ),
                new Player()
                );
        return world;
    }
}
