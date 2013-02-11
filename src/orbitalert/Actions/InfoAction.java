package orbitalert.Actions;

import java.util.ArrayList;
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
public class InfoAction implements Action {
    String infoLoc;
    
    public InfoAction(String infoLoc){
        setInfoLoc(infoLoc);
    }
    @Override
    public boolean execute(Mob usr){
        if(usr.getClass() == Player.class){
            Player player = (Player) usr;
        } else {
            //This should not be called by a non-player.
            return false;
        }
        //Find the right obj list and set it to contents.
        ArrayList<Obj> contents = null;
        switch (getInfoLoc()) {
            case "here":
                //The user was looking for info about the current room.
                Container loc = usr.getLoc();
                //Verify that we're actually in a room, then cast.
                if (loc.getClass() == Room.class){
                    Room locRoom = (Room) loc;
                    contents = locRoom.getContents();
                }
                break;
            case "inv":
                contents = usr.getContents();
                break;
            case "inventory":
                contents = usr.getContents();
                break;
        }
        
        //Iterate through the list and build output.
        String output = "[Item]         [ID]\n";
        if (contents != null){
            for(Obj obj:contents){
                if(obj.isGetable() || obj.isUseable()){
                    output += obj.getName();
                    output += "         ";
                    output += String.valueOf(obj.hashCode());
                    output += "\n";
                }
            }
        }
        
        //Send it to the player.
        GameHelper.output(output); 
        return true;
    }

    public String getInfoLoc() {
        return infoLoc;
    }

    public void setInfoLoc(String infoLoc) {
        this.infoLoc = infoLoc;
    }    
}
