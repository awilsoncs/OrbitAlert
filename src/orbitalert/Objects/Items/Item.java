/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package orbitalert.Objects.Items;

import java.util.HashMap;
import orbitalert.Objects.Obj;

/**
 *
 * @author Aaron
 */
public class Item extends Obj {
    boolean canPickup;
    
    public Item(HashMap<String, String> itemAttributes){
        setName(itemAttributes.get("name"));
        setShortDescription(itemAttributes.get("shortDescription"));
        setLongDescription(itemAttributes.get("longDescription"));
        setCanPickup(true);
    }
    
    public void setCanPickup(boolean newCanPickup){
        canPickup = newCanPickup;
    }
    public boolean getCanPickup(){
        return this.canPickup;
    }
}
