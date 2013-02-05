package orbitalert.Objects.Mobs;

import java.util.ArrayList;
import orbitalert.Areas.Exit;
import orbitalert.Areas.Room;
import orbitalert.Cell;
import orbitalert.Map;
import orbitalert.Objects.Container;
import orbitalert.Objects.Items.Item;
import orbitalert.Objects.Obj;
import orbitalert.Objects.Useable;
import orbitalert.World;

/**
 *
 * @author Aaron
 */
public abstract class Mob extends Obj implements Container {
    private int health;
    private int actionsPerTick;
    private int attackDamage;
    private int damageReduction;
    boolean canAttack;
    boolean canBeAttacked;
    ArrayList<Obj> contents;
    //ArrayList<Equipable> worn;
    //ArrayList<Equipable> wielded;
    String[] entersRoomPhrases;
    String[] leavesRoomPhrases;
    String[] attackPhrases;
    String[] damagedPhrases;
    String[] diesPhrases;
    String[] phrases;
    private World world;


    //State Control
    /**
     *
     * @return
     */
    public int getHealth(){
        return health;
    };
    /**
     *
     * @param newHealth
     * @return
     */
    public boolean setHealth(int newHealth){
        if (newHealth >= 0) {
            health = newHealth;
            return true;
        } else {
            return false;
        }
    };
    @Override
    public void tick(){
        behavior();
    }
    /**
     *
     */
    public abstract void behavior();
    //Combat Suite
    private boolean attack(Mob target){
        return false;
    };
    /**
     *
     * @param damage
     */
    public void damage(int damage){
        //Used when damage is inflicted upon this target.
    };
    private boolean getCanAttack(){
        return false;
    };
    /**
     *
     * @return
     */
    public boolean getCanBeAttacked(){
        return true;
    };
    //Movement Suite
    public boolean walk(String direction){
        
        //Check for exits in that direction
        if(canStep(direction)){
            Room nextStep = getStep(direction);
            move(nextStep);
        }
        return true;
    };
    
    private boolean canStep(String direction){
        Room locRoom = (Room) getLoc();
        Exit roomExit = locRoom.getExit(direction);
        if (roomExit.getIsOpen()){
            return true;
        }
        return false;
    };
    
    public Room getStep(String direction){
        Room locRoom = (Room) getLoc();
        Map map = getWorld().getMap();
        Cell locCell = map.getCell(locRoom);
        
        int x = locCell.getX();
        int y = locCell.getY();
        int z = locCell.getZ();
        
        switch (direction){
            case "north": y += 1; 
                break;
            case "south": y -= 1;
                break;
            case "east": x += 1;
                break;
            case "west": x -= 1;
                break;
            case "up": z += 1;
                break;
            case "down": z -= 1;
                break;                
        }
        Cell newCell = new Cell(x,y,z);
        Room newRoom = map.getRoom(newCell);
        if(newRoom != null){
            return newRoom;
        } else {
            return null;
        }
    };
    //private String getDirectionTo(Obj object){
    //    This is going to need to use A* pathfinding.
    
    //Obj interaction
    private boolean get(Item item){
        if (item.getCanMove()){
            item.move(this);
            return true;
        } else {
            return false;
        }
    }
    
    private boolean drop(Item item){
        //Moves the item into the Mob's loc.
        Container newLoc = getLoc();
        boolean result = item.move(newLoc);
        return result;
    };
    
    private boolean use(Useable item){
        return false;
    };

    /**
     *
     * @param obj
     * @return
     */
    @Override
    public boolean canHold(Obj obj){
        if (obj instanceof Item){
            return true;
        } else {
            return false;
        }
    };
    
    /**
     *
     * @return
     */
    @Override
    public ArrayList<Obj> getContents(){
        return this.contents;
    };
    
    /**
     *
     * @param obj
     * @return
     */
    @Override
    public boolean add(Obj obj){
        contents.add(obj);
        return true;
    };
    
    /**
     *
     * @param obj
     * @return
     */
    @Override
    public boolean remove(Obj obj){
        if (contents.contains(obj)) {
            contents.remove(obj);
            return true;
        } else {
            return false;
        }
    }
    
    //Environment interaction
    //Need to consider how the Mob can interact with the environment.
    //Social (Cosmetic)
    //private String getEntersRoomPhrase(){};
    //private String getLeavesRoomPhrase(){};
    //private String getAttackPhrase(){};
    //private String getDamagedPhrase(){};
    //private String getDiesPhrase(){};
    //private String getPhrase(){};
}
