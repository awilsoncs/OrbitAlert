/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package orbitalert.Objects.Items;
/**
 *
 * @author Aaron
 */
public class ItemTester {
    public void testItems(){
        Item newItem = ItemLoader.loadItem("medical");
        System.out.println(newItem.getName());
        System.out.println(newItem.getShortDescription());
        System.out.println(newItem.getLongDescription());
    }
}
