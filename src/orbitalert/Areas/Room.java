/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package orbitalert.Areas;

import java.util.ArrayList;
import java.util.HashMap;
import orbitalert.Objects.Container;
import orbitalert.Objects.Items.Item;
import orbitalert.Objects.Items.ItemLoader;
import orbitalert.Objects.Obj;
import orbitalert.Task;
import orbitalert.World;

/**
 *
 * @author Aaron
 */
public class Room implements Container {

    private String roomName;
    private String parentAreaType;
    private String roomDescription;
    private ArrayList<Obj> contents;
    private HashMap<String, Exit> exitMap;
    private World world;
    //HashMap exit list

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public Room(HashMap<String, String> roomAttributes, String parentArea) {
        setName(roomAttributes.get("name"));
        setDescription(roomAttributes.get("description"));
        setParentAreaType(parentArea);
        exitMap = new HashMap<>();
        contents = new ArrayList<>();
        
        //Generate Items
        
        int numberOfItems = (int) ((Math.random() * 3) * Math.random() * 2);
        for (;numberOfItems > 0; numberOfItems--){
            Item newItem = ItemLoader.loadItem(getParentAreaType());
            add(newItem);
            newItem.setWorld(world);
        }
    }

    public void setParentAreaType(String type){
        parentAreaType = type;
    }
    
    public String getParentAreaType(){
        return parentAreaType;
    }
    
    public void setName(String name) {
        roomName = name;
    }

    public String getName() {
        return roomName;
    }

    public void setDescription(String newDesc) {
        roomDescription = newDesc;
    }

    public String getDescription() {
        return roomDescription;
    }

    @Override
    public boolean canHold(Obj obj) {
        return true;
    }

    @Override
    public boolean add(Obj obj) {
        contents.add(obj);
        obj.setLoc(this);
        return true;
    }

    @Override
    public boolean remove(Obj obj) {
        if (contents.contains(obj)) {
            contents.remove(obj);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public ArrayList<Obj> getContents() {
        return contents;
    }

    public void addExit(String direction) {
        if (exitMap.containsKey(direction) == false) {
            exitMap.put(direction, new Exit());
        }
    }

    public void addExit(String direction, Task newTask) {
        System.out.println("Addint Exit to: ");
        System.out.println(getName());
        System.out.println(direction);
        if (exitMap.containsKey(direction) == false) {
            exitMap.put(direction, new Exit(newTask));
        }
    }
    
    public Exit getExit(String direction){
        if (exitMap.containsKey(direction)){
            return exitMap.get(direction);
        } else {
            return null;
        }
    }

    public String getSummary() {
        String output = "";
        output += this.getName() + "\n";
        output += this.getDescription();
        for (Obj object:getContents()){
            String objectShortDescription = object.getShortDescription();
            if (objectShortDescription != null) {
                output += " " + object.getShortDescription();
            }
        }
        
        output += " You see exits in the following directions: ";
        for(String direction:exitMap.keySet()){
            output += direction + ", ";
        }
        return output;
    }
}
