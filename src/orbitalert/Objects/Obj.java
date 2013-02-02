/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package orbitalert.Objects;

/**
 *
 * @author Aaron
 */
public abstract class Obj{
    Container loc;
    boolean canUse;
    boolean canMove;
    String name;
    String shortDescription;
    String longDescription;
    
    public void setName(String newName){
        name = newName;
    }
    
    public void setShortDescription(String newShortDescription){
        shortDescription = newShortDescription;
    }
    
    public void setLongDescription(String newLongDescription){
        longDescription = newLongDescription;
    }

    public String getName() {
        return name;
    }
    public boolean getCanUse() {
        return canUse;
    }
    public boolean getCanMove() {
        return canMove;
    }
    public String getShortDescription() {
        return shortDescription;
    }
    public String getLongDescription() {
        return longDescription;
    }
    public Container getLoc() {
        return loc;
    }
    private void setLoc(Container container) {
        loc = container;
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
}
