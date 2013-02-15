package orbitalert.Actions;

import java.util.List;
import orbitalert.Objects.Mobs.Mob;

/**
 *
 * @author Aaron
 */
public interface Action {
    
    public boolean execute(Mob usr);
    
    public void build(List<String> actionString);
}
