package orbitalert.Objects.Items;

import java.util.ArrayList;
import orbitalert.Objects.Obj;

/**
 *
 * @author Aaron
 */
public class ItemFactory {
    private static ItemFactory itemFactory;
    
    private ItemFactory(){};
    
    /**
     *
     * @return
     */
    public static synchronized ItemFactory getItemFactory(){
        if (itemFactory == null){
            itemFactory = new ItemFactory();
        }
        return itemFactory;
    }
    
    /**
     *
     * @param areaType
     * @return
     */
    public Item getItem(String areaType){
        return getItem(areaType, ItemLoader.chooseItemFile(areaType));
    };
    
    /**
     *
     * @param areaType
     * @param itemType
     * @return
     */
    public Item getItem(String areaType, String itemType){
        Item item = ItemLoader.loadItem(areaType, itemType);
        if (item != null
                && item.isContainer()){
            item.setContents(new ArrayList<Obj>());
            item.getContents().addAll(
                    getItemsForContainer(areaType, item.getHoldTypes()));
            for(Obj content:item.getContents()){
                content.setLoc(item);
            }
        }
        return item;
    };
    
    /**
     *
     * @param areaType
     * @param itemTypes
     * @return
     */
    public ArrayList<Item> getItemsForContainer(
            String areaType, ArrayList<String> itemTypes){
        
        ArrayList<Item> itemList = new ArrayList<>();
        int itemCount = (int) 1;
//                ((Math.random() * 7)
//                + (Math.random() * 7)
//                + (Math.random() * 7));
//        itemCount = itemCount - 10;
        
        for(; itemCount > 0; itemCount--){
            int random = (int) (Math.random() * itemTypes.size());
            String itemType = itemTypes.get(random);
            Item item = getItem(areaType, itemType);
            if (item != null){
                itemList.add(item);
            }
        }
        return itemList;
    }
}
