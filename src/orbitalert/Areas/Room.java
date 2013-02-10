/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package orbitalert.Areas;

import java.util.ArrayList;
import java.util.HashMap;
import orbitalert.Objects.Container;
import orbitalert.Objects.Obj;
import orbitalert.Task;

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
    //HashMap exit list

    public Room(
            HashMap<String, String> roomAttributes,
            String parentArea,
            ArrayList<Obj> newContents,
            HashMap<String, Exit> newExitMap
            ) {
        setName(roomAttributes.get("name"));
        setDescription(roomAttributes.get("description"));
        setParentAreaType(parentArea);
        exitMap = newExitMap;
        contents = newContents;
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
