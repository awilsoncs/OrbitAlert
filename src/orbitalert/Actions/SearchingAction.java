package orbitalert.Actions;

import java.util.List;
import orbitalert.Objects.Container;
import orbitalert.Objects.Mobs.Mob;
import orbitalert.Objects.Obj;

/**
 *
 * @author Aaron
 */
public abstract class SearchingAction implements Action {
    private String target;
    private String targetContainer;
    
    protected String buildTarget(String target) {
        return target.toLowerCase().replaceAll("\\s", "");
    }

    protected String buildTarget(List<String> target) {
        String output = "";
        //Put the output together.
        for (String string : target) {
            output += string;
        }
        if (output != null) {
            //Strip any whitespace from it and make it lowercase.
            output = output.toLowerCase().replaceAll("\\s", "");
        } else {
            return null;
        }
        return output;
    }

    @Override
    public abstract boolean execute(Mob usr);

    public String getTarget() {
        return target;
    }

    public String getTargetContainer() {
        return targetContainer;
    }

    protected final void parseSearch(List<String> parsedString){
        int fromIndex = -1;
        for (int i = 0; i < parsedString.size(); i++){
            String testString = buildTarget(parsedString.get(i));
            if(testString.equals("from") || testString.equals("in")){
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
    
    protected Obj search(String name, Container loc) {
        if (loc.getContents() != null) {
            Obj obj = searchByName(name, loc);
            //Check to make sure the player didn't search by hashcode.
            if (obj == null) {
                obj = searchByHash(name, loc);
            }
            return obj;
        }
        return null;
    }

    protected Obj searchByHash(String name, Container loc) {
        List<Obj> contents = loc.getContents();
        for (Obj obj : contents) {
            String objHash = String.valueOf(obj.hashCode());
            if (objHash.equals(name)) {
                return obj;
            }
        }
        return null;
    }

    protected Obj searchByName(String name, Container loc) {
        List<Obj> contents = loc.getContents();
        for (Obj obj : contents) {
            String objName = buildTarget(obj.getName());
            if (objName != null && objName.indexOf(name) >= 0) {
                return obj;
            }
        }
        return null;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public void setTargetContainer(String targetContainer) {
        this.targetContainer = targetContainer;
    }
    
}
