/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package orbitalert.Actions;

import java.util.List;
import orbitalert.Objects.Container;
import orbitalert.Objects.Items.Item;
import orbitalert.Objects.Mobs.Mob;
import orbitalert.Objects.Obj;

/**
 *
 * @author Aaron
 */
public class GetAction implements Action {
    String target;
    String targetContainer;
    
    public GetAction(List<String> parsedString){
        
        //If "from" is present in parsedString, we need to know what index.
        int fromIndex = -1;
        for (int i = 0; i < parsedString.size(); i++){
            if(buildTarget(parsedString.get(i)).equals("from")){
                fromIndex = i;
                break;
            }
        }
        //This means we found "from" and it's not at the end.
        if (fromIndex > 0 && fromIndex < parsedString.size()){
            //Split parsedString into two strings, ignoring FROM
            List<String> beforeFrom = parsedString
                    .subList(0, fromIndex);
            List<String> afterFrom = parsedString
                    .subList(fromIndex + 1, parsedString.size());
            setTarget(buildTarget(beforeFrom));
            setTargetContainer(buildTarget(afterFrom));
        } else {
            //Doesn't seem to be a well-formed "from", so build the target.
            setTarget(buildTarget(parsedString));
        }
    }
    
    private String buildTarget(String target){
        return target.toLowerCase().replaceAll("\\s", "");
    }
    
    private String buildTarget(List<String> target){
        String output = "";
        //Put the output together.
        for(String string:target){
            output += string;
        }
        if (output != null){
            //Strip any whitespace from it and make it lowercase.
            output = output.toLowerCase().replaceAll("\\s", "");
        } else {
            return null;
        }
        return output;
    }
    
    private Obj search(String name, Container loc){
        if(loc.getContents() != null){
            Obj obj = searchByName(name, loc);
                //Check to make sure the player didn't search by hashcode.
                if (obj == null){
                    obj = searchByHash(name, loc);
                }
            return obj;
        }
        return null;
    }
    
    private Obj searchByName(String name, Container loc){
        List<Obj> contents = loc.getContents();
        System.out.println("Searching " + loc.toString() + " for " + name);
        System.out.println(loc.toString() + " contents: " + contents.toString());
        for(Obj obj:contents){
            String objName = buildTarget(obj.getName());
            if(objName.equals(name)){
                return obj;
            }
        }
        return null;
    }
    
    private Obj searchByHash(String name, Container loc){
        List<Obj> contents = loc.getContents();
        for(Obj obj:contents){
            String objHash = String.valueOf(obj.hashCode());
            if(objHash.equals(name)){
                return obj;
            }
        }
        return null;
    }
    
    @Override
    public boolean execute(Mob usr) {
        Container loc = usr.getLoc();
        //Search the player's loc for the item's container, if we need to.
        if(targetContainer != null){
            Obj container = search(targetContainer, loc);
            if (container != null && container.isContainer()){
                loc = (Container) container;
            }
        }
        
        //Regardless of whether we found a new container,
        //search for the item, same as above.
        System.out.println("Searching for: " + target);
        Obj obj = search(target, loc);
        if (obj != null && obj.isGetable()){
            System.out.println("Found: " + obj.getName());
            if(obj.getClass() == Item.class){
                Item item = (Item) obj;
                usr.get(item);
                return true;
            }
        }
        return false;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getTargetContainer() {
        return targetContainer;
    }

    public void setTargetContainer(String targetContainer) {
        this.targetContainer = targetContainer;
    }
    
}
