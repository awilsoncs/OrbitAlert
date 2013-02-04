/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
