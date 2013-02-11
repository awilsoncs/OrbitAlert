/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package orbitalert.Objects.Mobs;

import orbitalert.Areas.Room;
import orbitalert.Cell;
import orbitalert.GameHelper;
import orbitalert.Objects.Container;
import orbitalert.Objects.Obj;

/**
 *
 * @author Aaron
 */
public class Player extends Mob {
    
    public Player(){
        setName("Player");
        setActive(true);
    }
    /**
     *
     */
    @Override
    public void behavior(){
        Room location = (Room) getLoc();
        Cell locCell = getWorld().getMap().getCell(location);
        GameHelper.output(location.getSummary());
        GameHelper.output(locCell.toString());
        GameHelper.output("\nWhat do you do?");
        String action = GameHelper.input();
        
        String[] parsedAction = action.split(" ");
        if(parsedAction.length > 0){
            switch (parsedAction[0]){
                case "exit": System.exit(1);
                case "quit": System.exit(1);
                case "info":
                    if (parsedAction.length > 1){
                        info(parsedAction[1]);
                    }
                    break;
                case "get":
                    if (parsedAction.length > 1){
                        get(parsedAction[1]);
                    }
                    break;
                case "drop":
                    if (parsedAction.length > 1){
                        drop(parsedAction[1]);
                    }
                    break;
                case "walk":
                    if (parsedAction.length > 1){
                        walk(parsedAction[1]);
                    }
                    break;
                case "go":
                    if (parsedAction.length > 1){
                        walk(parsedAction[1]);
                    }
                    break;
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

    private void info(String target) {
        if (target.equals("here")){
            Container loc = getLoc();
            if (loc.getClass() == Room.class){
                Room locRoom = (Room) loc;
                String output = "[Item]         [ID]\n";
                for(Obj obj:locRoom.getContents()){
                    if(obj.isGetable() || obj.isUseable()){
                        output += obj.getName();
                        output += "         ";
                        output += String.valueOf(obj.hashCode());
                        output += "\n";
                    }
                }
                GameHelper.output(output);
            }
        }
    }
}
