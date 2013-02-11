/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package orbitalert.Actions;

import orbitalert.Objects.Mobs.Mob;

/**
 *
 * @author Aaron
 */
public class WalkAction implements Action {
    private String direction;
    
    public WalkAction(String direction){
        setDirection(direction);
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }
    
    @Override
    public boolean execute(Mob usr){
        usr.walk(getDirection());
        return false;
    }
}
