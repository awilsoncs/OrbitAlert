package orbitalert.Actions;

import orbitalert.Objects.Items.Item;
import orbitalert.Objects.Mobs.Mob;
import orbitalert.Objects.Obj;

/**
 *
 * @author Aaron
 */
public class RemoveAction extends SearchingAction {
    static {
        ActionParser actionParser = ActionParser.getActionParser();
        actionParser.registerProduct("remove", RemoveAction.class);
    }

    @Override
    public boolean execute(Mob usr) {
        Obj obj = search(getTarget(), usr);
        if (obj != null && obj.getClass() == Item.class){
            Item item = (Item) obj;
            if (usr.getWorn().contains(item)){
                usr.remove(item);
                return true;
            }
        }
        return false;
    }
}
