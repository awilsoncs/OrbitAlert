package orbitalert;

import java.util.ArrayList;
import java.util.HashMap;
import orbitalert.Areas.Room;

/**
 *
 * @author Aaron
 */
public class Map  {
        private Cell dimensions;
        private HashMap<Cell, Room> roomMap;
        private HashMap<Room, Cell> cellMap;

        public Cell getDimensions(){
            return this.dimensions;
        }

     /**
     *
     * @param mapSize
     */
    public Map(Cell dimensions){
            this.dimensions = dimensions;
            roomMap = new HashMap<>();
            cellMap = new HashMap<>();
        }

    public void addRoom (Cell cell, Room room) {
        if (getRoom(cell) == null) {            
            roomMap.put(cell, room);            
            cellMap.put(room, cell);
        } else {
            GameHelper.output(
                    "Error: Attempting to write room in non-null cell.");
        }
    }

    public String getDirection(Cell previousCell, Cell currentCell) {
        //Returns the orthogonal direction traveled
        //from previousCell to currentCell.
        
        int x1 = previousCell.getX();
        int y1 = previousCell.getY();
        int z1 = previousCell.getZ();

        int x2 = currentCell.getX();
        int y2 = currentCell.getY();
        int z2 = currentCell.getZ();

        int dX = x2-x1;
        int dY = y2-y1;
        int dZ = z2-z1;

        if (dX > 0){
            return "east";
        } else if (dX < 0) {
            return "west";
        } else if (dY > 0) {
            return "north";
        } else if (dY < 0) {
            return "south";
        } else if (dZ > 0) {
            return "up";
        } else if (dZ < 0) {
            return "down";
        }
        return null;
    }

    @SuppressWarnings("empty-statement")
    public void linkRooms(Cell previousCell, Cell currentCell) {;
        String direction = getDirection(previousCell, currentCell);
        //All directions are previousRoom exits.
        Room previousRoom = getRoom(previousCell);
        //GameHelper.output(previousRoom.getName());
        Room currentRoom = getRoom(currentCell);
       //GameHelper.output(currentRoom.getName());
        if (direction != null && !direction.isEmpty()) {
            previousRoom.addExit(direction);
            switch (direction) {
                case "north": currentRoom.addExit("south");
                        break;
                case "east": currentRoom.addExit("west");
                        break;
                case "south": currentRoom.addExit("north");
                        break;
                case "west": currentRoom.addExit("east");
                        break;
                case "up": currentRoom.addExit("down");
                        break;
                case "down": currentRoom.addExit("up");
                        break;
            }
        }
    }

        public void linkRooms(Cell previousCell, Cell currentCell, Task newTask) {
            String direction = getDirection(previousCell, currentCell);
            //All directions are previousRoom exits.
            Room previousRoom = getRoom(previousCell);
            Room currentRoom = getRoom(currentCell);
            if (direction != null && !direction.isEmpty()) {
                previousRoom.addExit(direction, newTask);
                switch (direction) {
                    case "north": currentRoom.addExit("south", newTask);
                            break;
                    case "east": currentRoom.addExit("west", newTask);
                            break;
                    case "south": currentRoom.addExit("north", newTask);
                            break;
                    case "west": currentRoom.addExit("east", newTask);
                            break;
                    case "up": currentRoom.addExit("down", newTask);
                            break;
                    case "down": currentRoom.addExit("up", newTask);
                            break;
                }
            }
        }

        public ArrayList<Cell> getNeighbors (Cell cell) {
            //Parse Cell
            int x = cell.getX();
            int y = cell.getY();
            int z = cell.getZ();
            int maxX = getDimensions().getX();
            int maxY = getDimensions().getY();
            int maxZ = getDimensions().getZ();
            //Gather 6 neighbors.
            ArrayList<Cell> neighbors = new ArrayList<>();
            if (x > 0) {
                Cell newCell = new Cell(x-1, y, z);
                neighbors.add(newCell);
            }
            if (x < maxX) {
                Cell newCell = new Cell(x+1, y, z);
                neighbors.add(newCell);
            }
            if (y > 0) {
                Cell newCell = new Cell(x, y-1, z);
                neighbors.add(newCell);
            }
            if (y < maxY) {
                Cell newCell = new Cell(x, y+1, z);
                neighbors.add(newCell);
            }
            if (z < 0) {
                Cell newCell = new Cell(x, y, z-1);
                neighbors.add(newCell);
            }
            if (z < maxZ) {
                Cell newCell = new Cell(x, y, z+1);
                neighbors.add(newCell);
            }
            return neighbors;
        }

        public Room getRoom(Cell cell){
            Room room = roomMap.get(cell);
            return room;
        }
        
        public Cell getCell(Room room){
            Cell cell = cellMap.get(room);
            return cell;
        }
    }
