/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package orbitalert.Areas;

import java.util.ArrayList;
import orbitalert.GameHelper;
import orbitalert.Objects.Obj;

/**
 *
 * @author Aaron
 */
public class AreaTester {
    public void testAreas() {
       Area newArea = AreaLoader.loadArea("medical");
       GameHelper.output(newArea.getName());
       GameHelper.output(newArea.getDescription());
       
       Room newRoom = RoomLoader.loadRoom(newArea.getType());
       GameHelper.output(newRoom.getSummary());
       
    }
}
