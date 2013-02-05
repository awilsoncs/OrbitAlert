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
        switch (action){
            case "exit": System.exit(1);
            case "quit": System.exit(1);
            case "north": walk("north");
            case "east": walk("east");
            case "west": walk("west");
            case "south": walk("south");
            case "up": walk("up");
            case "down": walk("down");
        }
        if(action.equals("exit") || action.equals("quit")){
            System.exit(1);
        }
    }
}
