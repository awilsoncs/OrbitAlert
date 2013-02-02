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
public interface Equipable {
    public abstract void equip(Mob usr);
    public abstract void remove(Mob usr);
    public abstract boolean canEquip();
    public abstract boolean canRemove();
}
