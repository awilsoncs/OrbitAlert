package orbitalert.Objects.Mobs;

import orbitalert.Actions.Action;
import orbitalert.Actions.ActionParser;
import orbitalert.Areas.Room;
import orbitalert.Cell;
import orbitalert.GameHelper;

/**
 *
 * @author Aaron
 */
public class Player extends Mob {
    ActionParser actionParser;
    
    public Player(){
        setName("Player");
        setActive(true);
        setActionParser(new ActionParser());
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
        String actionString = GameHelper.input();
        
        Action action = actionParser.parseAction(actionString);
        if (action != null){
            action.execute(this);
        }
    }

    public ActionParser getActionParser() {
        return actionParser;
    }

    public void setActionParser(ActionParser actionParser) {
        this.actionParser = actionParser;
    }
}
