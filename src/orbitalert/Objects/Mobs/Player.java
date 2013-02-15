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
    private ActionParser actionParser;
    
    public Player(){
        setName("Player");
        setActive(true);
        actionParser = ActionParser.getActionParser();
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

    /**
     *
     * @return
     */
    public ActionParser getActionParser() {
        return actionParser;
    }

    /**
     *
     * @param actionParser
     */
    public void setActionParser(ActionParser actionParser) {
        this.actionParser = actionParser;
    }
}
