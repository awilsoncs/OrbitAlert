/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package orbitalert.Actions;

import orbitalert.Objects.Items.Item;
import orbitalert.Objects.Mobs.Mob;
import orbitalert.Objects.Obj;

/**
 *
 * @author Aaron
 */
public class DropAction extends SearchingAction {
    
    static {
        ActionParser actionParser = ActionParser.getActionParser();
        actionParser.registerProduct("drop", DropAction.class);
    }
    
    public DropAction(){};
    
    /**
     *
     * @param usr
     * @return
     */
    @Override
    public boolean execute(Mob usr){
        Obj obj = search(getTarget(), usr);
        if (obj != null && obj.getClass() == Item.class){
            Item item = (Item) obj;
            usr.drop(item);
            return true;
        }
        return false;
    }
}
