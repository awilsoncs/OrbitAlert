/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package orbitalert.Actions;

import java.util.List;
import orbitalert.Objects.Mobs.Mob;

/**
 *
 * @author Aaron
 */
public class WalkAction implements Action {
    private String direction;
    
    static {
        ActionParser actionParser = ActionParser.getActionParser();
        actionParser.registerProduct("walk", WalkAction.class);
        actionParser.registerProduct("go", WalkAction.class);
        actionParser.registerProduct("north", WalkAction.class);
        actionParser.registerProduct("west", WalkAction.class);
        actionParser.registerProduct("east", WalkAction.class);
        actionParser.registerProduct("south", WalkAction.class);
        actionParser.registerProduct("up", WalkAction.class);
        actionParser.registerProduct("down", WalkAction.class);
    }
    
    public WalkAction(){};
    
    @Override
    public void build(List<String> actionString){
        if (actionString.size() > 1){
            direction = actionString.get(1);
        } else {
            direction = actionString.get(0);
        }
    };
    
    public WalkAction(String direction){
        this.direction = direction;
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
