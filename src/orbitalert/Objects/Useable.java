/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package orbitalert.Objects;

import orbitalert.Objects.Mobs.Mob;

/**
 *
 * @author Aaron
 */
public interface Useable {
    public abstract boolean getCanUse();
    public abstract void use(Mob usr);
}
