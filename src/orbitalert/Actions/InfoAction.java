package orbitalert.Actions;

import java.util.ArrayList;
import java.util.List;
import orbitalert.Areas.Room;
import orbitalert.GameHelper;
import orbitalert.Objects.Container;
import orbitalert.Objects.Mobs.Mob;
import orbitalert.Objects.Mobs.Player;
import orbitalert.Objects.Obj;

/**
 *
 * @author Aaron
 */
public class InfoAction extends SearchingAction {
    
    public InfoAction(String target){
        setTarget(target);
    }
    
    public InfoAction(List<String> parsedString){
        parseSearch(parsedString);
    }
    
    @Override
    public boolean execute(Mob usr){
        //Security check, make sure the caller is a player.
        if(usr.getClass() == Player.class){
            Player player = (Player) usr;
        } else {
            //This should not be called by a non-player.
            return false;
        }
        
        //Assume the player is looking for an item in this room.
        Container loc = usr.getLoc();
        //There are several exceptions for info that may not be items.
        if(getTarget() != null){
            Obj obj;
            switch (getTarget()){
                case "here":
                    //The user is most likely looking for information about
                    //the current room.
                    loc = usr.getLoc();
                    GameHelper.output(buildInfoAll(loc));
                    return true;
                    
                case "inv":
                    //The user is looking for information about his or her
                    //inventory.
                    loc = usr;
                    GameHelper.output(buildInfoAll(loc));
                    return true;
                    
                case "inventory":
                    //Same as above
                    loc = usr;
                    GameHelper.output(buildInfoAll(loc));
                    return true;
                    
                case "room":
                    //Same as "here" for now.
                    loc = usr.getLoc();
                    GameHelper.output(buildInfoAll(loc));
                    return true;
            }

            if (getTargetContainer() != null){
                //Check to make sure the player wasn't searching by
                //an exception case. If the search was in "here" or "room",
                //we ignore it, this is already set to be the case.
                switch(getTargetContainer()){
                    case "inv":
                        loc = usr;
                        break;
                    case "inventory":
                        loc = usr;
                        break;
                    case "here":
                        break;
                    case "room":
                        break;
                    default:
                        Obj containerObj = search(getTargetContainer(), loc);
                        if (containerObj != null && containerObj.isContainer()){
                            loc = containerObj;
                        }
                }
            }
            
            //Now, we search for the obj in the loc;
            
            obj = search(getTarget(), loc);
            if(obj != null){
                GameHelper.output(buildInfoOf(obj));
                return true;
            } else {
                return false;
            }
        }
        return false;
    }
    
    protected String buildInfoOf(Obj obj){
        String output = "\n";
        output += obj.getName() + "\n";
        if (obj.getLongDescription() != null){
            output += obj.getLongDescription() + "\n";
        }
        return output;
    }
    
    protected String buildInfoAll(Container loc){
        List<Obj> contents = loc.getContents();
        String output = "[Item]         [ID]\n";
        if(contents != null){
            for(Obj obj:contents){
                if(obj.isGetable() || obj.isUseable()){
                    output += obj.getName();
                    output += "         ";
                    output += String.valueOf(obj.hashCode());
                    output += "\n";
                }
            }
        }
        return output;
    }
}
