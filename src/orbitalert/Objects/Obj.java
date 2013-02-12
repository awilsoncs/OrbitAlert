package orbitalert.Objects;

import java.util.ArrayList;
import orbitalert.Objects.Items.Item;
import orbitalert.World;

/**
 *
 * @author Aaron
 */
public abstract class Obj implements Container
{
    private Container loc;
    private ArrayList<Obj> contents;
    private ArrayList<String> holdTypes;
    private boolean getable = false;
    private boolean wieldable = false;
    private boolean wearable = false;
    private boolean active = false;
    private boolean moveable = false;
    private boolean useable = false;
    private boolean container = false;
    private String name;
    private String shortDescription;
    private String longDescription;
    private World world;

    public Container getLoc() {
        return loc;
    }

    public void setLoc(Container loc) {
        this.loc = loc;
    }

    public void setContents(ArrayList<Obj> contents) {
        this.contents = contents;
    }

    public ArrayList<String> getHoldTypes() {
        return holdTypes;
    }

    public void setHoldTypes(ArrayList<String> holdTypes) {
        this.holdTypes = holdTypes;
    }

    public boolean isGetable() {
        return getable;
    }

    public void setGetable(boolean getable) {
        this.getable = getable;
    }

    
    public boolean isWieldable() {
        return wieldable;
    }

    public void setWieldable(boolean wieldable) {
        this.wieldable = wieldable;
    }

    public boolean isWearable() {
        return wearable;
    }

    public void setWearable(boolean wearable) {
        this.wearable = wearable;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isMoveable() {
        return moveable;
    }

    public void setMoveable(boolean moveable) {
        this.moveable = moveable;
    }

    public boolean isUseable() {
        return useable;
    }

    public void setUseable(boolean useable) {
        this.useable = useable;
    }

    public boolean isContainer() {
        return container;
    }

    public void setContainer(boolean container) {
        this.container = container;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public boolean move(Container container) {
        if (container.canHold(this)){
            Container currentLoc = getLoc();
            currentLoc.remove(this);
            container.add(this);
            return true;
        } else {
            return false;
        }
    }

    public void tick() {
    }
    
    public boolean wield(Obj usr){
        return false;
    }
    
    public boolean unwield(Obj usr){
        return false;
    }
    
    public boolean wear(Obj usr){
        return false;
    }
    
    public boolean unwear(Obj usr){
        return false;
    }
    
    public boolean use(Obj usr){
        return false;
    }
    
    @Override
    public boolean canHold(Obj obj){
        if (obj instanceof Item){
            Item item = (Item) obj;
            if (getHoldTypes().contains(item.getName().toLowerCase())){
                return true;
            }
        }
        return false;
    };
    
    /**
     *
     * @return
     */
    @Override
    public ArrayList<Obj> getContents(){
        return this.contents;
    };
    
    @Override
    public boolean add(Obj obj){
        
        //Make sure we have an inventory before adding to it.
        if(getContents() == null){
            contents = new ArrayList<>();
        }
        contents.add(obj);
        obj.setLoc(this);
        return true;
    };
    
    /**
     *
     * @param obj
     * @return
     */
    @Override
    public boolean remove(Obj obj){
        if (contents != null && contents.contains(obj)) {
            contents.remove(obj);
            obj.setLoc(null);
            return true;
        } else {
            return false;
        }
    }
    
}
