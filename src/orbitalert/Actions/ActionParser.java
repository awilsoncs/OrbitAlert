package orbitalert.Actions;

/**
 *
 * @author Aaron
 */
public class ActionParser {
    public Action parseAction(String actionString){
        String[] parsedAction = actionString.split(" ");
        if(parsedAction.length > 0){
            switch (parsedAction[0]){
                case "exit": System.exit(1);
                case "quit": System.exit(1);
                case "info":
                    if (parsedAction.length > 1){
                        return new InfoAction(parsedAction[1]);
                    } else {
                        return new InfoAction("here");
                    }
//                case "get":
//                    if (parsedAction.length > 1){
//                        get(parsedAction[1]);
//                    }
//                    break;
//                case "drop":
//                    if (parsedAction.length > 1){
//                        drop(parsedAction[1]);
//                    }
//                    break;
//                case "walk":
//                    if (parsedAction.length > 1){
//                        return new WalkAction(parsedAction[1]);
//                    }
//                case "go":
//                    if (parsedAction.length > 1){
//                        return new WalkAction(parsedAction[1]);
//                    }
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
