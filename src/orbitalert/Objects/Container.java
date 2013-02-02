/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package orbitalert.Objects;
import java.util.ArrayList;

/**
 *
 * @author Aaron
 */
public interface Container {
	public abstract boolean canHold(Obj obj);
	public abstract ArrayList<Obj> getContents();
	public abstract boolean add(Obj obj);
        public abstract boolean remove(Obj obj);
}
