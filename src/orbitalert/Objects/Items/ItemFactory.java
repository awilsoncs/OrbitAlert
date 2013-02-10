package orbitalert.Objects.Items;

/**
 *
 * @author Aaron
 */
public class ItemFactory {
    public Item getItem(String areaType){
        return getItem(areaType, ItemLoader.chooseItemFile(areaType));
    };
    public Item getItem(String areaType, String itemType){
        Item item = ItemLoader.loadItem(areaType, itemType);
        
        
        return item;
    };
}
