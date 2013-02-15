package orbitalert.Actions;

import orbitalert.Objects.Container;
import orbitalert.Objects.Items.Item;
import orbitalert.Objects.Mobs.Mob;
import orbitalert.Objects.Obj;

/**
 *
 * @author Aaron
 */
public class WearAction extends SearchingAction {
    static {
        ActionParser actionParser = ActionParser.getActionParser();
        actionParser.registerProduct("wear", WearAction.class);
    }

    @Override
    public boolean execute(Mob usr) {
        Container loc = usr.getLoc();
        //Search the player's loc for the item's container, if we need to.
        if(getTargetContainer() != null){
            Obj container = search(getTargetContainer(), loc);
            if (container != null && container.isContainer()){
                loc = (Container) container;
            }
        }
        
        //Regardless of whether we found a new container,
        //search for the item, same as above.
        Obj obj = search(getTarget(), loc);
        if (obj != null && obj.isGetable() && obj.isWearable()){
            if(obj.getClass() == Item.class){
                Item item = (Item) obj;
                usr.wear(item);
                return true;
            }
        }
        return false;
    }
}
