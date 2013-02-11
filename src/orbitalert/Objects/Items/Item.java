/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package orbitalert.Objects.Items;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import orbitalert.Objects.Obj;

/**
 *
 * @author Aaron
 */
public class Item extends Obj

{    
    public Item(HashMap<String, String> itemAttributes){
        //Required
        
        //This walks through a series of attributes found in the item files
        //and sets the appropriate attributes.
        setName(itemAttributes.get("name"));
        
        if (itemAttributes.containsKey("shortDescription")){
            setShortDescription(itemAttributes.get("shortDescription"));
        } else if (itemAttributes.containsKey("short")){
            setShortDescription(itemAttributes.get("short"));
        } else {
            setShortDescription("An item has been discarded here.");
        }
        
        //Optional
        if (itemAttributes.containsKey("longDescription")){
            setLongDescription(itemAttributes.get("longDescription"));
        } else if (itemAttributes.containsKey("long")){
            //Resource files changed format partways through production.
            setLongDescription(itemAttributes.get("long"));            
        }
        if (itemAttributes.containsKey("container")){
            setContainer(Boolean.valueOf(itemAttributes.get("container")));
            if(isContainer() && itemAttributes.containsKey("holds")){
                ArrayList<String> holdTypes = new ArrayList<>(Arrays.asList(
                        itemAttributes.get("holds").split(", ")));
                setHoldTypes(holdTypes);
            }
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
