/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package orbitalert.Areas;

import orbitalert.Objects.Items.Item;
import orbitalert.Objects.Items.ItemLoader;

/**
 *
 * @author Aaron
 */
public class RoomFactory {
    
    public Room getRoom(String areaType){
        return getRoom(areaType, RoomLoader.chooseRoomFile(areaType));
    };
    
    public Room getRoom(String areaType, String roomType){
        Room room = RoomLoader.loadRoom(areaType, roomType);
        addItems(room, areaType);
        return room;
    };
    
    private void addItems(Room room, String areaType){
        int numberOfItems = (int) ((Math.random() * 2) + Math.random() * 1);
        for (;numberOfItems > 0; numberOfItems--){
            Item newItem = ItemLoader.loadItem(areaType);
            room.add(newItem);
        }
    }
    
}
