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
public interface Action {
    
    public boolean execute(Mob usr);
}
