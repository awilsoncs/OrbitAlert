/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package orbitalert;

import orbitalert.Areas.Room;

/**
 *
 * @author Aaron
 */
public class MapFactory {
    public static Map getMap(Cell mapSize){
        int x = mapSize.getX();
        int y = mapSize.getY();
        int z = mapSize.getZ();
        Map newMap = new Map(new Room[x][y][z]);
        return newMap;
    }
}
