package orbitalert.Actions;

import java.util.List;
import orbitalert.Objects.Mobs.Mob;

/**
 *
 * @author Aaron
 */
public class QuitAction implements Action {
    static
    {
        ActionParser actionParser = ActionParser.getActionParser();
        actionParser.registerProduct("quit", QuitAction.class);
        actionParser.registerProduct("QQ", QuitAction.class);
        actionParser.registerProduct("exit", QuitAction.class);
    }
    @Override
    public boolean execute(Mob usr) {
        System.exit(1);
        return true;
    }

    @Override
    public void build(List<String> actionString) {}
    
}
