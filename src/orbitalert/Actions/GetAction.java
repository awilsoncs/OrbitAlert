/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package orbitalert.Actions;

import orbitalert.Objects.Container;
import orbitalert.Objects.Items.Item;
import orbitalert.Objects.Mobs.Mob;
import orbitalert.Objects.Obj;

/**
 *
 * @author Aaron
 */
public class GetAction extends SearchingAction {
    
    static {
        ActionParser actionParser = ActionParser.getActionParser();
        actionParser.registerProduct("get", GetAction.class);
    }
    
    public GetAction(){};
    
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
        if (obj != null && obj.isGetable()){
            if(obj.getClass() == Item.class){
                Item item = (Item) obj;
                usr.get(item);
                return true;
            }
        }
        return false;
    }
    
}
