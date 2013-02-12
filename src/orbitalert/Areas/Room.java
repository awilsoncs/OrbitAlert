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

    private String name;
    private String areaType;
    private String description;
    private ArrayList<Obj> contents;
    private HashMap<String, Exit> exitMap;
    //HashMap exit list

    public Room(
            HashMap<String, String> roomAttributes,
            String parentArea,
            ArrayList<Obj> newContents,
            HashMap<String, Exit> newExitMap
            ) {
        name = roomAttributes.get("name");
        description = roomAttributes.get("description");
        areaType = parentArea;
        exitMap = newExitMap;
        contents = newContents;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAreaType() {
        return areaType;
    }

    public void setAreaType(String areaType) {
        this.areaType = areaType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public HashMap<String, Exit> getExitMap() {
        return exitMap;
    }

    public void setExitMap(HashMap<String, Exit> exitMap) {
        this.exitMap = exitMap;
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
