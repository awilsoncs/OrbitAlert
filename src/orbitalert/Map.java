package orbitalert;
import java.util.ArrayList;
import orbitalert.Areas.Room;

/**
 *
 * @author Aaron
 */
public class Map  {
        private Room[][][] mapArray;

        public Cell getDimensions(){
            int maxX = mapArray.length - 1;
            int maxY = mapArray[0].length - 1;
            int maxZ = mapArray[0][0].length - 1;
            Cell dimensions = new Cell(maxX, maxY, maxZ);
            return dimensions;
        }

        public Map(Cell mapSize){
            int x = mapSize.getX();
            int y = mapSize.getY();
            int z = mapSize.getZ();
            mapArray = new Room[x][y][z];
        }

        public void addRoom (Cell cell, Room room) {
            if (getRoom(cell) == null) {
                int x = cell.getX();
                int y = cell.getY();
                int z = cell.getZ();
                mapArray[x][y][z] = room;
            } else {
                GameHelper.output("Error: Attempting to write room in non-null cell.");
            }
        }

        public String getDirection(Cell previousCell, Cell currentCell) {
            //Returns the orthogonal direction traveled from previousCell to currentCell.
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
                    case "east": currentRoom.addExit("west");
                    case "south": currentRoom.addExit("north");
                    case "west": currentRoom.addExit("east");
                    case "up": currentRoom.addExit("down");
                    case "down": currentRoom.addExit("up");
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
                    case "east": currentRoom.addExit("west", newTask);
                    case "south": currentRoom.addExit("north", newTask);
                    case "west": currentRoom.addExit("east", newTask);
                    case "up": currentRoom.addExit("down", newTask);
                    case "down": currentRoom.addExit("up", newTask);
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
            int x = cell.getX();
            int y = cell.getY();
            int z = cell.getZ();
            if (mapArray[x][y][z] != null) {
                Room room = this.mapArray[x][y][z];
                return room;
            }
                return null;
        }
    }
