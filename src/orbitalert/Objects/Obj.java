package orbitalert.Objects;

import orbitalert.World;

/**
 *
 * @author Aaron
 */
public abstract class Obj{
    Container loc;
    boolean getable = false;
    boolean wieldable = false;
    boolean wearable = false;
    boolean active = false;
    boolean moveable = false;
    boolean useable = false;
    String name;
    String shortDescription;
    String longDescription;
    private World world;

    public Container getLoc() {
        return loc;
    }

    public void setLoc(Container loc) {
        this.loc = loc;
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
        if (container.canHold(this) == true){
            Container currentLoc = getLoc();
            currentLoc.remove(this);
            container.add(this);
            this.setLoc(container);
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
    
}
