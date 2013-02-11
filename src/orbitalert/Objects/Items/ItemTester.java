package orbitalert.Objects.Items;

import orbitalert.GameHelper;

/**
 *
 * @author Aaron
 */
public class ItemTester {
    public void testItems(){
        Item newItem = ItemLoader.loadItem("medical");
        GameHelper.output(newItem.getName());
        GameHelper.output(newItem.getShortDescription());
        GameHelper.output(newItem.getLongDescription());
    }
}
