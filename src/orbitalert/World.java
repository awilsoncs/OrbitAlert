package orbitalert;

import java.util.ArrayList;
import orbitalert.Objects.Mobs.Player;
import orbitalert.Objects.Obj;

/**
 *
 * @author Aaron
 */
public class World {
    private Map map;
    private ArrayList<Obj> objs;

    public World(
            Map newMap,
            ArrayList<Obj> objList,
            Cell mapSize,
            Cell startCell,
            int roomCount,
            Player player
            )
    {
        map = newMap;
        objs = objList;
        
        player.setWorld(this);
    }

    //Getters here.
    public Map getMap(){
        return this.map;
    }
    public ArrayList<Obj> getObjs(){
        return this.objs;
    }
    public void addObj(Obj obj){
        objs.add(obj);
    }
}
