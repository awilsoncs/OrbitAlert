package orbitalert.Actions;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Aaron
 */
public class ActionParser {
    private static ActionParser actionParser;
    private HashMap registeredActions = new HashMap();
    
    private ActionParser(){};
    
    public static synchronized ActionParser getActionParser()
    {
        if (actionParser == null)
        {
            actionParser = new ActionParser();
        }
        return actionParser;
    }
    
    public void registerProduct (String actionID, Class actionClass)
    {
        registeredActions.put(actionID, actionClass);
    }
    
    private Action createAction (String actionID) {
        Class actionClass = (Class) registeredActions.get(actionID);
        if (actionClass != null) {
            try 
            {
                Constructor actionConstructor = actionClass.getConstructor();
                return (Action) actionConstructor.newInstance();
            } catch (NoSuchMethodException 
                    | SecurityException 
                    | InstantiationException 
                    | IllegalAccessException 
                    | IllegalArgumentException 
                    | InvocationTargetException ex){}
            }
        return null;
    }
    
    /**
     *
     * @param actionString
     * @return
     */
    public Action parseAction(String actionString)
    {
        List<String> parsedAction = Arrays.asList(actionString.split(" "));
        Action newAction = createAction(parsedAction.get(0));
        if (newAction != null) 
        {
            newAction.build(parsedAction);
            return newAction;
        }
        return null;
    }

    public HashMap getRegisteredActions() {
        return registeredActions;
    }

    public void setRegisteredActions(HashMap registeredActions) {
        this.registeredActions = registeredActions;
    }
}
