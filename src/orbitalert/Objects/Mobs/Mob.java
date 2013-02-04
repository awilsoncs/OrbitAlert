/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package orbitalert.Objects.Mobs;
import java.util.ArrayList;
import orbitalert.Objects.Container;
import orbitalert.Objects.Items.Item;
import orbitalert.Objects.Obj;
import orbitalert.Objects.Useable;

/**
 *
 * @author Aaron
 */
public abstract class Mob extends Obj implements Container {
    int health;
    int actionsPerTick;
    int attackDamage;
    int damageReduction;
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
    private boolean walk(String direction){
        return false;
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
    
    //Container Implementation
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
