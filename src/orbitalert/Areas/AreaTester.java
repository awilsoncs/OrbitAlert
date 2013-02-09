/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package orbitalert.Areas;

import orbitalert.GameHelper;

/**
 *
 * @author Aaron
 */
public class AreaTester {
    public void testAreas() {
       Area newArea = AreaLoader.loadArea();
       GameHelper.output(newArea.getName());
       GameHelper.output(newArea.getDescription());
       
       Room newRoom = RoomLoader.loadRoom(newArea.getType());
       GameHelper.output(newRoom.getSummary());
       
    }
}
