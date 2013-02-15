package orbitalert.Areas;

import java.util.ArrayList;
import java.util.HashMap;
import orbitalert.World;

/**
 *
 * @author Aaron
 */
public class Area {
    private String name;
    private String description;
    private String type;
    private int areaDifficulty;
    private ArrayList<Room> areaRooms;
    private ArrayList<Room> areaDamagedRooms;
    private World world;
    //private ArrayList<Mob> areaMobs;

    public Area(){
        areaRooms = new ArrayList<>();
        areaDamagedRooms = new ArrayList<>();
    }
    
    public Area(HashMap<String, String> areaAttributes){
        name = areaAttributes.get("name");
        description = areaAttributes.get("description");
        type =  areaAttributes.get("type");
        areaRooms = new ArrayList<>();
        areaDamagedRooms = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAreaDifficulty() {
        return areaDifficulty;
    }

    public void setAreaDifficulty(int areaDifficulty) {
        this.areaDifficulty = areaDifficulty;
    }

    public ArrayList<Room> getAreaRooms() {
        return areaRooms;
    }

    public void setAreaRooms(ArrayList<Room> areaRooms) {
        this.areaRooms = areaRooms;
    }

    public ArrayList<Room> getAreaDamagedRooms() {
        return areaDamagedRooms;
    }

    public void setAreaDamagedRooms(ArrayList<Room> areaDamagedRooms) {
        this.areaDamagedRooms = areaDamagedRooms;
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }
    
    public Room makeRoom(){
        RoomFactory roomFactory = RoomFactory.getRoomFactory();
        Room newRoom = roomFactory.getRoom(type);
        
        ArrayList<Room> rooms = getAreaRooms();
        rooms.add(newRoom);
        setAreaRooms(rooms);
        return newRoom;
    };
    
    public Room makeRoom(String roomType){
        RoomFactory roomFactory = RoomFactory.getRoomFactory();
        Room newRoom = roomFactory.getRoom(type, roomType);
        
        ArrayList<Room> rooms = getAreaRooms();
        rooms.add(newRoom);
        setAreaRooms(rooms);
        return newRoom;
    }
}
