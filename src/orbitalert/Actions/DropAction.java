/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package orbitalert.Actions;

import java.util.List;
import orbitalert.Objects.Items.Item;
import orbitalert.Objects.Mobs.Mob;
import orbitalert.Objects.Obj;

/**
 *
 * @author Aaron
 */
public class DropAction extends SearchingAction {
    public DropAction(List<String> parsedString){
        parseSearch(parsedString);
    }
    
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
