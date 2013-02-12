package orbitalert.Actions;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Aaron
 */
public class ActionParser {
    public Action parseAction(String actionString){
        List<String> parsedAction = Arrays.asList(actionString.split(" "));
        if(parsedAction.size() > 0){
            switch (parsedAction.get(0)){
                case "exit": System.exit(1);
                case "quit": System.exit(1);
                case "info":
                    if (parsedAction.size() > 1){
                        return new InfoAction(
                                parsedAction.subList(1,parsedAction.size()));
                    } else {
                        return new InfoAction("here");
                    }
                case "inv": return new InfoAction("inv");
                case "inventory": return new InfoAction("inv");
                case "get":
                    if (parsedAction.size() > 1){
                        return new GetAction(
                                parsedAction.subList(1,parsedAction.size()));
                    }
                case "drop":
                    if (parsedAction.size() > 1){
                        return new DropAction(
                                parsedAction.subList(1,parsedAction.size()));
                    }
                    break;
                case "walk":
                    if (parsedAction.size() > 1){
                        return new WalkAction(parsedAction.get(1));
                    }
                case "go":
                    if (parsedAction.size() > 1){
                        return new WalkAction(parsedAction.get(1));
                    }
                case "north":   return new WalkAction("north");
                case "east":    return new WalkAction("east");
                case "west":    return new WalkAction("west");
                case "south":   return new WalkAction("south");
                case "up":      return new WalkAction("up");
                case "down":    return new WalkAction("down");
            }
        }
        return null;
    }
}
