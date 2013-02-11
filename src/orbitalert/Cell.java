package orbitalert;

/**
 *
 * @author Aaron
 */
public class Cell {
    private int x;
    private int y;
    private int z;

    public Cell(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
    }

    public int getX() {
            return x;
    }
    
    public int getY() {
            return y;
    }
    
    public int getZ() {
            return z;
    }

    @Override
    public String toString() {
        return "Cell{" + "x=" + x + ", y=" + y + ", z=" + z + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Cell other = (Cell) obj;
        if (this.x != other.x) {
            return false;
        }
        if (this.y != other.y) {
            return false;
        }
        if (this.z != other.z) {
            return false;
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + this.x;
        hash = 89 * hash + this.y;
        hash = 89 * hash + this.z;
        return hash;
    }
}
