package orbitalert;
import java.util.ArrayList;
import orbitalert.Areas.Area;
import orbitalert.Areas.AreaLoader;
import orbitalert.Areas.Room;
import orbitalert.Objects.Mobs.Player;
import orbitalert.Objects.Obj;

/**
 *
 * @author Aaron
 */
public class World {
    private Map map;
    private ArrayList<Area> areas;
    private ArrayList<Obj> objs;
    private int orbitDecay;

    //Setting attributes
    private int randomSeed; //Not implemented.
    private Cell mapSize = new Cell(4,4,4);
    private Cell traversal; //Not implemented.
    private int roomCount = 32;
    private int[] areaCount;
    private int[] taskDifficulty;
    private int[] roamingMonsters;

    public World() {

        //Build the world here.
        map = makeMap(mapSize);
        areas = new ArrayList<>();
        objs = new ArrayList<>();

        //Pick a starting cell.
        Cell mapDimensions = map.getDimensions();
        int startX = (int) (Math.random() * mapDimensions.getX());
        int startY = (int) (Math.random() * mapDimensions.getY());
        int startZ = (int) (Math.random() * mapDimensions.getZ());
        Cell startCell = new Cell(startX, startY, startZ);
        
        //create a new MedBay area.
        Area newArea = AreaLoader.loadArea("medical");
        this.areas.add(newArea);
        
        //pass in currentCell, currentCell, and the area into this.buildWorld.
        buildWorld(startCell, startCell, newArea);
        
        map.getRoom(startCell).add(new Player());
        for (Area area:areas){
            for (Room room: area.getRooms()){
                for (Obj obj: room.getContents()){
                    addObj(obj);
                }
            }
        }
    }

    private void buildWorld(Cell currentCell, Cell previousCell, Area area){
        Room newRoom;
        int roll = (int) (Math.random()*4);
        //GameHelper.output(Integer.toString(roll));
        if (area.getRooms().size() > 3 && roll == 3) {
            //Locking the area down.
            Area oldArea = area;
            area = AreaLoader.loadArea();
            areas.add(area);
            newRoom = area.makeRoom();
            map.addRoom(currentCell, newRoom);
            Task newTask = TaskBuilder.buildTask(oldArea);
            map.linkRooms(previousCell, currentCell, newTask);
            //This should be linked directly to a door, not a room;
        } else {
            newRoom = area.makeRoom();
            map.addRoom(currentCell, newRoom);
            map.linkRooms(previousCell, currentCell);
        }
        //Get the next neighbor to build.
        ArrayList<Cell> neighbors = map.getNeighbors(currentCell);
        while (neighbors.size() > 0 && roomCount > 0) {
            //Pop a random neighbor.
            int popIndex = (int) Math.random() * neighbors.size();
            Cell nextCell = neighbors.get(popIndex);
            neighbors.remove(nextCell);

            if (map.getRoom(nextCell) == null) {
                roomCount--;
                buildWorld(nextCell, currentCell, area);
            }
        }
    }

    //Makers here.
    private Map makeMap(Cell mapSize) {
        Map newMap = new Map(mapSize);
        return newMap;
    }

    //Getters here.
    public Map getMap(){
        return this.map;
    }
    public ArrayList<Area> getAreas(){
        return this.areas;
    }
    public ArrayList<Obj> getObjs(){
        return this.objs;
    }
    public void addObj(Obj obj){
        objs.add(obj);
    }
}
