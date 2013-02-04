/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package orbitalert.Objects.Mobs;

import orbitalert.GameHelper;

/**
 *
 * @author Aaron
 */
public class Player extends Mob {
    /**
     *
     */
    @Override
    public void behavior(){
        GameHelper.output("Player pinged");
        GameHelper.output("What do you do?");
        String action = GameHelper.input();
        GameHelper.output("Action Recieved");
        GameHelper.output(action);
    }
}
