package orbitalert;

/**
 *
 * @author Aaron
 */
public class Config {
    //Map building
    Cell mapSize;
    int minimumRoomsTotal;
    int minimumRoomsPerArea;
    int newAreaChance;
    String startArea;
    
    //System
    int wordWrap;
    
    public Cell getMapSize() {
        return mapSize;
    }

    public void setMapSize(Cell mapSize) {
        this.mapSize = mapSize;
    }

    public int getMinimumRoomsTotal() {
        return minimumRoomsTotal;
    }

    public void setMinimumRoomsTotal(int minimumRoomsTotal) {
        this.minimumRoomsTotal = minimumRoomsTotal;
    }

    public int getMinimumRoomsPerArea() {
        return minimumRoomsPerArea;
    }

    public void setMinimumRoomsPerArea(int minimumRoomsPerArea) {
        this.minimumRoomsPerArea = minimumRoomsPerArea;
    }

    public int getNewAreaChance() {
        return newAreaChance;
    }

    public void setNewAreaChance(int newAreaChance) {
        this.newAreaChance = newAreaChance;
    }

    public String getStartArea() {
        return startArea;
    }

    public void setStartArea(String startArea) {
        this.startArea = startArea;
    }

    public int getWordWrap() {
        return wordWrap;
    }

    public void setWordWrap(int wordWrap) {
        this.wordWrap = wordWrap;
    }
    
    
}
