package orbitalert;

import java.util.ArrayList;
import orbitalert.Areas.Area;
import orbitalert.Areas.AreaLoader;
import orbitalert.Areas.Room;

/**
 *
 * @author Aaron
 */
public class MapFactory {
    private static MapFactory mapFactory;
    static int MAXIMUM_ROOMS_TOTAL;
    static int MINIMUM_ROOMS_PER_AREA;
    static int NEW_AREA_CHANCE;
    static int PATH_DEPTH_ROOMS;
    static int TRAVERSAL_CHANCE;
    
    
    private MapFactory(){
        Config config = Config.getConfig();
        MAXIMUM_ROOMS_TOTAL = config.getMaximumRoomsTotal();
        MINIMUM_ROOMS_PER_AREA = config.getMinimumRoomsPerArea();
        NEW_AREA_CHANCE = config.getNewAreaChance();
        PATH_DEPTH_ROOMS = config.getMaxRoomsPerPath();
        TRAVERSAL_CHANCE = config.getTraversalChance();
    }
    
    public static MapFactory getMapFactory(){
        if (mapFactory == null){
            mapFactory = new MapFactory();
        }
        return mapFactory;
    }
    
    /**
     *
     * @param mapSize
     * @param startCell
     * @param roomCount
     * @return
     */
    public Map getMap(
            Cell mapSize,
            Cell startCell){
        
        //Build a blank map.
        Map newMap = new Map(mapSize);

        //Load and create a new MedBay area.
        Area startArea = AreaLoader.loadArea("medical");
        newMap.addRoom(startCell, startArea.makeRoom("main"));
        
        //Start a recursive build on newMap.
        newMap = buildMap(newMap,
                startCell,
                startCell,
                startArea,
                PATH_DEPTH_ROOMS);
        
        return newMap;
    }
    
    private Map buildMap(
            Map map,
            Cell currentCell,
            Cell previousCell,
            Area area,
            int depthCount
            ){
        
        Room newRoom;
        
        //Random check to see if a new Area should be started.
        int roll = (int) (Math.random() * 100);
        if (area.getAreaRooms().size() >= MINIMUM_ROOMS_PER_AREA
                && roll <= NEW_AREA_CHANCE) {
            //Start a new area.
            Area oldArea = area;
            area = AreaLoader.loadArea();
            
            //Build this cell as a new room in the new area.
            newRoom = area.makeRoom("main");
            map.addRoom(currentCell, newRoom);
            
            //Create a new task, and then link the rooms together, locked by
            //the newTask.
            Task newTask = TaskBuilder.buildTask(oldArea);
            map.linkRooms(previousCell, currentCell, newTask);
            
        } else {
            //Keep working in the present area for now.
            newRoom = area.makeRoom();
            map.addRoom(currentCell, newRoom);
            map.linkRooms(previousCell, currentCell);
        }
        
        //The current cell is build, so we recurse on its neighbors
        //in a random order.
        ArrayList<Cell> neighbors = map.getNeighbors(currentCell);
        while (neighbors.size() > 0 
                && MAXIMUM_ROOMS_TOTAL > 0 
                && depthCount > 0) {
            
            //Pop a random neighbor.
            int popIndex = (int) (Math.random() * neighbors.size());
            Cell nextCell = neighbors.get(popIndex);
            neighbors.remove(nextCell);
            
            int random = (int) (Math.random() * 100);
            if (random <= TRAVERSAL_CHANCE
                    && map.getRoom(nextCell) == null) {
                MAXIMUM_ROOMS_TOTAL--;
                depthCount--;
                buildMap(map, nextCell, currentCell, area, depthCount);
            }
        }
        
        //When we run out of neighbors, or have enough rooms, return the map.
        return map;
    }
}
