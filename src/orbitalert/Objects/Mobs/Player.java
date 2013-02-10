/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package orbitalert.Objects.Mobs;

import orbitalert.Areas.Room;
import orbitalert.Cell;
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
        Cell locCell = getWorld().getMap().getCell(location);
        GameHelper.output(location.getSummary());
        GameHelper.output(locCell.getSummary());
        GameHelper.output("\nWhat do you do?");
        String action = GameHelper.input();
        switch (action){
            case "exit": System.exit(1);
            case "quit": System.exit(1);
            case "north": walk("north");
                break;
            case "east": walk("east"); 
                break;
            case "west": walk("west"); 
                break;
            case "south": walk("south"); 
                break;
            case "up": walk("up");
                break;
            case "down": walk("down"); 
                break;
        }
    }
}
