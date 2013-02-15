package orbitalert.Actions;

import java.util.List;
import orbitalert.Objects.Mobs.Mob;

/**
 *
 * @author Aaron
 */
public class UnwieldAction implements Action {
    static {
        ActionParser actionParser = ActionParser.getActionParser();
        actionParser.registerProduct("unwield", UnwieldAction.class);
    }

    @Override
    public boolean execute(Mob usr) {
        //Regardless of whether we found a new container,
        //search for the item, same as above.
        if(usr.getWielded() != null){
            usr.unwield(usr.getWielded());
            return true;
        }
        return false;
    }

    @Override
    public void build(List<String> actionString) {}
}
