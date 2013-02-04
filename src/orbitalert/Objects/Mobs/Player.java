/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package orbitalert.Objects.Mobs;

import orbitalert.Areas.Room;
import orbitalert.GameHelper;

/**
 *
 * @author Aaron
 */
public class Player extends Mob {
    
    public Player(){
        setName("Player");
        setShortDescription("There is a player here");
    }
    /**
     *
     */
    @Override
    public void behavior(){
        Room location = (Room) getLoc();
        GameHelper.output(location.getSummary());
        GameHelper.output("\nWhat do you do?");
        String action = GameHelper.input();
        if(action.equals("exit") || action.equals("quit")){
            System.exit(1);
        }
    }
}
