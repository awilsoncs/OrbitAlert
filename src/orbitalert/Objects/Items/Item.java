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
    public Item(HashMap<String, String> itemAttributes){
        //Required
        setName(itemAttributes.get("name"));
        setShortDescription(itemAttributes.get("shortDescription"));
        
        //Optional
        if (itemAttributes.containsKey("longDescription")){
            setLongDescription(itemAttributes.get("longDescription"));
        }
        if (itemAttributes.containsKey("useable")){
            setUseable(Boolean.valueOf(itemAttributes.get("useable")));
        }
        if (itemAttributes.containsKey("wieldable")){
            setWieldable(Boolean.valueOf(itemAttributes.get("wieldable")));
        }
        if (itemAttributes.containsKey("wearable")){
            setWearable(Boolean.valueOf(itemAttributes.get("wearable")));
        }
        if (itemAttributes.containsKey("getable")){
            setGetable(Boolean.valueOf(itemAttributes.get("getable")));
        }
        if (itemAttributes.containsKey("active")){
            setActive(Boolean.valueOf(itemAttributes.get("active")));
        }
    }
}
