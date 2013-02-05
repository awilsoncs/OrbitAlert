package orbitalert.Areas;

import java.util.ArrayList;
import java.util.HashMap;
import orbitalert.World;

/**
 *
 * @author Aaron
 */
public class Area {
    private String areaName;
    private String areaDescription;
    private String type;
    private int areaDifficulty;
    private ArrayList<String> roomTypes;
    private ArrayList<Room> areaRooms;
    private ArrayList<Room> areaDamagedRooms;
    private World world;
    //private ArrayList<Mob> areaMobs;

    public Area(){
        roomTypes = new ArrayList<>();
        areaRooms = new ArrayList<>();
        areaDamagedRooms = new ArrayList<>();
    }
    
    public Area(HashMap<String, String> areaAttributes){
        setName(areaAttributes.get("name"));
        setDescription(areaAttributes.get("description"));
        setType(areaAttributes.get("type"));
        roomTypes = new ArrayList<>();
        areaRooms = new ArrayList<>();
        areaDamagedRooms = new ArrayList<>();
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }
    
    public void setType(String newType){
        type = newType;
    }
    
    public String getType(){
        return type;
    }
    
    private int getDifficulty() {
        return areaDifficulty;
    }

    public void setName(String name) {
        areaName = name;
    }
    public String getName() {
        return areaName;
    }
    public void setDescription(String description){
        areaDescription = description;
    }
    public String getDescription() {
        return areaDescription;
    }
    public void addRoom(Room room) {
        areaRooms.add(room);
    }
    public ArrayList<Room> getRooms() {
        return areaRooms;
    }
    
    public ArrayList<Room> getDamagedRooms() {
        return areaDamagedRooms;
    }

   // public ArrayList<Mob> getMobs() {
   //     return this.areaMobs;
   // }
    
    public Room makeRoom(){
        Room newRoom = RoomLoader.loadRoom(type);
        addRoom(newRoom);
        newRoom.setWorld(world);
        return newRoom;
    };
}
