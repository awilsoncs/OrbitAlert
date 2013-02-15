package orbitalert;

/**
 *
 * @author Aaron
 */
public class Config {
    
    private static Config config;
    
    //Map building
    Cell mapSize = new Cell(4,4,4); 
    int maximumRoomsTotal = 80;
    int minimumRoomsPerArea = 3;
    int newAreaChance = 25;
    int maxRoomsPerPath = 8;
    int traversalChance = 70;
    String startArea;
    
    //System
    int wordWrap = 80;
    
    private Config(){
        //do stuff here
    }

    public static synchronized Config getConfig() {
        if (config == null) {
            config = new Config();
        }
        return config;
    }
    
    public Cell getMapSize() {
        return mapSize;
    }

    public void setMapSize(Cell mapSize) {
        this.mapSize = mapSize;
    }

    public int getMaximumRoomsTotal() {
        return maximumRoomsTotal;
    }

    public void setMaximumRoomsTotal(int maximumRoomsTotal) {
        this.maximumRoomsTotal = maximumRoomsTotal;
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

    public int getMaxRoomsPerPath() {
        return maxRoomsPerPath;
    }

    public void setMaxRoomsPerPath(int maxRoomsPerPath) {
        this.maxRoomsPerPath = maxRoomsPerPath;
    }

    public int getTraversalChance() {
        return traversalChance;
    }

    public void setTraversalChance(int traversalChance) {
        this.traversalChance = traversalChance;
    }
    
    public int getWordWrap() {
        return wordWrap;
    }

    public void setWordWrap(int wordWrap) {
        this.wordWrap = wordWrap;
    } 
}
