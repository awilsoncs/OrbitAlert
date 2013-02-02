/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package orbitalert.Areas;

import java.util.ArrayList;
import orbitalert.Objects.Obj;

/**
 *
 * @author Aaron
 */
public class AreaTester {
    public void testAreas() {
       Area newArea = AreaLoader.loadArea("medical");
       System.out.println(newArea.getName());
       System.out.println(newArea.getDescription());
       
       Room newRoom = RoomLoader.loadRoom(newArea.getType());
       System.out.println(newRoom.getSummary());
       
    }
}
