package orbitalert.Objects.Mobs;

import java.util.ArrayList;
import orbitalert.Areas.Exit;
import orbitalert.Areas.Room;
import orbitalert.Cell;
import orbitalert.GameHelper;
import orbitalert.Map;
import orbitalert.Objects.Container;
import orbitalert.Objects.Items.Item;
import orbitalert.Objects.Obj;
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
        if (roomExit != null && roomExit.isOpen()){
            return true;
        } else if (roomExit != null && roomExit.isOpen() == false){
            GameHelper.output("That exit is locked.");
            return false;
        }
        GameHelper.output("You cannot go that way.");
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
            GameHelper.output("Null room");
            return null;
        }
    };
    //private String getDirectionTo(Obj object){
    //    This is going to need to use A* pathfinding.
    
    /**
     *
     * @param item
     * @return
     */
    public boolean get(Item item){
        if (item.isGetable()){
            item.move(this);
            return true;
        } else {
            return false;
        }
    }
    
    /**
     *
     * @param itemName
     * @return
     */
    public boolean get(String itemName){
        Container loc = getLoc();
        ArrayList<Obj> objList = loc.getContents();
        for(Obj obj:objList){
            //Normalize capitalization.
            String objName = obj.getName().toLowerCase();
            itemName = itemName.toLowerCase();
            
            //Skip over items that aren't getable
            //then check by name and hashcode.
            if(obj.isGetable() && 
                    (objName.equals(itemName) ||
                    String.valueOf(obj.hashCode()).equals(itemName))){
                
                //Make sure it's an Item, then cast and get it.
                if(obj.getClass() == Item.class){
                    Item item = (Item) obj;
                    return get(item);
                }
            }
        }
        return false;
    }
    
    /**
     *
     * @param item
     * @return
     */
    public boolean drop(Item item){
        //Moves the item into the Mob's loc.
        Container newLoc = getLoc();
        boolean result = item.move(newLoc);
        String newShortDescription = "A " + item.getName().toLowerCase() +
                 " has been discarded here. ";
        item.setShortDescription(newShortDescription);
        return result;
    };
    
    /**
     *
     * @param itemName
     * @return
     */
    public boolean drop(String itemName){
        for (Obj obj:getContents()){
            String objName = obj.getName().toLowerCase();
            itemName = itemName.toLowerCase();
            
            if(objName.equals(itemName) ||
                    String.valueOf(obj.hashCode()).equals(itemName)){
                if(obj.getClass() == Item.class){
                    Item item = (Item) obj;
                    return drop(item);
                }
            }
        }
        return false;
    }
    
    private boolean use(Item item){
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
